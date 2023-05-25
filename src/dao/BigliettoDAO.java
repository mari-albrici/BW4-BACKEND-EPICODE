package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Biglietto;
import utils.JPAUtil;

public class BigliettoDAO {
	private EntityManagerFactory emf;

	public BigliettoDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	// Costruttore

	public List<Biglietto> contaBigliettiVidimatiInPeriodo(LocalDate dataInizio, LocalDate dataFine) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT b FROM Biglietto b WHERE b.vidimato = true "
				+ "AND b.dataVidimazione >= :dataInizio AND b.dataVidimazione <= :dataFine");
		query.setParameter("dataInizio", dataInizio);
		query.setParameter("dataFine", dataFine);

		List<Biglietto> count = query.getResultList();
		return count;
	}

	public List<Biglietto> getAllBiglietti() {
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query query = em.createQuery("SELECT b FROM Biglietto b");
		List<Biglietto> risposta = query.getResultList();
		t.commit();
		em.close();
		return risposta;
	}
}
