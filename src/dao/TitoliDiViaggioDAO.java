package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.TitoliDiViaggio;

public class TitoliDiViaggioDAO {

	private final EntityManager em;

	public TitoliDiViaggioDAO(EntityManager em) {
		this.em = em;
	}
	
	public void save(TitoliDiViaggio tv) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(tv);
		transaction.commit(); 
		System.out.println("Prestito salvato!");
	}
}
