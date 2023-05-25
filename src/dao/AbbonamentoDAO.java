package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Abbonamento;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO {
    private EntityManagerFactory emf;

    public AbbonamentoDAO(EntityManagerFactory entityManager) {
        this.emf = entityManager;
    }

    public List<Abbonamento> getAbbonamentiEmessiInPeriodo(LocalDate dataInizio, LocalDate dataFine) {
    	EntityManager em = emf.createEntityManager();
        TypedQuery<Abbonamento> query = em.createQuery(
            "SELECT a FROM Abbonamento a WHERE a.dataEmissione >= :dataInizio AND a.dataEmissione <= :dataFine",
            Abbonamento.class
        );
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);

        return query.getResultList();
    }
    
    public List<Abbonamento> getStoricoAbbonamentiPerNumeroTessera(Long numeroTessera) {
        TypedQuery<Abbonamento> query = ((EntityManager) emf).createQuery(
            "SELECT a FROM Abbonamento a WHERE a.numeroTessera.id = :numeroTessera", Abbonamento.class);
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }
}




