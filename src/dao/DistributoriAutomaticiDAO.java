package dao;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import entities.DistributoriAutomatici;

public class DistributoriAutomaticiDAO {
	
	
	private EntityManagerFactory emf;

	public DistributoriAutomaticiDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	
	//metodo save
	public void save(DistributoriAutomatici d) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();//partono le operazioni che voglio fare nel database
		em.persist(d);//salvera nel database cio che passo
		transaction.commit();//termina la transazione ed effettua il flush del oggetto
		System.out.println("Distributore automatico salvato correttamente");
	}
	
	//metodo getByid
	public DistributoriAutomatici getById(String id) {
		EntityManager em = emf.createEntityManager();
		DistributoriAutomatici found = em.find(DistributoriAutomatici.class, UUID.fromString(id));
		return found;
	}
	
	//metodo delete
	public void FindAndDelete(String id) {
		EntityManager em = emf.createEntityManager();
		DistributoriAutomatici found = em.find(DistributoriAutomatici.class, UUID.fromString(id));
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			System.out.println("Distributore Automatico con id " + id + " eliminato!");
		}else {
			System.out.println("id non trovato");
		}
	}
}
