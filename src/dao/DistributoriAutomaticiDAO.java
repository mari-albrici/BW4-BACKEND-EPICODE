package dao;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.DistributoriAutomatici;
import entities.PuntiVendita;
import entities.Stato;

public class DistributoriAutomaticiDAO {
	
	
	private EntityManagerFactory emf;

	public DistributoriAutomaticiDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	
	//metodo save
	public void save(DistributoriAutomatici d) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(d);
		transaction.commit();
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
	public List<DistributoriAutomatici> distributoriAutomaticiDisattiviOAttivi(Stato stato){
		EntityManager em = emf.createEntityManager();
		TypedQuery<DistributoriAutomatici> query = em.createNamedQuery("DistributoriAutomatici.distributoriAutomaticiDisattiviOAttivi", DistributoriAutomatici.class);
		query.setParameter("stato", stato);
		return query.getResultList();
	}
}
