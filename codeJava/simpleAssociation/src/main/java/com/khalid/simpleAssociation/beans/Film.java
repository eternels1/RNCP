package com.khalid.simpleAssociation.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Film {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date dateFilm;
	private int dureeMinutes;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Acteur> lstActeurs;
	@ManyToOne
	private Genre genreduFilm;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public Date getDateFilm() {return dateFilm;}
	public void setDateFilm(Date dateFilm) {this.dateFilm = dateFilm;}
	public int getDureeMinutes() {return dureeMinutes;}
	public void setDureeMinutes(int dureeMinutes) {this.dureeMinutes = dureeMinutes;}
	public Set<Acteur> getLstActeurs() {
		if (lstActeurs==null) {
			lstActeurs= new HashSet<>();
		}
		return lstActeurs;}
	
	public void setLstActeurs(Set<Acteur> lstActeurs) {this.lstActeurs = lstActeurs;}
	public Genre getGenreduFilm() {return genreduFilm;}
	public void setGenreduFilm(Genre genreduFilm) {this.genreduFilm = genreduFilm;}
	
	public Film() {}
	public Film(int id, String titre, Date dateFilm, int dureeMinutes) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateFilm = dateFilm;
		this.dureeMinutes = dureeMinutes;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", dateFilm=" + dateFilm + ", dureeMinutes=" + dureeMinutes+ "]";
	}
	
	
}
