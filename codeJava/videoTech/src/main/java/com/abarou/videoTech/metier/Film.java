package com.abarou.videoTech.metier;

public class Film {

	private int id;
	private String titre;
	private int annee;
	private String realisateur;
	private String description;
	private int rating;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public int getAnnee() {return annee;}
	public void setAnnee(int annee) {this.annee = annee;}
	
	public String getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getRating() {return rating;}
	public void setRating(int rating) {this.rating = rating;}
	
	public Film(int id, String titre, int annee, String realisateur, String description, int rating) {
		setAnnee(annee);
		setDescription(description);
		setId(id);
		setRating(rating);
		setTitre(titre);
		setRealisateur(realisateur);		
	}
	public Film() {}
	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", annee=" + annee + ", realisateur=" + realisateur
				+ ", description=" + description + ", rating=" + rating + "]";
	}
	
	
	
}
