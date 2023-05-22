package entities;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@DiscriminatorValue("distributore_automatico")
@Getter
@Setter
@NoArgsConstructor
public class DistributoriAutomatici extends PuntiVendita{
	@Enumerated(EnumType.STRING)
	private Stato stato;

	public DistributoriAutomatici(String indirizzo, Stato stato) {
		super(indirizzo);
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "DistributoriAutomatici [stato=" + stato + "]";
	}
}
