package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Biglietto extends TitoliDiViaggio{

	private boolean vidimato;
	
	@ManyToOne
	private ParcoMezzi mezzo;
	private LocalDate dataVidimazione;

	
	public Biglietto(LocalDate dataEmissione, boolean convalidato, PuntiVendita puntoVendita, boolean vidimato, ParcoMezzi mezzo, LocalDate dataVidimazione) {
		super(dataEmissione, convalidato, puntoVendita);
		this.vidimato = vidimato;
		this.mezzo = mezzo;
		this.dataVidimazione = dataVidimazione;
	}
}
