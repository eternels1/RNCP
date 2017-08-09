package com.khalid.Exercice_JPA_2.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.khalid.Exercice_JPA_2.utils.Horodatable;
import com.khalid.Exercice_JPA_2.utils.Horodateur;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners({Horodateur.class})
@Inheritance(strategy=InheritanceType.JOINED)
@Getter @Setter@NoArgsConstructor@ToString(exclude= {"tags"})
public class Content implements Horodatable{

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id			private int id;
				private String nom;
				private LocalDateTime dateCreation;
				private LocalDateTime dateEdition;
	@ManyToMany	private Set<Tag> tags;
	
	public Set<Tag> getTags(){
		if (tags==null) {
			tags= new HashSet<>();
		}
		return tags;
	}
	public Content(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
}
