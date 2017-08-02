package com.abarou.AgenceVoyage.metier;

import java.util.Date;

public class Voyage {
	
	private int id;
	private String libelle;
	private String destination;
	private double prix;
	private int agenceId;
	private Agence agence;
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	private boolean passeport;
	private Date dateDepart;
	public Date getDateDepart() {return dateDepart;}
	public void setDateDepart(Date dateDepart) {this.dateDepart = dateDepart;}
	public Date getDateArrivee() {return dateArrivee;}
	public void setDateArrivee(Date dateArrivee) {this.dateArrivee = dateArrivee;}
	private Date dateArrivee;	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	public String getDestination() {return destination;}
	public void setDestination(String destination) {this.destination = destination;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}	
	public int getAgenceId() {return agenceId;}
	public void setAgenceId(int agenceId) {this.agenceId = agenceId;}
	public boolean isPasseport() {return passeport;}
	public void setPasseport(boolean passeport) {this.passeport = passeport;}
	public Voyage(int id, String libelle, String destination, double prix, int agenceId, boolean passeport) {
		setId(id);
		setLibelle(libelle);
		setDestination(destination);
		setPrix(prix);
		setAgenceId(agenceId);
		setPasseport(passeport);
		setAgence(null);
	}
	public Voyage() {}
	@Override
	public String toString() {
		return "Voyage [id=" + id + ", libelle=" + libelle + ", destination=" + destination + ", prix=" + prix
				+ ", agenceId=" + agenceId + ", passeport=" + passeport + "]";
	}
	

}
