package exercice_java_4_heritage.metier;

public abstract class Personne {
	String nom;
	String prenom;
	String email;
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
	
public abstract String contacter();

public abstract String sauver();


}
