package entities;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
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
}
