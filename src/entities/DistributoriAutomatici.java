package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DistributoriAutomatici extends PuntiVendita{
	private Stato stato;
	
	public enum Stato {
		attivo,disattivo
	}

	public DistributoriAutomatici(String indirizzo, boolean is_distributore, Stato stato) {
		super(indirizzo, is_distributore);
		this.stato = stato;
	}
}
