package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PeriodoManutenzione {
	@Override
	public String toString() {
		return "PeriodoManutenzione [id=" + id + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + "]";
	}

	public PeriodoManutenzione(ParcoMezzi mezzo, LocalDate dataInizio, LocalDate dataFine) {

		this.mezzo = mezzo;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToOne
	private ParcoMezzi mezzo;
	private LocalDate dataInizio;
	private LocalDate dataFine;
}
