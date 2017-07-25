package designPatterns;

public class Client {
	
	private String nom;
	private Adresse adresse;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public Adresse getAdresse() {return adresse;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	
	public Client(String nom, Adresse adresse) 
	{super();this.nom = nom;
	this.adresse = adresse;
	}
	
	@Override
	public String toString() {
		return "Client [nom=" + nom + ", adresse=" + adresse + "]";
	}
	
	

}
