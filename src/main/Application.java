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
		//ud.save(utente1);
	
		Tessera tessera1 = new Tessera(LocalDate.of(2023, 05, 02));
		//td.save(tessera1);
		
		// **** COLLEGAMENTO TESSERA A UTENTE ****
		//ud.collegaTessera(1, tessera1);
		
		Tratta tratta1 = new Tratta("Piazza Dante", "Piazza Leopardi", "45", "15");
		//pmd.salvaTratta(tratta1);	
		
		Tratta trattaUno = pmd.getTratta("7c6429fe-a518-44d1-9969-523e4f407aa5");
	
		ParcoMezzi mezzo1 = new ParcoMezzi(stato_parcoMezzi.servizio, 75, trattaUno, LocalDateTime.of(2023, 05, 23, 10, 26), LocalDateTime.of(2023, 05, 23, 12, 26), 2, 1, TipoDiMezzo.Autobus);
		//pmd.salvaMezzo(mezzo1);

		PuntiVendita pvendita1 = new PuntiVendita("Via Verdi, 18", 1, false);
		//pvd.save(pvendita1);
		
		//pvd.getById("1132b40d-f98a-4ce5-860a-34cacf61385e");
		
		
		ParcoMezzi mezzoUno = pmd.getMezzo("82be1454-1136-4a2f-b729-c971423fec35");
		
		Biglietto biglietto1 = new Biglietto(LocalDate.of(2023, 05, 22), true, pvendita1, true, mezzoUno, LocalDate.of(2023, 05, 23));
		//tvd.saveBiglietto(biglietto1);
		
	
		Tessera tesseraUno = td.getById(1004);
		
		Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2023, 03, 10), true, pvendita1, Periodicità.SETTIMANALE, LocalDate.of(2023, 03, 10).plusDays(7), tesseraUno);
		//tvd.saveAbbonamento(abbonamento1);
	
		PeriodoManutenzione manutenzione = new PeriodoManutenzione(mezzoUno, LocalDate.of(2022, 12, 01), LocalDate.of(2022, 12, 10));
		//pmd.savePeriodoManutenzione(manutenzione);
		
		PeriodoServizi servizio = new PeriodoServizi(mezzoUno, LocalDate.of(2022, 12, 10), LocalDate.of(2023, 12, 01));
		//pmd.savePeriodoServizio(servizio);
		
		//System.out.println(mezzoUno.getPeriodoManutenzione());
		//System.out.println(mezzoUno.getPeriodoServizio());
		
		//tvd.checkValiditaAbbonamento(tesseraUno);

		
		//pvd.getTicketsBySalesPoint("88db3b5f-e0f3-4341-b933-8d3cdeae2636");
		

		//tvd.bigliettiVendutiPerTempo(LocalDate.of(2023, 05, 10), LocalDate.of(2023, 05, 26)).stream().forEach(b -> log.info(b.toString()));
		
		//mezzoUno.getBiglietti().stream().forEach(b -> log.info(b.toString()));
	
		PuntoVendita();
		
		
		emf.close();
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
