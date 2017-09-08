package com.khalid.springAcademy.beans;

public class Jongleur implements IArtiste {

	private String nom;
	int nbBalles;
	
	public int getNbBalles() {return nbBalles;}

	public void setNbBalles(int nbBalles) {this.nbBalles = nbBalles;}

	public void setNom(String nom) {this.nom = nom;}	

	public Jongleur(String nom, int nbBalles) {
		super();
		this.nom = nom;
		this.nbBalles = nbBalles;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public void faireNumero() {
		System.out.println("Moi "+getNom()+" vais jongler avec "+this.getNbBalles()+" balles");
		System.out.println("je jongle, je jongle...");
	}

}
