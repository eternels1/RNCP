package com.khalid.basicMapping.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="products")
public class Produit {
	
	private int id;
	private String nom;
	private double prix;
	private double poids;
	private int stock;
	private Date dateCatalogue;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	@Column(name="nom_produit",length=100)
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public double getPoids() {return poids;}
	public void setPoids(double poids) {this.poids = poids;}
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}
	public Date getDateCatalogue() {return dateCatalogue;}
	public void setDateCatalogue(Date dateCatalogue) {this.dateCatalogue = dateCatalogue;}
	
	public Produit() {}
	public Produit(int id, String nom, double prix, double poids, int stock, Date dateCatalogue) {
		setId(id);
		setNom(nom);
		setPrix(prix);
		setPoids(poids);
		setStock(stock);
		setDateCatalogue(dateCatalogue);		
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + ", stock=" + stock
				+ ", dateCatalogue=" + dateCatalogue + "]";
	}
	

}
