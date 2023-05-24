package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

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
		BigliettoDAO bd = new BigliettoDAO(emf);
		AbbonamentoDAO ad = new AbbonamentoDAO(emf);

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
		
		ParcoMezzi mezzoUno = pmd.getMezzo("e4d6336c-5886-4a22-bdd5-40d844066dbc");

		
		Biglietto biglietto1 = new Biglietto(LocalDate.of(2023, 05, 22), true, pvendita1, true, mezzoUno, LocalDate.of(2023, 05, 23));
		//tvd.saveBiglietto(biglietto1);
		
		Biglietto biglietto2 = new Biglietto(LocalDate.of(2023, 04, 27), true, pvendita1, mezzoUno);
//		tvd.saveBiglietto(biglietto2);
	
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
				log.info("1. BIGLIETTI");
				log.info("2. ABBONAMENTI");
				int action = input.nextInt();
				switch(action) {
					case 1: 
						log.info("1. BIGLIETTI");
						break;
					case 2: log.info("2. ABBONAMENTI");
						break;
					default: 
						System.out.println("Action not available. Please choose a number between 1 and 2 or type 0 to exit the program.");
					input.close();
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

}
