package com.khalid.visageLivre.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@ToString
public class Profil {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
@Id								private int id;
@Column(length=100)				private String nom;
@Column(length=100)				private String prenom;
								private int age;
								private String email;
								private String ville;
@Column(length=10)				private String sexe;

public Profil(int id, String nom, String prenom, int age, String email, String ville, String sexe) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.age = age;
	this.email = email;
	this.ville = ville;
	this.sexe = sexe;
}



}
