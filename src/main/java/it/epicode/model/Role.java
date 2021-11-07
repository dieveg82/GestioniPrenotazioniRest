package it.epicode.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table (name ="role")
public class Role {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated (EnumType.STRING)
	private RoleType roleType;
	
	public Role(RoleType roleType) {
		this.roleType = roleType;
	}
	
	public Role () {}
	
}
