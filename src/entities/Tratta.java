package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Tratta {

	@Id
	@GeneratedValue
	private UUID id;
	private String zonaDiPartenza;
	private String capolinea;
	private String tempoMedioPercorrenza;
	private String distanza;

	public Tratta(String zonaDiPartenza, String capolinea, String tempoMedioPercorrenza, String distanza) {

		this.zonaDiPartenza = zonaDiPartenza;
		this.capolinea = capolinea;
		this.tempoMedioPercorrenza = tempoMedioPercorrenza + " min";
		this.distanza = distanza + " Km";
	}
}
