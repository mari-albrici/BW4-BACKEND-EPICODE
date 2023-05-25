package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.PuntiVendita;

public class PuntiVenditaDAO {

	private EntityManagerFactory emf;

	public PuntiVenditaDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void save(PuntiVendita n) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(n);
		transaction.commit();
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
		} else {
			System.out.println("id non trovato");
		}
		em.close();
	}

	public void getTicketsBySalesPoint(String id) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT COUNT(*) FROM TitoliDiViaggio t WHERE t.puntoVendita = :puntoVendita");

		PuntiVendita selezionato = this.getById(id);

		q.setParameter("puntoVendita", selezionato);

		long totalTickets = q.getResultList().size();

		System.out.println("Il numero di biglietti venduto nel punto vendita selezionato è: " + totalTickets);
		em.close();
	}

	public List<PuntiVendita> getAllPuntiVendita() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query query = em.createQuery("SELECT p FROM PuntiVendita p");
		List<PuntiVendita> risposta = query.getResultList();
		t.commit();
		em.close();
		return risposta;
	}
	
	public List<PuntiVendita> findVenditeMax(){
		EntityManager em = emf.createEntityManager();
		TypedQuery<PuntiVendita> query = em.createNamedQuery("PuntiVendita.findVenditeMax", PuntiVendita.class);
		return query.getResultList();
	}
}


