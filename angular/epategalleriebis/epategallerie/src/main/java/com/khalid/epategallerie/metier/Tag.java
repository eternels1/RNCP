package com.khalid.epategallerie.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@ToString(exclude= {"imageFiles"})
public class Tag {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
@Id										private int id;
@Column(length=100)						private String label;
@ManyToMany(mappedBy="tags")			private Set<ImageFile> imageFiles;

public Set<ImageFile> getImageFiles(){
	if (imageFiles==null) {
		imageFiles=new HashSet<>();
	}
	return imageFiles;
}


public Tag(int id, String label) {
	super();
	this.id = id;
	this.label = label;
}	

}
