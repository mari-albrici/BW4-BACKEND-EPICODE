package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import entities.Utente;

@Entity
@NamedQuery(name = "Tessera.findAll", query = "SELECT a FROM Tessera a")
public class Tessera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate data_inizio_abbonamento;
	private LocalDate data_fine_abbonamento;
	@OneToOne
	private Utente utente;

	public Tessera() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData_inizio_abbonamento() {
		return data_inizio_abbonamento;
	}

	public void setData_inizio_abbonamento(LocalDate data_inizio_abbonamento) {
		this.data_inizio_abbonamento = data_inizio_abbonamento;
	}

	public LocalDate getData_fine_abbonamento() {
		return data_fine_abbonamento;
	}

	public void setData_fine_abbonamento(LocalDate data_fine_abbonamento) {
		this.data_fine_abbonamento = data_fine_abbonamento;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	@Override
	public String toString() {
		return "Tessera [id=" + id + ", data_inizio_abbonamento=" + data_inizio_abbonamento + ", data_fine_abbonamento="
				+ data_fine_abbonamento + ", utente=" + utente + "]";
	}
	
	
}
