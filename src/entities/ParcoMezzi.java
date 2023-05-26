package entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import entities.enums.TipoDiMezzo;
import entities.enums.stato_parcoMezzi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@NamedQuery(name = "ParcoMezzi.findMezzoMoreManutenzione", query = "SELECT pm FROM ParcoMezzi pm "
		+ "ORDER BY SIZE(pm.periodoManutenzione) DESC ")

public class ParcoMezzi {

	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private stato_parcoMezzi stato;

	@OneToMany(mappedBy = "mezzo", fetch = FetchType.EAGER)
	private List<PeriodoServizi> periodoServizio;
	@OneToMany(mappedBy = "mezzo", fetch = FetchType.EAGER)
	private Set<PeriodoManutenzione> periodoManutenzione;
	private int capienza;

	@ManyToOne
	private Tratta tratta;

	private LocalDateTime orarioDiPartenza;
	private LocalDateTime orarioDiArrivo;
	private double tempoImpiegato;
	private int numeroVoltePercorrenzaTratta;

	@Enumerated(EnumType.STRING)
	private TipoDiMezzo tipoDiMezzo;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mezzo")
	private Set<Biglietto> biglietti;

	public ParcoMezzi(stato_parcoMezzi stato, int capienza, Tratta tratta, LocalDateTime orarioDiPartenza,
			LocalDateTime orarioDiArrivo, double tempoImpiegato, int numeroVoltePercorrenzaTratta,
			TipoDiMezzo tipoDiMezzo) {

		this.stato = stato;
		this.capienza = capienza;
		this.tratta = tratta;
		this.orarioDiPartenza = orarioDiPartenza;
		this.orarioDiArrivo = orarioDiArrivo;
		this.tempoImpiegato = tempoImpiegato;
		this.numeroVoltePercorrenzaTratta = numeroVoltePercorrenzaTratta;
		this.tipoDiMezzo = tipoDiMezzo;
	}

	@Override
	public String toString() {
		return "ParcoMezzi [id=" + id + ", stato=" + stato + ", capienza=" + capienza + ", orarioDiPartenza="
				+ orarioDiPartenza + ", orarioDiArrivo=" + orarioDiArrivo + ", tempoImpiegato=" + tempoImpiegato
				+ ", numeroVoltePercorrenzaTratta=" + numeroVoltePercorrenzaTratta + ", tipoDiMezzo=" + tipoDiMezzo
				+ "]";
	}

}
