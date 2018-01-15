package com.loncoto.firstSecurity.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@NoArgsConstructor@ToString(exclude= {"roles","password"})
@Entity
public class Utilisateur {
	
	
	public Utilisateur(int id, String userName, String password, boolean enabled, Set<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private boolean enabled;
	@ManyToMany 
	private Set<Role> roles;
	
	public Set<Role> getRoles(){
		if (roles==null) {
			roles= new HashSet<>();
		}
		return roles;
	}
	

}
