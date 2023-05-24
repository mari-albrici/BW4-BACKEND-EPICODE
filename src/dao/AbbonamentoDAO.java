package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Abbonamento;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO {
    private EntityManager emf;

    public AbbonamentoDAO(EntityManager entityManager) {
        this.emf = entityManager;
    }

    public List<Abbonamento> getAbbonamentiEmessiInPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Abbonamento> query = emf.createQuery(
            "SELECT a FROM Abbonamento a WHERE a.dataEmissione >= :dataInizio AND a.dataEmissione <= :dataFine",
            Abbonamento.class
        );
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);

        return query.getResultList();
    }
}

//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        UtenteDAO utenteDAO = new UtenteDAO();
//        TesseraDAO tesseraDAO = new TesseraDAO();
//
//        while (true) {
//            System.out.println("Scegli un opzione:");
//            System.out.println("1. Crea un Utente");
//            System.out.println("2. Crea una Tessera");
//            System.out.println("3. Cerca Utente tramite ID");
//            System.out.println("4. Cerca Tessera tramite ID");
//            System.out.println("5. Cancella Utente tramite ID");
//            System.out.println("6. Cancella Tessera tramite ID");
//            System.out.println("0. Exit");
//
//            int option = scanner.nextInt();
//            scanner.nextLine(); // Consume the newline character
//
//            switch (option) {
//                case 1:
//                    System.out.println("Inserisci i dettagli per la creazione di un nuovo utente:");
//                    System.out.print("Nome: ");
//                    String nome = scanner.nextLine();
//                    System.out.print("Cognome: ");
//                    String cognome = scanner.nextLine();
//
//                    Utente utente = new Utente(nome, cognome);
//                    utenteDAO.save(utente);
//                    System.out.println("Utente creato con successo.");
//                    break;
//
//                case 2:
//                    System.out.println("Inserisci i dettagli per la creazione di una nuova tessera:");
//                    System.out.print("Data di creazione (yyyy-mm-dd): ");
//                    String dataCreazioneStr = scanner.nextLine();
//                    LocalDate dataCreazione = LocalDate.parse(dataCreazioneStr);
//
//                    Tessera tessera = new Tessera(dataCreazione);
//                    tesseraDAO.save(tessera);
//                    System.out.println("tessera creata con successo.");
//                    break;
//
//                case 3:
//                    System.out.print("Inserisci l'ID Utente: ");
//                    long utenteID = scanner.nextLong();
//                    Utente trovaUtente = utenteDAO.getById(utenteID);
//                    if (foundUser != null) {
//                        System.out.println("Utente trovato: " + trovaUtente.toString());
//                    } else {
//                        System.out.println("Utente non trovato.");
//                    }
//                    break;
//
//                case 4:
//                    System.out.print("Inserisci l'ID della tua tessera: ");
//                    long tesseraId = scanner.nextLong();
//                    Tessera trovaTessera = tesseraDAO.getById(tesseraId);
//                    if (foundCard != null) {
//                        System.out.println("Tessera Trovata: " + trovaTessera.toString());
//                    } else {
//                        System.out.println("Tessera non trovata.");
//                    }
//                    break;
//
//                case 5:
//                    System.out.print("Inserisci l'ID utente per la cancellazione: ");
//                    long idUtenteDaCancellare = scanner.nextLong();
//                    utenteDAO.delete(idUtenteDaCancellare);
//                    System.out.println("Utente cancellato con successo.");
//                    break;
//
//                case 6:
//                    System.out.print("Inserisci l'ID della tua tessera per la cancellazione: ");
//                    long IdTesseraDaCancellare = scanner.nextLong();
//                    tesseraDAO.delete(IdTesseraDaCancellare);
//                    System.out.println("Tessera cancellata con successo.");
//                    break;
//
//                case 0:
//                    System.out.println("esci...");
//                    scanner.close();
//                    System.exit(0);
//                    break;
//
//                default:
//                    System.out.println("Hai scelto un opzione sbagliata.");
//                    break;
//            }
//
//            System.out.println();
//        }
//    }
//}

