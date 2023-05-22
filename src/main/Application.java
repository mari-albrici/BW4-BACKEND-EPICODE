package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DistributoriAutomaticiDAO;
import dao.RivenditoriAutorizzatiDAO;
import entities.DistributoriAutomatici;
import entities.RivenditoriAutorizzati;
import entities.Stato;
import lombok.extern.slf4j.Slf4j;
import utils.JPAUtil;
@Slf4j
public class Application {
	private static EntityManagerFactory emf =  JPAUtil.getEntityManagerFactory();   
	
	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		DistributoriAutomaticiDAO da = new DistributoriAutomaticiDAO(em);
		RivenditoriAutorizzatiDAO ra = new RivenditoriAutorizzatiDAO(em);
		
		DistributoriAutomatici primoDis = new DistributoriAutomatici("via costantino 25", Stato.attivo);
		DistributoriAutomatici secondoDis = new DistributoriAutomatici("via Giacomo Matteotti 10", Stato.disattivo);
		
		RivenditoriAutorizzati primoRiv = new RivenditoriAutorizzati("via Agrippa");
		
		da.save(primoDis);
		da.save(secondoDis);
		ra.save(primoRiv);
		
		em.close();
		emf.close();
	}
}
