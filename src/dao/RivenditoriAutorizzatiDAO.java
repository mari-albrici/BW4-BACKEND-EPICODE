package dao;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import entities.RivenditoriAutorizzati;

public class RivenditoriAutorizzatiDAO {
	private final EntityManager em;
	
	public RivenditoriAutorizzatiDAO(EntityManager em) {
		this.em = em;
	}
	
	//metodo save
	public void save(RivenditoriAutorizzati d) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();//partono le operazioni che voglio fare nel database
		em.persist(d);//salvera nel database cio che passo
		transaction.commit();//termina la transazione ed effettua il flush del oggetto
		System.out.println("Rivenditore Autorizzato salvato correttamente");
	}
	
	//metodo getByid
	public RivenditoriAutorizzati getById(String id) {
		RivenditoriAutorizzati found = em.find(RivenditoriAutorizzati.class, UUID.fromString(id));
		return found;
	}
	
	//metodo delete
	public void FindAndDelete(String id) {
		RivenditoriAutorizzati found = em.find(RivenditoriAutorizzati.class, UUID.fromString(id));
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			System.out.println("Rivenditore con id " + id + " eliminato!");
		}else {
			System.out.println("id non trovato");
		}
	}
}
