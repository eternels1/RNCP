package com.khalid.Exercice_JPA_2.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString(callSuper=true)
public class DocumentPdf extends Content{
	
	private String titre;
	private String nomAuteur;
	
	public DocumentPdf(int id, String nom, String titre,String nomAuteur) {
		super(id, nom);
		this.titre = titre;
		this.nomAuteur = nomAuteur;
	}

	

}
