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
public class Biglietto extends TitoliDiViaggio {

	@Override
	public String toString() {
		return "Biglietto [vidimato=" + vidimato + ", dataVidimazione=" + dataVidimazione + ", Id=" + getId()
				+ ", DataEmissione=" + getDataEmissione() + ", isConvalidato=" + isConvalidato() + "]";
	}

	private boolean vidimato;

	@ManyToOne
	private ParcoMezzi mezzo;
	private LocalDate dataVidimazione;

	public Biglietto(LocalDate dataEmissione, boolean convalidato, PuntiVendita puntoVendita, boolean vidimato,
			ParcoMezzi mezzo, LocalDate dataVidimazione) {
		super(dataEmissione, convalidato, puntoVendita);
		this.vidimato = vidimato;
		this.mezzo = mezzo;
		this.dataVidimazione = dataVidimazione;
	}

	public Biglietto(LocalDate dataEmissione, boolean convalidato, PuntiVendita puntoVendita, ParcoMezzi mezzo) {
		super(dataEmissione, convalidato, puntoVendita);
		this.vidimato = false;
		this.mezzo = mezzo;
		this.dataVidimazione = null;
	}

}
