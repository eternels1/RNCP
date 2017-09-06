package com.khalid.springKamelot.beans;

public class Cheval {
	private String nom;

	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	public Cheval(String nom) {
		super();
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return "Cheval [nom=" + nom + "]";
	}


}
