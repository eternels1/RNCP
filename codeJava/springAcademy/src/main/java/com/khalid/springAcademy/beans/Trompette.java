package com.khalid.springAcademy.beans;

public class Trompette implements IInstrument {
	
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
		System.out.println("coin coion trompette");
		
	}

}
