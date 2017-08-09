package com.khalid.jpaHeritageTablePerClass.beans;

import javax.persistence.Column;
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
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Personne {

//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id @Column(length=36)		private String id;
								private String nom;
								private String prenom;
	
	
}
