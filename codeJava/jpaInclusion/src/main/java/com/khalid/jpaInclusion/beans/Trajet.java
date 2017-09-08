package com.khalid.jpaInclusion.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
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
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Trajet {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id				private int id;
					private String nom;
					private int duree;
	@Embedded		private Adresse depart;
	@AttributeOverrides({
		@AttributeOverride(name="rue",column=@Column(name="ARR_RUE",length=100)),
		@AttributeOverride(name="ville",column=@Column(name="ARR_VILLE",length=100)),
		@AttributeOverride(name="cp",column=@Column(name="ARR_CP",length=100)),
		@AttributeOverride(name="pays",column=@Column(name="ARR_PAYS",length=100))})
	@Embedded		private Adresse arrivee;

}
