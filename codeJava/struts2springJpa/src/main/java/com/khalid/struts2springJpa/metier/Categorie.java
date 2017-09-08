package com.khalid.struts2springJpa.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@NoArgsConstructor@ToString(exclude= {"produits"})
@Entity
public class Categorie {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id									private int id;
	@Column(length=100, unique=true)	private String libelle;
	@ManyToMany							private Set<Produit> produits;
										
	public Set<Produit> getProduits(){
		if (produits==null) {
			produits= new HashSet<>();
		}
		return produits;
	}
	
	
	public Categorie(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	

}
