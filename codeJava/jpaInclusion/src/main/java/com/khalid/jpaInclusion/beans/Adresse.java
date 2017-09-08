package com.khalid.jpaInclusion.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Adresse {
	
	@Column(length=100)			private String rue;
	@Column(length=20)			private int cp;
	@Column(length=50)			private String ville;
	@Column(length=100)			private String pays;


}
