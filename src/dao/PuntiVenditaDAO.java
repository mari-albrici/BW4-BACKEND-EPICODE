package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.PuntiVendita;

public class PuntiVenditaDAO {
	private final EntityManager em;
	
	public PuntiVenditaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(PuntiVendita n) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();//partono le operazioni che voglio fare nel database
		em.persist(n);//salvera nel database cio che passo
		transaction.commit();//termina la transazione ed effettua il flush del oggetto
		System.out.println("Evento salvato");
	}

	public PuntiVendita getById(String id) {
		PuntiVendita found = em.find(PuntiVendita.class, UUID.fromString(id));
		return found;
	}
	
	public void FindAndDelete(String id) {
		PuntiVendita found = em.find(PuntiVendita.class, UUID.fromString(id));
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			System.out.println("Evento con id " + id + " eliminato!");
		}else {
			System.out.println("id non trovato");
		}
	}
}
