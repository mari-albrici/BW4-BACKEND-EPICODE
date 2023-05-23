package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;
import utils.JPAUtil;
import entities.Utente;
import entities.enums.Periodicità;
import entities.enums.TipoDiMezzo;
import entities.enums.stato_parcoMezzi;
import entities.ParcoMezzi;
import entities.Tessera;
import entities.Tratta;
import entities.Biglietto;
import entities.Abbonamento;
import entities.PuntiVendita;
import entities.PeriodoManutenzione;
import entities.PeriodoServizi;

import dao.UtenteDAO;
import dao.TesseraDAO;
import dao.ParcoMezziDAO;
import dao.PuntiVenditaDAO;
import dao.TitoliDiViaggioDAO;

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
//		ud.save(utente1);
		
		Tessera tessera1 = new Tessera(LocalDate.of(2023, 05, 02));
//		td.save(tessera1);
		
		// **** COLLEGAMENTO TESSERA A UTENTE ****
//		ud.collegaTessera(1, tessera1);
		
		Tratta tratta1 = new Tratta("Piazza Dante", "Piazza Leopardi", "45", "15");
//		pmd.salvaTratta(tratta1);	
		
		Tratta trattaUno = pmd.getTratta("7c6429fe-a518-44d1-9969-523e4f407aa5");
		
		ParcoMezzi mezzo1 = new ParcoMezzi(stato_parcoMezzi.servizio, 75, trattaUno, LocalDateTime.of(2023, 05, 23, 10, 26), LocalDateTime.of(2023, 05, 23, 12, 26), 2, 1, TipoDiMezzo.Autobus);
//		pmd.salvaMezzo(mezzo1);

		PuntiVendita pvendita1 = new PuntiVendita("Via Verdi, 18", 1, false);
//		pvd.save(pvendita1);
		
		pvd.getById("1132b40d-f98a-4ce5-860a-34cacf61385e");
		
		ParcoMezzi mezzoUno = pmd.getMezzo("e4d6336c-5886-4a22-bdd5-40d844066dbc");
		
		Biglietto biglietto1 = new Biglietto(LocalDate.of(2023, 05, 22), true, pvendita1, true, mezzoUno, LocalDate.of(2023, 05, 23));
//		tvd.saveBiglietto(biglietto1);
		
		
		Tessera tesseraUno = td.getById(1004);
		
		Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2023, 03, 10), true, pvendita1, Periodicità.SETTIMANALE, LocalDate.of(2023, 03, 10).plusDays(7), tesseraUno);
//		tvd.saveAbbonamento(abbonamento1);
		
		
		PeriodoManutenzione manutenzione = new PeriodoManutenzione(mezzoUno, LocalDate.of(2022, 12, 01), LocalDate.of(2022, 12, 10));
//		pmd.savePeriodoManutenzione(manutenzione);
		
		PeriodoServizi servizio = new PeriodoServizi(mezzoUno, LocalDate.of(2022, 12, 10), LocalDate.of(2023, 12, 01));
//		pmd.savePeriodoServizio(servizio);
		
//		System.out.println(mezzoUno.getPeriodoManutenzione());
//		System.out.println(mezzoUno.getPeriodoServizio());
		
		
//		tvd.checkValiditaAbbonamento(tesseraUno);
		
		pvd.getTicketsBySalesPoint("88db3b5f-e0f3-4341-b933-8d3cdeae2636");
		
		emf.close();
	}

}
