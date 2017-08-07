  package com.khalid.JPAUniversity.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professeur {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private double salaire;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public double getSalaire() {return salaire;}
	public void setSalaire(double salaire) {this.salaire = salaire;}
	
	public Professeur() {}
	public Professeur(int id, String nom, String prenom, double salaire) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setSalaire(salaire);
	}
	
	@Override
	public String toString() {
		return "Professeur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", salaire=" + salaire + "]";
	}
	

}
