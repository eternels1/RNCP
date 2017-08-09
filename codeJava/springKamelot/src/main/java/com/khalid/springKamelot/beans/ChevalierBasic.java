package com.khalid.springKamelot.beans;

public class ChevalierBasic implements IChevalier {

	private String nom;
	private double competence;
	private IQuete quete;
	
	
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	public double getCompetence() {return competence;}

	public void setCompetence(double competence) {this.competence = competence;}

	public IQuete getQuete() {return quete;}

	public void setQuete(IQuete quete) {this.quete = quete;}

	public void setNom(String nom) {this.nom = nom;}
	
	public ChevalierBasic(String nom) {setNom(nom);}

	@Override
	public String toString() {
		return "ChevalierBasic [nom=" + nom + ", competence=" + competence + ", quete=" + quete + "]";
	}

	@Override
	public void partirEnQuete() {
		System.out.println("moi, "+getNom()+" part en quete : "+getQuete().getDescription());
		boolean success=getQuete().realiserQuete(getCompetence());
		if (success) {
			System.out.println("moi, "+getNom()+" reviens victorieusement de la quete!");
		}
		else System.out.println("moi, "+getNom()+" aura plus de chance une autre fois...");

	}

}
