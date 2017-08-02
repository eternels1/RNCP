package com.khalid.BlogManager.metier;

import java.util.Date;

public class Post {

	private int id;
	private String titre;
	private String corps;
	private Date dateCreation;
	private Date dateEdition;
	private int auteurId;
	private Auteur auteur;	
	
	public Auteur getAuteur() {return auteur;}
	public void setAuteur(Auteur auteur) {this.auteur = auteur;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	public Date getDateCreation() {return dateCreation;}
	public void setDateCreation(Date dateCreation) {this.dateCreation = dateCreation;}
	public Date getDateEdition() {return dateEdition;}
	public void setDateEdition(Date dateEdition) {this.dateEdition = dateEdition;}
	public int getAuteurId() {return auteurId;}
	public void setAuteurId(int auteurId) {this.auteurId = auteurId;}
	public Post() {}
	public Post(int id, String titre, String corps, Date dateCreation, Date dateEdition, int auteurId) {
		setId(id);
		setTitre(titre);
		setCorps(corps);
		setDateCreation(dateCreation);
		setDateEdition(dateEdition);
		setAuteurId(auteurId);
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", titre=" + titre + ", corps=" + corps + ", dateCreation=" + dateCreation
				+ ", dateEdition=" + dateEdition + ", auteurId=" + auteurId + "]";
	}
	
}
