package com.loncoto.mangamania.metier;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class Manga {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String Auteur;
	//@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateParution;
	private String genre;
	private int rating;
}
