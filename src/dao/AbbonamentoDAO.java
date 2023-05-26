package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Abbonamento;
import entities.Utente;
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

	public List<Abbonamento> getAbbonamentiPerTipo(Periodicita periodicita) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Abbonamento> query = em
				.createQuery("SELECT a FROM Abbonamento a WHERE a.periodicita >= :periodicita", Abbonamento.class);
		query.setParameter("periodicita", periodicita);

		return query.getResultList();
	}

	public List<Abbonamento> getAbbonamentiPerUtente(String nome, String cognome) {
		EntityManager em = emf.createEntityManager();

		TypedQuery<Utente> queryOne = em.createQuery(
				"SELECT u FROM Utente u WHERE LOWER(u.nome) LIKE LOWER(:nome) AND LOWER(u.cognome) LIKE LOWER(:cognome)",
				Utente.class);
		queryOne.setParameter("nome", "%" + nome + "%");
		queryOne.setParameter("cognome", "%" + cognome + "%");

		Utente utente = queryOne.getSingleResult();

		TypedQuery<Abbonamento> query = em.createQuery("SELECT a FROM Abbonamento a WHERE a.numeroTessera >= :tessera",
				Abbonamento.class);
		query.setParameter("tessera", utente.getTessera());

		return query.getResultList();
	}

	public List<Abbonamento> getStoricoAbbonamentiPerNumeroTessera(Long numeroTessera) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Abbonamento> query = em.createQuery(
				"SELECT a FROM Abbonamento a WHERE a.numeroTessera.id = :numeroTessera", Abbonamento.class);
		query.setParameter("numeroTessera", numeroTessera);
		return query.getResultList();
	}

	public List<Abbonamento> getStoricoAbbonamentiPerUtente(Long idUtente) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Abbonamento> query = em.createQuery(
				"SELECT a FROM Abbonamento a WHERE a.numeroTessera.utenti.id = :idUtente", Abbonamento.class);
		query.setParameter("idUtente", idUtente);
		return query.getResultList();
	}

}
