package com.khalid.jpaHeritageTablePerClass.beans;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @NoArgsConstructor @ToString(callSuper=true)
public class Employe extends Personne {

	private String poste;
	private double salaire;
	public Employe(String id, String nom, String prenom, String poste, double salaire) {
		super(id, nom, prenom);
		this.poste = poste;
		this.salaire = salaire;
	}
	
	
	
}
