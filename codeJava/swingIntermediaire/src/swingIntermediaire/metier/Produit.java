package swingIntermediaire.metier;

import java.security.GeneralSecurityException;
import java.util.Comparator;
import java.util.function.Predicate;

public class Produit {
	
//	public static Predicate<Produit> getFilter(final String categorieName) {
//		if (categorieName.equals("toutes"))
//			return p -> true;
//		else
//			return  p -> p.getCategorie().equals(categorieName);
//	}
	 
	
	
	
	public static final Predicate<Produit> ALL_CATEGORIES_FILTER 
											= p-> true;
	public static final Predicate<Produit> VIANDES_CATEGORIES_FILTER 
											= p-> p.getCategorie().equals("viandes");
	public static final Predicate<Produit> FROMAGES_CATEGORIES_FILTER 
											= p-> p.getCategorie().equals("fromages");
    public static final Predicate<Produit> LEGUMES_CATEGORIES_FILTER 
											= p-> p.getCategorie().equals("legumes");
	public static final Predicate<Produit> DIVERS_CATEGORIES_FILTER 
											= p-> p.getCategorie().equals("divers");
	public static final Predicate<Produit> CEREALES_CATEGORIES_FILTER 
											= p-> p.getCategorie().equals("cereales");

public static final Comparator<Produit> ID_SORT
			=(p1,p2) -> Integer.compare(p1.getId(), p2.getId());
			public static final Comparator<Produit> NOM_SORT
			=(p1,p2) -> p1.getNom().compareTo(p2.getNom());
			public static final Comparator<Produit> PRIX_SORT
			=(p1,p2) -> Double.compare(p1.getPrix(), p2.getPrix());
			public static final Comparator<Produit> POIDS_SORT
			=(p1,p2) -> Double.compare(p1.getPoids(), p2.getPoids());
	
	
	
	private int id;
	private String nom;
	private double prix;
	private double poids;
	private String categorie;
	
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public double getPoids() {return poids;}
	public void setPoids(double poids) {this.poids = poids;}	
	public String getCategorie() {return categorie;}
	public void setCategorie(String categorie) {this.categorie = categorie;}
	
	public Produit(int id, String nom, double prix, double poids, String categorie) {
		
		setId(id);
		setNom(nom);
		setPoids(poids);
		setPrix(prix);
		setCategorie(categorie);
		
		}
	public Produit() {this(0,"",0.0,0.0,"divers");}
	
	
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + ", categorie=" + categorie
				+ "]";
	}
	
	
	

}
