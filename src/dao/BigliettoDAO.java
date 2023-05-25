package dao;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class BigliettoDAO {
	private EntityManagerFactory emf;

    public BigliettoDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
    // Costruttore

    public int contaBigliettiVidimatiInPeriodo(LocalDate dataInizio, LocalDate dataFine) {
    	EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(b) FROM Biglietto b WHERE b.vidimato = true " +
            "AND b.dataVidimazione >= :dataInizio AND b.dataVidimazione <= :dataFine",
            Long.class
        );
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);

        Long count = query.getSingleResult();
        return count.intValue();
    }

    
}

