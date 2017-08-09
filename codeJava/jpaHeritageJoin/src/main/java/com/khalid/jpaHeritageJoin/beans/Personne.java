package com.khalid.jpaHeritageJoin.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Personne {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id 		private int id;
				private String nom;
				private String prenom;
	
	
}
