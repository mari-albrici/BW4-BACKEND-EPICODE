package main;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.Periodicità;
import entities.Abbonamento;
import dao.TitoliDiViaggioDAO;
import utils.JPAUtil;

public class Application {

	private static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	
	public static Logger logger = (Logger) LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		
		TitoliDiViaggioDAO tvd = new TitoliDiViaggioDAO(em);
		
		Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2023, 01, 01), true, Periodicità.MENSILE, LocalDate.of(2023, 01, 31), 12345678);
		
		tvd.save(abbonamento1);
	}
}
