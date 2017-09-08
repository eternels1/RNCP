package com.khalid.epategallerie.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.khalid.epategallerie.metier.views.ImageComplete;
import com.khalid.epategallerie.metier.views.ImageWithoutTags;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@ToString(exclude= {"tags"})
public class ImageFile {
@GeneratedValue(strategy=GenerationType.IDENTITY)
@JsonView(ImageWithoutTags.class)@Id					private int id;
@JsonView(ImageWithoutTags.class)@Column(length=100)	private String name;
@JsonView(ImageWithoutTags.class)@Column(length=200)	private String fileName;
@JsonView(ImageWithoutTags.class)@Column(length=100)	private String contentType;
@JsonView(ImageWithoutTags.class)						private long filsize;
@JsonView(ImageComplete.class)@Column(length=60)		private String hashMD5;
/*@JsonIgnore*/@JsonView(ImageComplete.class)@ManyToMany						private Set<Tag> tags;

public Set<Tag> getTags(){
	if (tags==null) {
		tags=new HashSet<>();		
	}
	return tags;
}

public ImageFile(int id, String name, String fileName, String contentType, long filsize, String hashMD5) {
	super();
	this.id = id;
	this.name = name;
	this.fileName = fileName;
	this.contentType = contentType;
	this.filsize = filsize;
	this.hashMD5 = hashMD5;
}


}
