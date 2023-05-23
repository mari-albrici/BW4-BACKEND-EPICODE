package dao;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import entities.Abbonamento;
import entities.Tessera;
import entities.TitoliDiViaggio;

public class TitoliDiViaggioDAO {

	private EntityManagerFactory emf;

	public TitoliDiViaggioDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public void save(TitoliDiViaggio tv) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(tv);
		transaction.commit(); 
		System.out.println("Titolo di viaggio creato!");
		em.close();
	}
	
	public boolean checkValiditaAbbonamento(Tessera numeroTessera) {
		EntityManager em = emf.createEntityManager();
		Abbonamento foundAbbonamento = em.find(Abbonamento.class, numeroTessera);
		em.close();
		if(foundAbbonamento.getDataScadenza().isBefore(LocalDate.now()) ) {
			System.out.println("ABBONAMENTO VALIDO");
			return true;
		} else {
			System.out.println("ABBONAMENTO NON VALIDO");
			return false;
		}
	}
	
}
