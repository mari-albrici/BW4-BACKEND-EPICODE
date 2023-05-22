package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("rivenditore_autorizzato")
@Getter
@Setter
@NoArgsConstructor

public class RivenditoriAutorizzati extends PuntiVendita{

	public RivenditoriAutorizzati(String indirizzo) {
		super(indirizzo);
	}
}
