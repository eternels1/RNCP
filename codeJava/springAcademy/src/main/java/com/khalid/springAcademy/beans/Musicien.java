package com.khalid.springAcademy.beans;

public class Musicien implements IArtiste {

	private String nom;
	private IInstrument instrument;
	
	
	public IInstrument getInstrument() {return instrument;}

	public void setInstrument(IInstrument instrument) {this.instrument = instrument;}

	public void setNom(String nom) {this.nom = nom;}

	@Override
	public String getNom() {
		
		return nom;
	}

	@Override
	public void faireNumero() {
		System.out.println("Moi "+getNom()+" vais jouer de "+instrument.getNom());
		instrument.jouer();

	}

}
