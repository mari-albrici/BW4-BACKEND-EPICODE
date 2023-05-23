package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.JPAUtil;
import entities.Tessera;
import entities.Utente;

public class UtenteDAO {

	private static final Logger logger = LoggerFactory.getLogger(UtenteDAO.class);

	public void save(Utente a) {

		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			t.begin();
			em.persist(a);
			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public Utente getById(long id) {

		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Utente p = em.find(Utente.class, id);
			return p;

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public void delete(int id) {

		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			Utente p = em.find(Utente.class, id);

			t.begin();
			em.remove(p);
			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}



	public void collegaTessera(long idUtente, Tessera tess) {

		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			t.begin();
			Utente utente = this.getById(idUtente);
			
			utente.setTessera(tess);

			t.commit();
			
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

}
