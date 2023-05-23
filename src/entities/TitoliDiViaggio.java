package entities;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "titoli_viagio")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_titolo")
public abstract class TitoliDiViaggio {

	@Id
	@GeneratedValue
	private UUID id;
	
	private LocalDate dataEmissione;
	private boolean convalidato;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private PuntiVendita puntoVendita;
	
	public TitoliDiViaggio(LocalDate dataEmissione, boolean convalidato, PuntiVendita puntoVendita) {
		this.dataEmissione = dataEmissione;
		this.convalidato = convalidato;
		this.puntoVendita = puntoVendita;
	}
}
