package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Tessera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy="tessera")
	private Utente utente;
	
	private LocalDate emissioneTessera = LocalDate.now();
	
	private LocalDate scadenzaTessera = LocalDate.now().plusYears(1);
	
	public void rinnovaTessera() {
		if( LocalDate.now().compareTo(scadenzaTessera) < 0 ) {
			scadenzaTessera = scadenzaTessera.plusYears(1);
		} else {
			scadenzaTessera = LocalDate.now().plusYears(1);
		}
	}
	
}
