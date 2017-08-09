package com.khalid.Exercice_JPA_2.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString(callSuper=true)
public class Image extends Content {

	private String fileName;
	private String typeImage;
	@ManyToOne 	private Gallerie gallerie;
	
	public Image(int id, String nom,String fileName, String typeImage) {
		super(id, nom);
		this.fileName = fileName;
		this.typeImage = typeImage;
	}	
	
}
