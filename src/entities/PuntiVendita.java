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
import lombok.ToString;

@Entity
@Table(name = "punti_vendita")
@DiscriminatorColumn(name = "tipologia")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class PuntiVendita {
	@Id
	@GeneratedValue
	private UUID id;
	private String indirizzo;
	private boolean is_distributore;
	
	//@OneToMany(mappedBy = punti_vendita) 
	//public TitoliDiViaggio titoli_di_viaggio;
	
	public PuntiVendita(String indirizzo, boolean is_distributore) {
		super();
		this.indirizzo = indirizzo;
		this.is_distributore = is_distributore;
	}

	@Override
	public String toString() {
		return "PuntiVendita [id=" + id + ", indirizzo=" + indirizzo + ", is_distributore=" + is_distributore + "]";
	}
}
