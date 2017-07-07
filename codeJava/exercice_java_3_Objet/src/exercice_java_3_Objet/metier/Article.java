package exercice_java_3_Objet.metier;

public class Article {
	
	private int id;
	private String titre;
	private String corp;
	private String nomAuteur;
	private double rating;
	
	public static final String DEFAUT_TITRE="lorem";
	public static final String DEFAUT_CORPS="lorem Ipsum";
	public static final String DEFAUT_NOMAUTEUR="homer";
	public static final double DEFAUT_RATING= 2.5;
	
	public static int compteur=0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {

		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		
		if (titre.length()>= 5 && titre.length()<=100) {
			this.titre = titre;
		}
		else this.titre= DEFAUT_TITRE;
		
	}

	public String getCorp() {
		return corp;
	}
	public void setCorp(String corp) {
		if (corp.length()>=5 && corp.length()<=400) {
			this.corp = corp;
		}
		else this.corp= DEFAUT_CORPS;
	}
	
	public String getNomAuteur() {
		return nomAuteur;
	}
	public void setNomAuteur(String nomAuteur) {
		if (nomAuteur.length()>=2 && nomAuteur.length()<=50) {
			this.nomAuteur = nomAuteur;
		}
		else this.nomAuteur=DEFAUT_NOMAUTEUR;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		if (rating>=0.0 && rating<=5.0) {
			this.rating = rating;
		}
		this.rating= DEFAUT_RATING;
	}
	

	
	
	public Article() {
		
	}
	public Article(int id, String titre, String corp, String nomAuteur, double rating) {
		setId(id);
		setTitre(titre);
		setCorp(corp);
		setNomAuteur(nomAuteur);
		setRating(rating); 
	}
	public Article(String titre, String corp, String nomAuteur, double rating) {
		setId(compteur++);
		setTitre(titre);
		setCorp(corp);
		setNomAuteur(nomAuteur);
		setRating(rating);
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", titre=" + titre + ", corp=" + corp + ", nomAuteur=" + nomAuteur + ", rating="
				+ rating + "]";
	}
	
	
	
	
}
