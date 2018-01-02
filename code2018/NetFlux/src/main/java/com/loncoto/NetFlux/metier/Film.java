package com.loncoto.NetFlux.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity@Getter
public class Film {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String realisateur;
	private int annee;
	private String synopsis;
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
		this.titre = titre;
	}
	public String getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public Film(int id, String titre, String realisateur, int annee, String synopsis) {
		super();
		this.id = id;
		this.titre = titre;
		this.realisateur = realisateur;
		this.annee = annee;
		this.synopsis = synopsis;
	}
	public Film() {}
	
	
	
}
