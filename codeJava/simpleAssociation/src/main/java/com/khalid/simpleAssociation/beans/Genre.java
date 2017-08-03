package com.khalid.simpleAssociation.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	
	
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	
	
	public Genre() {}
	public Genre(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Genre [id=" + id + ", libelle=" + libelle + "]";
	}
	

}
