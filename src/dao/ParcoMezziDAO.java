package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.ParcoMezzi;
import entities.PeriodoManutenzione;
import entities.PeriodoServizi;
import entities.Tratta;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParcoMezziDAO {

	private EntityManagerFactory emf;

	public ParcoMezziDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void salvaMezzo(ParcoMezzi e) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(e);
		t.commit();
		em.close();
		log.info("mezzo salvato");
	}

	public void salvaTratta(Tratta e) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(e);
		t.commit();
		em.close();
		log.info("tratta salvata");
	}

	public void savePeriodoServizio(PeriodoServizi e) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(e);
		t.commit();
		em.close();
		log.info("periodo di servizio salvato");
	}

	public void savePeriodoManutenzione(PeriodoManutenzione e) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(e);
		t.commit();
		em.close();
		log.info("periodo di manutenzione salvato");
	}

	public ParcoMezzi getMezzo(String id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		ParcoMezzi risposta = em.find(ParcoMezzi.class, UUID.fromString(id));
		t.commit();
		em.close();
		return risposta;
	}

	public Tratta getTratta(String tratta) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Tratta risposta = em.find(Tratta.class, UUID.fromString(tratta));
		t.commit();
		em.close();
		return risposta;
	}

	public List<ParcoMezzi> getAllMezzi() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query query = em.createQuery("SELECT m FROM ParcoMezzi m");
		List<ParcoMezzi> risposta = query.getResultList();
		t.commit();
		em.close();
		return risposta;
	}

	public List<Tratta> getAllTratte() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query query = em.createQuery("SELECT t FROM Tratta t");
		List<Tratta> risposta = query.getResultList();
		t.commit();
		em.close();
		return risposta;
	}

	public List<ParcoMezzi> findMezzoMoreManutenzione() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ParcoMezzi> query = em.createNamedQuery("ParcoMezzi.findMezzoMoreManutenzione", ParcoMezzi.class);
		// query.setMaxResults(1);
		return query.getResultList();
	}

	public UUID getTrattaPiuUtilizzata() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();

		Query query = em.createQuery(
				"SELECT p.tratta.id, COUNT(p) as utilizzo FROM ParcoMezzi p GROUP BY p.tratta.id ORDER BY utilizzo DESC");
		query.setMaxResults(1);

		Object[] result = (Object[]) query.getSingleResult();
		UUID trattaId = (UUID) result[0];
		Long numerovoltepercorrenzatratta = (Long) result[1];

		t.commit();
		em.close();

		System.out.println("Tratta ID: " + trattaId);
		System.out.println("Numero di volte percorse: " + numerovoltepercorrenzatratta);

		return trattaId;
	}

	public List<ParcoMezzi> getMezziPerTratta(UUID trattaId) {
		EntityManager em = emf.createEntityManager();
		String queryStr = "SELECT p FROM ParcoMezzi p WHERE p.tratta.id = :trattaId";
		Query query = em.createQuery(queryStr, ParcoMezzi.class);
		query.setParameter("trattaId", trattaId);
		return query.getResultList();
	}

}
