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
import dao.BigliettoDAO;
import dao.AbbonamentoDAO;
import entities.Abbonamento;
import entities.Biglietto;
import entities.DistributoriAutomatici;
import entities.ParcoMezzi;
import entities.PuntiVendita;
import entities.Stato;
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

		// ********** INTERFACCIA - SWITCH **********
		
		Scanner input = new Scanner(System.in);
		
		int chosenAction = -1;
		
		log.info("Choose an action: ");
		System.out.println("1. UTENTI E TESSERE");
		System.out.println("2. TRATTE E MEZZI");
		System.out.println("3. PUNTI VENDITA");
		System.out.println("4. TITOLI DI VIAGGIO");
		System.out.println("0. ESCI");
		
		while(chosenAction != 0) {
			
			chosenAction = input.nextInt();
			
			switch(chosenAction) {
			case 1: 
				log.info("UTENI E TESSERE. Scegliere tra: ");
				gestioneUtentiTessere(input);
				break;
			case 2: 
				log.info("TRATTE E MEZZI. Scegliere tra: ");
				gestioneMezzi(chosenAction, pmd, input);
				break;
			case 3: 
				log.info("PUNTI VENDITA. Scegliere tra: ");
				PuntoVendita();
				break;
			case 4: 
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
			case 0: 
				input.close();
				System.out.println("Programma chiuso.");
				break;
			default: 
					System.out.println("Action not available. Please choose a number between 1 and 5 or type 0 to exit the program.");
					input.close();
			}
		}	

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
	
	private static void gestioneBiglietti(Scanner input) {
		
		System.out.println("1. CREA");
		System.out.println("2. CERCA");
		System.out.println("3. VIDIMA");
		
		int scelta;
		scelta = input.nextInt();
		input.nextLine();
		switch(scelta) {
		
		case 1: 
			// CREA
			System.out.println("Inserire ID mezzo");
			String mezzoId = input.nextLine();
			ParcoMezzi mezzo = pmd.getMezzo(mezzoId);
			
			System.out.println("Inserire ID punto vendita");
			String pvenditaId = input.nextLine();
			PuntiVendita pvendita = pvd.getById(pvenditaId);
			
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
		input.nextLine();
		
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
			
			System.out.println("Selezionare ID tessera su cui attivare l'abbonamento: ");
			long tesseraID = input.nextInt();
			Tessera tessera = td.getById(tesseraID);
			
			Abbonamento abbonamento = new Abbonamento(dataEmissione, true, pvendita, periodicità, dataScadenza, tessera);
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
	
	private static void gestioneUtentiTessere(Scanner input) {
		System.out.println("Scegli un opzione:");
		System.out.println("1. Crea un Utente");
      System.out.println("2. Crea una Tessera");
      System.out.println("3. Cerca Utente tramite ID");
      System.out.println("4. Cerca Tessera tramite ID");
      System.out.println("5. Cancella Utente tramite ID");
      System.out.println("6. Cancella Tessera tramite ID");
      System.out.println("0. Exit");

      int option = input.nextInt();
      input.nextLine(); // Consume the newline character

      switch (option) {
          case 1:
              System.out.println("Inserisci i dettagli per la creazione di un nuovo utente:");
              System.out.print("Nome: ");
              String nome = input.nextLine();
              System.out.print("Cognome: ");
              String cognome = input.nextLine();

              Utente utente = new Utente(nome, cognome);
              ud.save(utente);
              System.out.println("Utente creato con successo.");
              break;

          case 2:
              System.out.println("Inserisci i dettagli per la creazione di una nuova tessera:");
              System.out.print("Data di creazione (yyyy-mm-dd): ");
              String dataCreazioneStr = input.nextLine();
              LocalDate dataCreazione = LocalDate.parse(dataCreazioneStr);

              Tessera tessera = new Tessera(dataCreazione);
              td.save(tessera);
              System.out.println("tessera creata con successo.");
              break;

          case 3:
              System.out.print("Inserisci l'ID Utente: ");
              long utenteID = input.nextLong();
              Utente trovaUtente = ud.getById(utenteID);
              if (trovaUtente != null) {
                  System.out.println("Utente trovato: " + trovaUtente.toString());
              } else {
                  System.out.println("Utente non trovato.");
              }
              break;

          case 4:
              System.out.print("Inserisci l'ID della tua tessera: ");
              long tesseraId = input.nextLong();
              Tessera trovaTessera = td.getById(tesseraId);
              if (trovaTessera != null) {
                  System.out.println("Tessera Trovata: " + trovaTessera.toString());
              } else {
                  System.out.println("Tessera non trovata.");
              }
              break;

          case 5:
              System.out.print("Inserisci l'ID utente per la cancellazione: ");
              int idUtenteDaCancellare = input.nextInt();
              input.nextLine();
              ud.delete(idUtenteDaCancellare);
              System.out.println("Utente cancellato con successo.");
              break;

          case 6:
              System.out.print("Inserisci l'ID della tua tessera per la cancellazione: ");
              long IdTesseraDaCancellare = input.nextLong();
              td.delete(IdTesseraDaCancellare);
              System.out.println("Tessera cancellata con successo.");
              break;

          case 0:
              System.out.println("esci...");
              input.close();
              System.exit(0);
              break;

          default:
              System.out.println("Hai scelto un opzione sbagliata.");
              break;
      }

      System.out.println();
  
	}

}
