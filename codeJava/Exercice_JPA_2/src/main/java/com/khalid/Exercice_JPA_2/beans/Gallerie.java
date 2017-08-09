package com.khalid.Exercice_JPA_2.beans;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor  @ToString(callSuper=true,exclude= {"images"})
public class Gallerie extends Content {
	
									private String titre;
	@OneToMany(mappedBy="gallerie")	private Set<Image> images;
	public Gallerie(int id, String nom, String titre) {
		super(id, nom);
		this.titre = titre;
		
	}
}
