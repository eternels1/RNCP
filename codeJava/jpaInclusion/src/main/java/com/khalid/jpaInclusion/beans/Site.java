package com.khalid.jpaInclusion.beans;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Site {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id				private int id;
					private String nom;
	@Embedded		private Adresse adresse;
					private Geolocalisation geolocalisation;
	

}
