package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import entities.enums.Periodicità ;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Abbonamento extends TitoliDiViaggio {

	@Enumerated(EnumType.STRING)
	private Periodicità  periodicità ;
	private LocalDate dataScadenza;

	@OneToOne
	private Tessera numeroTessera;

	public Abbonamento(LocalDate dataEmissione, boolean convalidato, PuntiVendita puntoVendita,  Periodicità  periodicità , LocalDate dataScadenza,
			Tessera numeroTessera) {
		super(dataEmissione, convalidato, puntoVendita);
		this.periodicità  = periodicità ;
		this.dataScadenza = dataScadenza;
		this.numeroTessera = numeroTessera;
	}
}
