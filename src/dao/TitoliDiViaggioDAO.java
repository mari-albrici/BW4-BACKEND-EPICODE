package dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Abbonamento;
import entities.TitoliDiViaggio;

public class TitoliDiViaggioDAO {

	private final EntityManager em;

	public TitoliDiViaggioDAO(EntityManager em) {
		this.em = em;
	}
	
	public void save(TitoliDiViaggio tv) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(tv);
		transaction.commit(); 
		System.out.println("Titolo creato!");
	}
	
	public boolean checkValiditaAbbonamento(UUID numeroTessera) {
		Abbonamento foundAbbonamento = em.find(Abbonamento.class, numeroTessera);
		if(foundAbbonamento.getDataScadenza().isBefore(LocalDate.now()) ) {
			System.out.println("ABBONAMENTO VALIDO");
			return true;
		} else {
			System.out.println("ABBONAMENTO NON VALIDO");
			return false;
		}
	}
}
