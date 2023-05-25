package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Abbonamento;
import entities.enums.Periodicita;

public class AbbonamentoDAO {
	private EntityManagerFactory emf;

	public AbbonamentoDAO(EntityManagerFactory entityManager) {
		this.emf = entityManager;
	}

	public List<Abbonamento> getAbbonamentiEmessiInPeriodo(LocalDate dataInizio, LocalDate dataFine) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Abbonamento> query = em.createQuery(
				"SELECT a FROM Abbonamento a WHERE a.dataEmissione >= :dataInizio AND a.dataEmissione <= :dataFine",
				Abbonamento.class);
		query.setParameter("dataInizio", dataInizio);
		query.setParameter("dataFine", dataFine);

		return query.getResultList();
	}
	
	public List<Abbonamento> getAbbonamentiPerTipo(Periodicita periodicita){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Abbonamento> query = em.createQuery(
				"SELECT a FROM Abbonamento a WHERE a.periodicita >= :periodicita",
				Abbonamento.class);
		query.setParameter("periodicita", periodicita);

		return query.getResultList();
	}
}
