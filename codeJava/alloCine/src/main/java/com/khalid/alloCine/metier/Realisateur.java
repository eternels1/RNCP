package com.khalid.alloCine.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@ToString(exclude={"films"})
public class Realisateur {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id									private int id;
										private String nom;
	@OneToMany(mappedBy="realisateur")	private Set<Film> films;
	
	public Set<Film> getFilms(){
		if (films==null) {
			films= new HashSet<>();
		}
		return films;
	}
					
	public Realisateur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

}
