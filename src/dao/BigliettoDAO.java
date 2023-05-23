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
        TypedQuery<Long> query = ((EntityManager) emf).createQuery(
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
//*************PROVA DA INSERIRE NEL MAIN*****
//public class Main {
//    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit-name");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        BigliettoDAO bigliettoDAO = new BigliettoDAO(entityManager);
//
//        
//        LocalDate dataInizio = LocalDate.of(2023, 1, 1);
//        LocalDate dataFine = LocalDate.of(2023, 12, 31);
//
//       
//        int numeroBigliettiVidimati = bigliettoDAO.contaBigliettiVidimatiInPeriodo(dataInizio, dataFine);
//
//       
//        System.out.println("Numero di biglietti vidimati nel periodo: " + numeroBigliettiVidimati);
//
//        
//        entityManager.close();
//        entityManagerFactory.close();
//    }
//}

