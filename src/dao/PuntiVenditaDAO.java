package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import entities.PuntiVendita;

public class PuntiVenditaDAO {

	private EntityManagerFactory emf;

	public PuntiVenditaDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void save(PuntiVendita n) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();//partono le operazioni che voglio fare nel database
		em.persist(n);//salvera nel database cio che passo
		transaction.commit();//termina la transazione ed effettua il flush del oggetto
		System.out.println("Punto vendita aggiunto correttamente");
		em.close();
	}

	public PuntiVendita getById(String id) {
		EntityManager em = emf.createEntityManager();
		PuntiVendita found = em.find(PuntiVendita.class, UUID.fromString(id));
		em.close();
		return found;
	}
	
	public void FindAndDelete(String id) {
		EntityManager em = emf.createEntityManager();
		PuntiVendita found = em.find(PuntiVendita.class, UUID.fromString(id));
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			System.out.println("Punto vendita con id " + id + " eliminato!");
		}else {
			System.out.println("id non trovato");
		}
		em.close();
	}
}
