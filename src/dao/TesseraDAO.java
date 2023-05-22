package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.JPAUtil;

import entities.Tessera;

public class TesseraDAO {

	private static final Logger logger = LoggerFactory.getLogger(TesseraDAO.class);

	public static void save(Tessera a) {

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

	public static Tessera getById(int id) {

		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Tessera p = em.find(Tessera.class, id);
			return p;

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static void delete(int id) {

		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			Tessera p = em.find(Tessera.class, id);

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






}
