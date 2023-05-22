package entities;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Abbonamento extends TitoliDiViaggio{
	
	private Periodicità periodicità;
	private LocalDate dataScadenza; 

	public Abbonamento(LocalDate dataEmissione, boolean convalidato, Periodicità periodicità, LocalDate dataScadenza) {
		super(dataEmissione, convalidato);
		this.periodicità = periodicità;
		this.dataScadenza = dataScadenza;
	}
}
