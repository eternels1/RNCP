package com.khalid.jpaInclusion.beans;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Livre {
	
@EmbeddedId			private CleLivre id;
					private String titre;
					private int nbpages;

}
