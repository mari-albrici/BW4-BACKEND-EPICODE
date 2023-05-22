package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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

}
