package com.khalid.BlogManager.metier;

public class Auteur {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public Auteur() {}
	public Auteur(int id, String nom, String prenom, String email) {
		setId(id);
		setNom(prenom);
		setPrenom(prenom);
		setEmail(email);
	}
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
	
}
