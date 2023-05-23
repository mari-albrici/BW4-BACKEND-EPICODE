package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;
import utils.JPAUtil;
import entities.Utente;
import entities.enums.TipoDiMezzo;
import entities.enums.stato_parcoMezzi;
import entities.ParcoMezzi;
import entities.Tessera;
import entities.Tratta;
import entities.Biglietto;
import entities.Abbonamento;
import entities.PuntiVendita;

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
		
		Tessera tessera1 = new Tessera(LocalDate.of(2023, 05, 02));
		
		// **** COLLEGAMENTO TESSERA A UTENTE ****
//		ud.collegaTessera(1, tessera1);
		
		Tratta tratta1 = new Tratta("Piazza Dante", "Piazza Leopardi", "45", "15");
		
		Tratta trattaUno = pmd.getTratta("b5168d2c-eb24-41ca-92df-c854a35d2c40");
		
		ParcoMezzi mezzo1 = new ParcoMezzi(stato_parcoMezzi.servizio, 75, trattaUno, LocalDateTime.of(2023, 05, 23, 10, 26), LocalDateTime.of(2023, 05, 23, 12, 26), 2, 1, TipoDiMezzo.Autobus);

		// *** TUTTO BENE FINO A QUI ***
		
		
		PuntiVendita pvendita1 = new PuntiVendita("Via Verdi, 18", 1, false);
		pvd.getById("af462838-aff1-4321-8073-8da44891eb81");
		
		ParcoMezzi mezzoUno = pmd.getMezzo("55e2d841-2a6a-4a82-9015-8a9992f008ce");
		
		Biglietto biglietto1 = new Biglietto(LocalDate.of(2023, 05, 22), true, pvendita1, true, mezzoUno, LocalDate.of(2023, 05, 23));

//		Abbonamento abbonamento1 = new Abbonamento()
				
		emf.close();
	}

}
