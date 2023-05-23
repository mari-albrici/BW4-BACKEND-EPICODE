package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Abbonamento;
import entities.Biglietto;
import entities.Tessera;

public class TitoliDiViaggioDAO {

	private EntityManagerFactory emf;

	public TitoliDiViaggioDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void saveBiglietto(Biglietto b) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(b);
		transaction.commit();
		System.out.println("Titolo di viaggio creato!");
		em.close();
	}

	public List<Biglietto> bigliettiVendutiPerTempo(LocalDate da, LocalDate a) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createQuery("SELECT b FROM Biglietto b WHERE b.dataEmissione>= :from AND  b.dataEmissione<= :to");
		q.setParameter("from", da);
		q.setParameter("to", a);

		List<Biglietto> results = q.getResultList();
		transaction.commit();
		em.close();
		return results;
	}

	public void saveAbbonamento(Abbonamento a) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(a);
		transaction.commit();
		System.out.println("Titolo di viaggio creato!");
		em.close();
	}

	public boolean checkValiditaAbbonamento(Tessera numeroTessera) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT a FROM Abbonamento a WHERE a.numeroTessera = :numeroTessera");

		q.setParameter("numeroTessera", numeroTessera);

		Abbonamento foundAbbonamento = (Abbonamento) q.getSingleResult();

		em.close();

		if (foundAbbonamento.getDataScadenza().isBefore(LocalDate.now())) {
			System.out.println("ABBONAMENTO VALIDO");
			return true;
		} else {
			System.out.println("ABBONAMENTO NON VALIDO");
			return false;
		}
	}

}
