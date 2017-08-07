package com.khalid.JPAUniversity.beans;

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
public class Matiere {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelleMatiere;
	
	
	
	
	
	

}
