package dao;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import entities.DistributoriAutomatici;

public class DistributoriAutomaticiDAO {
	private final EntityManager em;
	
	public DistributoriAutomaticiDAO(EntityManager em) {
		this.em = em;
	}
	
	//metodo save
	public void save(DistributoriAutomatici d) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();//partono le operazioni che voglio fare nel database
		em.persist(d);//salvera nel database cio che passo
		transaction.commit();//termina la transazione ed effettua il flush del oggetto
		System.out.println("Evento salvato");
	}
	
	//metodo getByid
	public DistributoriAutomatici getById(String id) {
		DistributoriAutomatici found = em.find(DistributoriAutomatici.class, UUID.fromString(id));
		return found;
	}
	
	//metodo delete
	public void FindAndDelete(String id) {
		DistributoriAutomatici found = em.find(DistributoriAutomatici.class, UUID.fromString(id));
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
