package com.khalid.springKamelot.beans;

public class ChevalierTableRonde implements IChevalier {

	private String nom;
	private double competence;
	private IQuete quete;
	private Cheval monture;
	
	@Override
	public String getNom() { return nom;}
	public double getCompetence() {return competence;}
	public void setCompetence(double competence) {this.competence = competence;}
	public IQuete getQuete() {return quete;}
	public void setQuete(IQuete quete) {this.quete = quete;}
	public void setNom(String nom) {this.nom = nom;}
	public Cheval getMonture() {return monture;}
	public void setMonture(Cheval monture) {this.monture = monture;}
	
	@Override
	public String toString() {
		return "ChevalierTableRonde [nom=" + nom + ", competence=" + competence + ", quete=" + quete + ", monture="
				+ monture + "]";
	}
	// constructeur
	public ChevalierTableRonde(String nom) {
		System.out.println("construction chevalier " + nom);
		setNom(nom);
	}

	@Override
	public void partirEnQuete() {
		System.out.println("moi, sire " + getNom()
						+ " part en quete sur mon fidele destrier "
						+ getMonture().getNom() + " : "
						+ getQuete().getDescription());
		
		boolean success = getQuete().realiserQuete(getCompetence());
		
		if (success)
			System.out.println("moi, sire " + getNom() 
							+ " reviens glorieusement de quete");
		else
			System.out.println("moi, sire " + getNom()
							+ " a eu un contretemps");

	}


}
