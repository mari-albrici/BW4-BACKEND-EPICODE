package entities;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tessere")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name="tessere.findAll", query = "SELECT t FROM Tessera t")
@NamedQuery(name="findTesseraById", query = "SELECT t FROM Tessera t WHERE numero_tessera = :id")
@SequenceGenerator(name= "tessera_sequence", sequenceName = "tessera_sequence", initialValue = 1000, allocationSize = 0)
public class Tessera {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tessera_sequence")
	@Column(name = "numero_tessera")
	private Long id;
	
	@OneToOne(mappedBy= "tessera")
	private Utente utenti;
	
	@OneToOne(mappedBy= "numeroTessera", cascade = CascadeType.ALL)
	private Abbonamento abbonamento;
	
	@Column(nullable = false)
	private LocalDate data_creazione;
	
	@Column(nullable = false)
	private LocalDate data_scadenza;


	public Tessera(LocalDate data_creazione) {
		super();
		this.data_creazione = data_creazione;
		this.data_scadenza = data_creazione.plusYears(1);
	}

	public void setData_creazione(LocalDate data_creazione) {
		this.data_creazione = data_creazione;
		this.data_scadenza = data_creazione.plusYears(1);
	}


	@Override
	public String toString() {
		return "Tessera [getId()=" + getId() + ", getData_creazione()="
				+ getData_creazione()+ ", getData_scadenza()="
				+ getData_scadenza() + "]";
	}
	
}