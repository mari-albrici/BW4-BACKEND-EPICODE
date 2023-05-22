package entities;
import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "punti_vendita")
@DiscriminatorColumn(name = "tipologia")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //consigliato per avere migliori performance e in questo caso 
													//possibilmente avremo solo un campo null.
@Getter
@Setter
@NoArgsConstructor
public abstract class PuntiVendita {
	@Id
	@GeneratedValue
	private UUID id;
	private String indirizzo;
	private Integer numeroVendite;
	
	//@OneToMany(mappedBy = punti_vendita) 
	//public List<TitoliDiViaggio> titoli_di_viaggio;
	
	public PuntiVendita(String indirizzo) {
		super();
		this.indirizzo = indirizzo;
		this.numeroVendite = numeroVendite;
	}

	@Override
	public String toString() {
		return "PuntiVendita [indirizzo=" + indirizzo + ", numeroVendite=" + numeroVendite + "]";
	}
}
