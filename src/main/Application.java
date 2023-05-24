package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import dao.DistributoriAutomaticiDAO;
import dao.ParcoMezziDAO;
import dao.PuntiVenditaDAO;
import dao.TesseraDAO;
import dao.TitoliDiViaggioDAO;
import dao.UtenteDAO;
import entities.Abbonamento;
import entities.Biglietto;
import entities.DistributoriAutomatici;
import entities.ParcoMezzi;
import entities.PeriodoManutenzione;
import entities.PeriodoServizi;
import entities.PuntiVendita;
import entities.Stato;
import entities.Tessera;
import entities.Tratta;
import entities.Utente;
import entities.enums.Periodicità;
import entities.enums.TipoDiMezzo;
import entities.enums.stato_parcoMezzi;
import lombok.extern.slf4j.Slf4j;
import utils.JPAUtil;

@Slf4j
public class Application {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

		UtenteDAO ud = new UtenteDAO();
		TesseraDAO td = new TesseraDAO();
		ParcoMezziDAO pmd = new ParcoMezziDAO(emf);
		PuntiVenditaDAO pvd = new PuntiVenditaDAO(emf);
		TitoliDiViaggioDAO tvd = new TitoliDiViaggioDAO(emf);

		Utente utente1 = new Utente("Mario", "Rossi");
		// ud.save(utente1);

		Tessera tessera1 = new Tessera(LocalDate.of(2023, 05, 02));
		// td.save(tessera1);

		// **** COLLEGAMENTO TESSERA A UTENTE ****
		// ud.collegaTessera(1, tessera1);

		PuntiVendita pvendita1 = new PuntiVendita("Via Verdi, 18", 1, false);
		// pvd.save(pvendita1);

		// pvd.getById("1132b40d-f98a-4ce5-860a-34cacf61385e");

		ParcoMezzi mezzoUno = pmd.getMezzo("82be1454-1136-4a2f-b729-c971423fec35");
		
		Biglietto biglietto1 = new Biglietto(LocalDate.of(2023, 05, 22), true, pvendita1, true, mezzoUno,
				LocalDate.of(2023, 05, 23));
		// tvd.saveBiglietto(biglietto1);

		Tessera tesseraUno = td.getById(1004);

		Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2023, 03, 10), true, pvendita1, Periodicità.SETTIMANALE,
				LocalDate.of(2023, 03, 10).plusDays(7), tesseraUno);
		// tvd.saveAbbonamento(abbonamento1);

		PeriodoManutenzione manutenzione = new PeriodoManutenzione(mezzoUno, LocalDate.of(2022, 12, 01),
				LocalDate.of(2022, 12, 10));
		// pmd.savePeriodoManutenzione(manutenzione);

		PeriodoServizi servizio = new PeriodoServizi(mezzoUno, LocalDate.of(2022, 12, 10), LocalDate.of(2023, 12, 01));
		// pmd.savePeriodoServizio(servizio);

		// System.out.println(mezzoUno.getPeriodoManutenzione());
		// System.out.println(mezzoUno.getPeriodoServizio());

		// tvd.checkValiditaAbbonamento(tesseraUno);
		
		//mezzoUno.getBiglietti().stream().forEach(b -> log.info(b.toString()));


		//pvd.getTicketsBySalesPoint("88db3b5f-e0f3-4341-b933-8d3cdeae2636");

		//tvd.bigliettiVendutiPerTempo(LocalDate.of(2023, 05, 10), LocalDate.of(2023, 05, 26)).stream().forEach(b -> log.info(b.toString()));

		emf.close();

	}

	public static void gestioneMezzi(int enter, ParcoMezziDAO pmd, Scanner scan) {

		int numeroInserito = 0;
		int entrata = enter;
		while (entrata == 2) {
			System.out.println(
					"premere 2 per creare una tratta, premere 3 per creare un mezzo (per creare un mezzo abbiamo bisogno di avere una tratta esistente) premere 4 per uscire");
			System.out.println();
			System.out.println("di seguito i mezzi:");
			System.out.println();
			pmd.getAllMezzi().stream().forEach(m -> System.out.println(m));
			System.out.println();
			System.out.println("di seguito le tratte:");
			System.out.println();
			pmd.getAllTratte().stream().forEach(t -> System.out.println(t));
			numeroInserito = scan.nextInt();
			scan.nextLine();
			switch (numeroInserito) {
			case 2:
				System.out.println("inserisci zona di partenza:");
				String zonaDiPartenza = scan.nextLine();
				System.out.println("inserisci zona di arrivo:");
				String zonaDiArrivo = scan.nextLine();
				System.out.println("inserisci il tempo medio di percorrenza:");
				String tempoMedioPercorrenza = scan.nextLine();
				System.out.println("inserisci la distanza:");
				String distanza = scan.nextLine();
				Tratta tratta = new Tratta(zonaDiPartenza, zonaDiArrivo, tempoMedioPercorrenza, distanza);
				pmd.salvaTratta(tratta);
				break;
			case 3:
				System.out.println("inserisci l'id della tratta da inserire:");
				String idInserito = scan.nextLine();
				Tratta trattaTrovata = pmd.getTratta(idInserito);
				System.out.println("inserisci lo stato del mezzo (servizio, manutenzione):");
				String statoMezzo = scan.nextLine();
				System.out.println("inserisci la capienza:");
				int capienza = scan.nextInt();
				scan.nextLine();
				System.out.println("inserisci la data di partenza:");
				LocalDateTime dataPartenza = formattareData(scan);
				System.out.println("inserisci la data di arrivo: ");
				LocalDateTime dataArrivo = formattareData(scan);
				System.out.println("inserisci il tempo impiegato:");
				double tempoImpiegato = scan.nextDouble();
				scan.nextLine();
				System.out.println("inserisci il numero delle volte della percorrenza tratta:");
				int numeroVoltePercorrenzaTratta = scan.nextInt();
				scan.nextLine();
				System.out.println("inserisci il tipo del mezzo(Tram, Autobus;):");
				String tipoMezzo = scan.nextLine();
				ParcoMezzi parcoMezzi = new ParcoMezzi(stato_parcoMezzi.valueOf(statoMezzo), capienza, trattaTrovata,
						dataPartenza, dataArrivo, tempoImpiegato, numeroVoltePercorrenzaTratta,
						TipoDiMezzo.valueOf(tipoMezzo));
				pmd.salvaMezzo(parcoMezzi);
				break;
			case 4:
				entrata = 4;
				numeroInserito = 6;
			}
		}
		// ���������������������������� SCANNER PRINCIPALE ��������������������������
//		Scanner scan = new Scanner(System.in);
//
//		int numberInsert = 1;
//
//		while (numberInsert > 0 & numberInsert < 6) {
//			switch (numberInsert) {
//			case 1:
//				System.out.println("inserisci il numero dell'azione");
//				numberInsert = scan.nextInt();
//				scan.nextLine();
//			case 2:
//
//				gestioneMezzi(2, pmd, scan);
//				numberInsert = 1;
//
//			}
//		}
//		scan.close();
	}

	public static LocalDateTime formattareData(Scanner scan) {
		System.out.println("inserisci l'anno:");
		int anno = scan.nextInt();
		scan.nextLine();
		System.out.println("inserisci il mese");
		int mese = scan.nextInt();
		scan.nextLine();
		System.out.println("inserisci il giorno:");
		int giorno = scan.nextInt();
		scan.nextLine();
		System.out.println("inserisci l'ora:");
		int ora = scan.nextInt();
		scan.nextLine();
		System.out.println("inserisci i minuti:");
		int minuti = scan.nextInt();

		return LocalDateTime.of(anno, mese, giorno, ora, minuti);
	}
	
	

	public static void PuntoVendita() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("1. Aggiungi punto vendita");
		System.out.println("2. Cerca un punto vendita");
		System.out.println("3. Elimina un punto vendita");
		System.out.println("4. Visualizza il numero totale dei biglietti e abbonamenti venduti nel punto vendita dato un id");
		
		int scelta = scanner.nextInt();
		
		scanner.nextLine();
		
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		PuntiVenditaDAO pvd = new PuntiVenditaDAO(emf);
		DistributoriAutomaticiDAO da = new DistributoriAutomaticiDAO(emf);
		
		
		switch(scelta) {
			case 1: 
				
				System.out.println("Inserisci true se e' un distributore automatico oppure false se e' un rivenditore autorizzato");
				boolean distributore = scanner.nextBoolean();
				if(distributore == false) {
					
					System.out.println("Inserisci indirizzo");
					scanner.nextLine();
					String indirizzo = scanner.nextLine();
					
					System.out.println("Inserisci numeroVendite");
					Integer numeroVendite = scanner.nextInt();
					
					PuntiVendita pvendita2 = new PuntiVendita(indirizzo, numeroVendite, distributore);
					pvd.save(pvendita2);
					
				}else {
					System.out.println("Inserisci indirizzo");
					scanner.nextLine();
					String indirizzo = scanner.nextLine();
					
					System.out.println("Inserisci numeroVendite");
					Integer numeroVendite = scanner.nextInt();
					
					System.out.println("Inserisci lo stato del distributore ovvero attivo o disattivo");
					scanner.nextLine();
					String statoDistributore = scanner.nextLine();
					
					if(statoDistributore.matches("attivo")) {
						DistributoriAutomatici distributoriAuto = new DistributoriAutomatici(indirizzo, numeroVendite, Stato.attivo);
						da.save(distributoriAuto);
						
					}else if(statoDistributore.matches("disattivo")){
						DistributoriAutomatici distributoriAuto = new DistributoriAutomatici(indirizzo, numeroVendite, Stato.disattivo);
						da.save(distributoriAuto);
					}
				}
				
				break;
				
			case 2:
				System.out.println("Inserisci id");
				String idpuntoVendita = scanner.nextLine();
				System.out.println(pvd.getById(idpuntoVendita));
				break;
				
				
			case 3:
				System.out.println("Inserisci id");
				String pvEliminato = scanner.nextLine();
				pvd.FindAndDelete(pvEliminato);
				
				break;
				
			case 4:
				String totBigliettiVenduti = scanner.nextLine();
				pvd.getTicketsBySalesPoint(totBigliettiVenduti);
				
				break;
				
			default:
				System.out.println("Scelta non valida");
				break;
		}
		scanner.close();

	}
}
