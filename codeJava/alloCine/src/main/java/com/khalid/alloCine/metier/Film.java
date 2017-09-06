package com.khalid.alloCine.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@ToString(exclude= {"acteurs","realisateur"})@NoArgsConstructor
public class Film {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id									private int id;
										private String titre;
	@Column(length=400)					private String synopsys;
										private int annee;
	@ManyToMany							private Set<Acteur> acteurs;
	@ManyToOne							private Realisateur realisateur;
	
	public Set<Acteur> getActeurs(){
		if (acteurs==null) {
			acteurs= new HashSet<>();
		}
		return acteurs;
	}
	
	public Film(int id, String titre, String synopsys, int annee) {
		super();
		this.id = id;
		this.titre = titre;
		this.synopsys = synopsys;
		this.annee = annee;
	}
							
	

}
