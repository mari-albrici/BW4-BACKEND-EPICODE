package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import entities.enums.Periodicita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Abbonamento extends TitoliDiViaggio {

	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;
	private LocalDate dataScadenza;

	@OneToOne
	private Tessera numeroTessera;

	public Abbonamento(LocalDate dataEmissione, boolean convalidato, PuntiVendita puntoVendita, Periodicita periodicita,
			LocalDate dataScadenza, Tessera numeroTessera) {
		super(dataEmissione, convalidato, puntoVendita);
		this.periodicita = periodicita;
		this.dataScadenza = dataScadenza;
		this.numeroTessera = numeroTessera;
	}

	@Override
	public String toString() {
		return "Abbonamento [periodicita =" + periodicita + ", dataScadenza=" + dataScadenza + "]";
	}

}