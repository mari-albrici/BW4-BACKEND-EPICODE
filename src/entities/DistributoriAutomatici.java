package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("distributore_automatico")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "DistributoriAutomatici.distributoriAutomaticiDisattiviOAttivi" , query = "SELECT d FROM DistributoriAutomatici d WHERE d.stato = :stato")
public class DistributoriAutomatici extends PuntiVendita {
	@Enumerated(EnumType.STRING)
	private Stato stato;

	public DistributoriAutomatici(String indirizzo, Integer numeroVendite, Stato stato) {
		super(indirizzo, numeroVendite, true);
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "DistributoriAutomatici [id=" + this.getId() + " stato=" + stato + " indirizzo=" + getIndirizzo()
				+ "numero vendite=" + getNumeroVendite() + "]";
	}
}
