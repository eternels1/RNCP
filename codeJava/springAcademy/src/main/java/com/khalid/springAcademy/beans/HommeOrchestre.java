package com.khalid.springAcademy.beans;

import java.util.List;

public class HommeOrchestre implements IArtiste {

	private String nom;
	List<IInstrument> lstinstruments;	
		
	public List<IInstrument> getLstinstruments() {return lstinstruments;}

	public void setLstinstruments(List<IInstrument> lstinstruments) {this.lstinstruments = lstinstruments;}

	public void setNom(String nom) {this.nom = nom;}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public void faireNumero() {
		System.out.println("Moi "+getNom()+" vais jouer avec :");
		for (IInstrument instrument : lstinstruments) {
			System.out.println(instrument.getNom());
			instrument.jouer();
		}

	}

}
