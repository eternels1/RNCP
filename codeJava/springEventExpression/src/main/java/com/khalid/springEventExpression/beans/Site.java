package com.khalid.springEventExpression.beans;

public class Site {
	
	private int id;
	private String nom;
	private Adresse adresse;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public Adresse getAdresse() {return adresse;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	
	public Site() {}
	public Site(int id, String nom, Adresse adresse) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Site [id=" + id + ", nom=" + nom + ", adresse=" + adresse + "]";
	}	

}
