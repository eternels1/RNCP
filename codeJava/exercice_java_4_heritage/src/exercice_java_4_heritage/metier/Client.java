package exercice_java_4_heritage.metier;

public class Client extends Personne {

	public Client(String nom, String prenom, String email,int numClient, double solde) {
		setEmail(email);
		setNom(nom);
		setPrenom(prenom);
		this.noClient=numClient;
		this.soldeCompte= solde;
	}
	
	
	public double soldeCompte;
	public int noClient;	
	
	public double getSoldeCompte() {return soldeCompte;}
	public void setSoldeCompte(double soldeCompte) {this.soldeCompte = soldeCompte;}
	public int getNoClient() {return noClient;}
	public void setNoClient(int noClient) {this.noClient = noClient;}

	
	
	@Override
	public String toString() {
		return "Client [soldeCompte=" + soldeCompte + ", noClient=" + noClient + "]";
	}
	@Override
	public String contacter() {
		// TODO Auto-generated method stub
		return  "Je vous contact et mon n° Client est : "+ getNoClient();
	}
	@Override
	public String sauver() {
		// TODO Auto-generated method stub
		return getNom()+";"+getPrenom()+";"+getEmail()+";" +getNoClient()+";"+getSoldeCompte()+";";
	}
	
	
	
}
