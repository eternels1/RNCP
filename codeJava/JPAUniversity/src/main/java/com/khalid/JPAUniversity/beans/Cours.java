package com.khalid.JPAUniversity.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cours {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private int capaciteMax;
	
	@ManyToMany
	private Set<Etudiant> lstEtudiants;
	
	public Set<Etudiant> getLstEtudiants() {
		if (lstEtudiants==null) {
			lstEtudiants= new HashSet<>();
		}
		return lstEtudiants;
	}
	public void setLstEtudiants(Set<Etudiant> lstEtudiants) {this.lstEtudiants = lstEtudiants;}
	
	@ManyToOne
	Professeur professeur;
	@ManyToOne
	Matiere matiere;
		
	
	public Matiere getMatiere() {return matiere;}
	public void setMatiere(Matiere matiere) {this.matiere = matiere;}
	public Professeur getProfesseur() {return professeur;}
	public void setProfesseur(Professeur professeur) {this.professeur = professeur;}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	public Date getDateDebut() {return dateDebut;}
	public void setDateDebut(Date dateDebut) {this.dateDebut = dateDebut;}
	public Date getDateFin() {return dateFin;}
	public void setDateFin(Date dateFin) {this.dateFin = dateFin;}
	public int getCapaciteMax() {return capaciteMax;}
	public void setCapaciteMax(int capaciteMax) {this.capaciteMax = capaciteMax;}
	
	public Cours(int id, String libelle, Date dateDebut, Date dateFin, int capaciteMax) {
		setId(id);
		setLibelle(libelle);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setCapaciteMax(capaciteMax);
	}
	public Cours() {}
	
	@Override
	public String toString() {
		return "Cours [id=" + id + ", libelle=" + libelle + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", capaciteMax=" + capaciteMax + "]";
	}
	
	

}
