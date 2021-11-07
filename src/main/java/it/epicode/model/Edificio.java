package it.epicode.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table (name ="edificio")
public class Edificio {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String indirizzo;
	
	@ManyToOne
	private Città citta;
	
//	@OneToMany (mappedBy = "edificio")
//	List<Postazione> listPostazione = new ArrayList<>();
	
	
	public Edificio(String nome, String indirizzo, Città città) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = città;
	}
	
	
	
}
