package com.khalid.alloCine.metier;

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

@Entity
@Getter@Setter@NoArgsConstructor@ToString(exclude= {"films"})
public class Acteur {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id									private int id;
										private String nom;
										private String email;
	@ManyToMany(mappedBy="acteurs")		private Set<Film> films;
	
	public Set<Film> getFilms(){
		if (films==null) {
			films=new HashSet<>();
		}
		return films;
	}
										
	public Acteur(int id, String nom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
	}
	

}
