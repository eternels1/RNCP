package com.khalid.springAcademy.beans;

public class Guitare implements IInstrument {
	
	private String nom;

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public void jouer() {
		System.out.println("tring tring guitare...");

	}

}
