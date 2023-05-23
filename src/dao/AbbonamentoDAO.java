package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Abbonamento;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO {
    private EntityManager emf;

    public AbbonamentoDAO(EntityManager entityManager) {
        this.emf = entityManager;
    }

    public List<Abbonamento> getAbbonamentiEmessiInPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Abbonamento> query = emf.createQuery(
            "SELECT a FROM Abbonamento a WHERE a.dataEmissione >= :dataInizio AND a.dataEmissione <= :dataFine",
            Abbonamento.class
        );
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);

        return query.getResultList();
    }
}
