package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManagerFactory;

import dao.ParcoMezziDAO;
import dao.PuntiVenditaDAO;
import dao.TesseraDAO;
import dao.TitoliDiViaggioDAO;
import dao.UtenteDAO;
import dao.BigliettoDAO;
import dao.AbbonamentoDAO;
import entities.Abbonamento;
import entities.Biglietto;
import entities.ParcoMezzi;
import entities.PeriodoManutenzione;
import entities.PeriodoServizi;
import entities.PuntiVendita;
import entities.Tessera;
import entities.Tratta;
import entities.Utente;
import entities.enums.Periodicita;
import entities.enums.TipoDiMezzo;
import entities.enums.stato_parcoMezzi;
import lombok.extern.slf4j.Slf4j;
import utils.JPAUtil;

@Slf4j
public class Application {
	
		static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

		static UtenteDAO ud = new UtenteDAO();
		static TesseraDAO td = new TesseraDAO();
		static ParcoMezziDAO pmd = new ParcoMezziDAO(emf);
		static PuntiVenditaDAO pvd = new PuntiVenditaDAO(emf);
		static TitoliDiViaggioDAO tvd = new TitoliDiViaggioDAO(emf);
		static BigliettoDAO bd = new BigliettoDAO(emf);
		static AbbonamentoDAO ad = new AbbonamentoDAO(emf);

	public static void main(String[] args) {


//		Utente utente1 = new Utente("Mario", "Rossi");
		//ud.save(utente1);
	
//		Tessera tessera1 = new Tessera(LocalDate.of(2023, 05, 02));
		//td.save(tessera1);
		
		// **** COLLEGAMENTO TESSERA A UTENTE ****
		//ud.collegaTessera(1, tessera1);
		
//		Tratta tratta1 = new Tratta("Piazza Dante", "Piazza Leopardi", "45", "15");
		//pmd.salvaTratta(tratta1);	
		
//		Tratta trattaUno = pmd.getTratta("7c6429fe-a518-44d1-9969-523e4f407aa5");
	
//		ParcoMezzi mezzo1 = new ParcoMezzi(stato_parcoMezzi.servizio, 75, trattaUno, LocalDateTime.of(2023, 05, 23, 10, 26), LocalDateTime.of(2023, 05, 23, 12, 26), 2, 1, TipoDiMezzo.Autobus);
		//pmd.salvaMezzo(mezzo1);

//		PuntiVendita pvendita1 = new PuntiVendita("Via Verdi, 18", 1, false);
		//pvd.save(pvendita1);
		
		//pvd.getById("1132b40d-f98a-4ce5-860a-34cacf61385e");
		
//		ParcoMezzi mezzoUno = pmd.getMezzo("e4d6336c-5886-4a22-bdd5-40d844066dbc");

		
//		Biglietto biglietto1 = new Biglietto(LocalDate.of(2023, 05, 22), true, pvendita1, true, mezzoUno, LocalDate.of(2023, 05, 23));
		//tvd.saveBiglietto(biglietto1);
		
//		Biglietto biglietto2 = new Biglietto(LocalDate.of(2023, 04, 27), true, pvendita1, mezzoUno);
//		tvd.saveBiglietto(biglietto2);
	
//		Tessera tesseraUno = td.getById(1004);
		
//		Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2023, 03, 10), true, pvendita1, Periodicità.SETTIMANALE, LocalDate.of(2023, 03, 10).plusDays(7), tesseraUno);
		//tvd.saveAbbonamento(abbonamento1);
	
//		PeriodoManutenzione manutenzione = new PeriodoManutenzione(mezzoUno, LocalDate.of(2022, 12, 01), LocalDate.of(2022, 12, 10));
		//pmd.savePeriodoManutenzione(manutenzione);
		
//		PeriodoServizi servizio = new PeriodoServizi(mezzoUno, LocalDate.of(2022, 12, 10), LocalDate.of(2023, 12, 01));
		//pmd.savePeriodoServizio(servizio);
		
		//System.out.println(mezzoUno.getPeriodoManutenzione());
		//System.out.println(mezzoUno.getPeriodoServizio());
		
		//tvd.checkValiditaAbbonamento(tesseraUno);
		
		// ********* METODI DA CONTROLLARE *******

//		pvd.getTicketsBySalesPoint("88db3b5f-e0f3-4341-b933-8d3cdeae2636");
		
//		tvd.bigliettiVendutiPerTempo(LocalDate.of(2023, 05, 10), LocalDate.of(2023, 05, 26)).stream()
//				.forEach(b -> log.info(b.toString()));
		
//		mezzoUno.getBiglietti().stream().forEach(b -> log.info(b.toString()));
		
//		System.out.println("!!: " + bd.contaBigliettiVidimatiInPeriodo(LocalDate.of(2023, 01, 01), LocalDate.now()));
		
//		 ad.getAbbonamentiEmessiInPeriodo(LocalDate.of(2023, 01, 01), LocalDate.now()).stream()
//			.forEach(b -> log.info(b.toString()));
		
//		tvd.vidimazioneBiglietto("8f419c65-65f3-410b-a625-f48167f46c4f");
		
		
		
		
		
		// ********** INTERFACCIA - SWITCH **********
		
		Scanner input = new Scanner(System.in);
		
		int chosenAction = -1;
		
		log.info("Choose an action: ");
		
		while(chosenAction != 0) {
			
			chosenAction = input.nextInt();
			
			switch(chosenAction) {
			case 1: ;
			case 2: ;
			case 3: ;
			case 4: ;
			case 5: 
				log.info("TITOLI DI VIAGGIO. Scegliere tra: ");
				System.out.println("1. BIGLIETTI");
				System.out.println("2. ABBONAMENTI");
				int action = input.nextInt();
				switch(action) {
					case 1: 
						log.info("1. BIGLIETTI. Scegli tra: ");
						gestioneBiglietti(input);
						break;
					case 2: log.info("2. ABBONAMENTI. Scegli tra: ");
						gestioneAbbonamenti(input);
						break;
					default: 
						System.out.println("Action not available. Please choose a number between 1 and 2 or type 0 to exit the program.");
				}
				break;
			case 0: ;
			default: 
					System.out.println("Action not available. Please choose a number between 1 and 5 or type 0 to exit the program.");
					input.close();
			}
		}	

		emf.close();
	}

	private static void gestioneBiglietti(Scanner input) {
		
		System.out.println("1. CREA");
		System.out.println("2. CERCA");
		System.out.println("3. VIDIMA");
		
		int scelta;
		scelta = input.nextInt();
		switch(scelta) {
		case 1: 
			// CREA
			System.out.println("Inserire ID punto vendita");
			String pvenditaId = input.nextLine();
			PuntiVendita pvendita = pvd.getById(pvenditaId);
			System.out.println("Inserire ID mezzo");
			String mezzoId = input.nextLine();
			ParcoMezzi mezzo = pmd.getMezzo(mezzoId);
			Biglietto biglietto = new Biglietto(LocalDate.now(), true, pvendita, mezzo);
			System.out.println("Biglietto creato correttamente: " + biglietto.toString());
			break;
		case 2: 
			//CERCA
			System.out.println("Inserire ID biglietto da cercare: ");
			String bigliettoCercato = input.nextLine();
			tvd.getById(bigliettoCercato);
			System.out.println("Il biglietto cercato è: " + bigliettoCercato.toString());
			break;
		case 3: 
			//VIDIMA
			System.out.println("Inserire ID biglietto da vidimare: ");
			String bigliettoVidimare = input.nextLine();
			tvd.vidimazioneBiglietto(bigliettoVidimare);
			System.out.println("Il biglietto vidimato correttamente è: " + bigliettoVidimare.toString());
			break;
		default: 
			System.out.println("Action not available. Please choose a number between 1 and 2 or type 0 to exit the program.");
		}
		
	}
	
	private static void gestioneAbbonamenti(Scanner input) {
		
		System.out.println("1. CREA");
		System.out.println("2. CERCA per TESSERA");
		
		int scelta;
		scelta = input.nextInt();
		
		switch(scelta) {
		case 1: 
			// CREA
			LocalDate dataEmissione = LocalDate.now();
			System.out.println("Inserire ID punto vendita");
			String pvenditaId = input.nextLine();
			PuntiVendita pvendita = pvd.getById(pvenditaId);
			System.out.println("Selezionare periodi di validità: 1 per settimanale o 2 per mensile");
			int periodicitaScelta = input.nextInt();
			
			Periodicita periodicità = null;
			LocalDate dataScadenza = null;
			
			if(periodicitaScelta == 1) {
				periodicità = Periodicita.SETTIMANALE;
				dataScadenza = dataEmissione.plusDays(7);
			} else if(periodicitaScelta == 2) {
				periodicità = Periodicita.MENSILE;
				dataScadenza = dataEmissione.plusDays(30);
			}
			System.out.println("Inserire ID mezzo");
			String mezzoId = input.nextLine();
			ParcoMezzi mezzo = pmd.getMezzo(mezzoId);
			
			System.out.println("Selezionare ID tessera su cui attivare l'abbonamento: ");
			long tesseraID = input.nextInt();
			Tessera tessera = td.getById(tesseraID);
			
			Abbonamento abbonamento = new Abbonamento(dataEmissione, true, pvendita, periodicità, dataScadenza, tessera );
			System.out.println("Abbonamento creato correttamente: " + abbonamento.toString());
			break;
		case 2: 
			//CHECK VALIDITA' 
			System.out.println("Inserire ID tessera da cercare: ");
			long tesseraCercata = input.nextInt();
			Tessera tesseraC = td.getById(tesseraCercata);
			tvd.checkValiditaAbbonamento(tesseraC);
			break;
		default: 
			System.out.println("Action not available. Please choose a number between 1 and 2 or type 0 to exit the program.");
		}
	}
	


}
