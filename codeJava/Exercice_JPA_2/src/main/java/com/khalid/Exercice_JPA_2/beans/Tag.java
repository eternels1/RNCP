package com.khalid.Exercice_JPA_2.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString(exclude= {"contents"})
public class Tag {
	
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Id								private int id;
								private String libelle;
@ManyToMany(mappedBy="tags")	private Set<Content> contents;
	
}
