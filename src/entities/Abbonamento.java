package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Abbonamento extends TitoliDiViaggio{
	
	@Enumerated(EnumType.STRING)
	private Periodicità periodicità;
	private LocalDate dataScadenza; 
	private long numeroTessera;
	

	public Abbonamento(LocalDate dataEmissione, boolean convalidato, Periodicità periodicità, LocalDate dataScadenza, long numeroTessera) {
		super(dataEmissione, convalidato);
		this.periodicità = periodicità;
		this.dataScadenza = dataScadenza;
		this.numeroTessera = numeroTessera;
	}
}
