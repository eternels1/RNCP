package com.khalid.springEventExpression.beans;

public class Adresse {
	
	private String rue;
	private String ville;
	private String codepostal;
	private String pays;
	
	public String getRue() {return rue;}
	public void setRue(String rue) {this.rue = rue;}
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}
	public String getCodepostal() {return codepostal;}
	public void setCodepostal(String codepostal) {this.codepostal = codepostal;}
	public String getPays() {return pays;}
	public void setPays(String pays) {this.pays = pays;}
	public Adresse(String rue, String ville, String codepostal, String pays) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codepostal = codepostal;
		this.pays = pays;
	}
	public Adresse() {}
	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", ville=" + ville + ", codepostal=" + codepostal + ", pays=" + pays + "]";
	}
	
	

}
