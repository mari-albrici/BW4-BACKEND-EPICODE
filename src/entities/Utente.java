package entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name="utenti.findAll", query = "SELECT e FROM Utente e")
@NamedQuery(name="utenti.findId", query = "SELECT e FROM Utente e WHERE e.id = :id")
@NamedQuery(name="utenti.findTessera", query = "SELECT e FROM Utente e WHERE tessera_utente = :tessera")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "tessera_utente", unique= true)
	private Tessera tessera;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;


	public Utente(Tessera tessera, String nome, String cognome) {
		super();
		this.tessera = tessera;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public Utente(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

	@Override
	public String toString() {
		return "Utente [getId()=" + getId() + ", getTessera()=" + getTessera() + ", getNome()=" + getNome()
				+ ", getCognome()=" + getCognome() + "]";
	}

	

	
	
	

}