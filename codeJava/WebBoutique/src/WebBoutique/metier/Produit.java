package WebBoutique.metier;

public class Produit {
	private int id;
	private String nom;
	private double prix;
	private double poids;
	
	
	
	
	
	
	
	
	
	
	
	public Produit() {this(0,"",0.0,0.0);}
	public Produit(int id, String nom, double prix, double poids) {
		setId(id);
		setNom(nom);
		setPrix(prix);
		setPoids(poids);	
	}
			
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + "]";
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public double getPoids() {return poids;}
	public void setPoids(double poids) {this.poids = poids;}
	
	
	

}
