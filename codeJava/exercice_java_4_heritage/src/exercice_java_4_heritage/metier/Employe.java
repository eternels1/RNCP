package exercice_java_4_heritage.metier;

public class Employe extends Personne {
	
	double salaire;
	String poste;
	
	
	public double getSalaire() {return salaire;}
	public void setSalaire(double salaire) {this.salaire = salaire;}
	public String getPoste() {return poste;}
	public void setPoste(String poste) {this.poste = poste;}


	public Employe(String nom, String prenom, String email, double salaire, String poste) {
		super();
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		this.salaire = salaire;
		this.poste = poste;
	}
	@Override
	public String toString() {
		return "Employe [salaire=" + salaire + ", poste=" + poste + "]";
	}
	@Override
	public String contacter() {
		// TODO Auto-generated method stub
		return "Je vous contact et mon Poste Employé est : "+getPoste();
	}
	@Override
	public String sauver() {
		// TODO Auto-generated method stub
		return getNom()+getPrenom()+email+getPoste()+";"+getSalaire()+";";
	}

}
