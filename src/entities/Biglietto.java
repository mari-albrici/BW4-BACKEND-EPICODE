package entities;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Biglietto extends TitoliDiViaggio{

	private boolean vidimato;
	private String mezzo;
	private LocalDate dataVidimazione;
	
	public Biglietto(LocalDate dataEmissione, boolean convalidato, boolean vidimato, String mezzo, LocalDate dataVidimazione) {
		super(dataEmissione, convalidato);
		this.vidimato = vidimato;
		this.mezzo = mezzo;
		this.dataVidimazione = dataVidimazione;
	}
}
