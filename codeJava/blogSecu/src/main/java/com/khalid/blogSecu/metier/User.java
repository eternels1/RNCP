package com.khalid.blogSecu.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter@NoArgsConstructor@ToString(exclude= {"roles"})
public class User {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id															private int id;
	@Column(length=100, unique=true)							private String username;
	@Column(length=100)											private String password;
																private boolean enabled;
	@ManyToMany													private Set<Role> roles;
	
	
	public Set<Role> getRoles(){
		if (roles==null) {
			roles= new HashSet<>();
		}
		return roles;
	}
	
	public User(int id, String username, String password, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	

}
