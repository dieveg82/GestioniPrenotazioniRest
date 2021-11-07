package it.epicode.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table (name = "user")
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String nome;
	private String email;
	private String password;
	private boolean active = true;
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
//	@OneToMany (mappedBy = "user")
//	private Set<Prenotazione> setPrenotazione = new HashSet<Prenotazione>();
	
	
	public User(String username, String nome, String email, String password, boolean active) {
		this.username = username;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.active = active;
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nome=" + nome + ", email=" + email + ", password="
				+ password + ", active=" + active + "]";
	}
	
	
}
