package com.abarou.videoTech.actions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.abarou.videoTech.metier.Film;
import com.opensymphony.xwork2.ActionSupport;

import dao.GenericDAO;

public class FilmAction extends ActionSupport
						implements ApplicationAware{
	
	private GenericDAO<Film> filmDAO;
	@Override
	public void setApplication(Map<String, Object> ctx) {
		this.filmDAO= (GenericDAO<Film>) ctx.get("filmDAO");
	}
	
	private List<Film> films;
	private int id;
	private int editId;
	private String editTitre;
	private String editRealisateur;
	private int editAnnee;
	private int editRating;
	private String editDescription;
	public int getEditId() {return editId;}
	public void setEditId(int editId) {this.editId = editId;}
	public String getEditTitre() {return editTitre;}
	public void setEditTitre(String editTitre) {this.editTitre = editTitre;}
	public String getEditRealisateur() {return editRealisateur;}
	public void setEditRealisateur(String editRealisateur) {this.editRealisateur = editRealisateur;}
	public int getEditAnnee() {return editAnnee;}
	public void setEditAnnee(int editAnnee) {this.editAnnee = editAnnee;}
	public int getEditRating() {return editRating;}
	public void setEditRating(int editRating) {this.editRating = editRating;}
	
	
	public String getEditDescription() {
		return editDescription;
	}
	public void setEditDescription(String editDescription) {
		this.editDescription = editDescription;
	}

	private String titre;
	//je récupére un loggeur pour ma class
	Logger log = LogManager.getLogger(FilmAction.class);
	
	public String getTitre() {
		log.info("appel de getTitre()");
		return titre;}
	public void setTitre(String titre) {
		log.info("appel de setTitre()");
		this.titre = titre;}
	

	public List<Film> getFilms() {
		log.info("appel de getFilm()");
		if (films==null) {
			films=filmDAO.findAll();
		}
	return films;
}
	
	
	public void setId(int id) {log.info("appel de setId()");
		this.id = id;
	}
	public FilmAction() {
		log.info("appel du constructeurFilmAction()");
////		films= Arrays.asList(new Film(1, "Valerian", 2017, "Luc Besson", "des trucs galactique", 2),
////				 new Film(2, "Kung Fury", 2015, "David Sandberg", "Année 80, art martiaux, dinosores et des nazis", 1),
////				 new Film(3, "Le Roi Lion", 1994, "Luc Besson", "des trucs galactique", 2)
//	);
	}
	public String listeFilm() {
		
		//lister les films et repondra à l'url /film
		log.info("appel de listeFilm()");
		titre = "la video du futur en "+ LocalDateTime.now();
				
		return SUCCESS;
	}
	
	public String editerFilm() {
		log.info("appel dediterFilm() avec id = "+this.id);
//		Optional<Film> f=getFilms().stream().filter(f1 -> f1.getId()==id)
//							.findFirst();
		
		Film f= filmDAO.findByCle(this.id);
		if (f !=null) {
			setEditId(f.getId());
			setEditTitre(f.getTitre());
			setEditAnnee(f.getAnnee());
			setEditRealisateur(f.getRealisateur());
			setEditRating(f.getRating());
			setEditDescription(f.getDescription());
			
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	
	public String creerFilm() {
		log.info("appel creerFilm()");

		
			setEditId(0);
			setEditTitre("un titre..");
			setEditAnnee(LocalDate.now().getYear());
			setEditRealisateur("realisateur");
			setEditRating(1);
			setEditDescription("un film...");
			
			return SUCCESS;
		
	}
	
	public String deleteFilm() {
		log.info("appel de deleteFilm() avec id = "+ id);
				
		filmDAO.delete(id);
		return SUCCESS;
		
	}
	
	public String sauverFilm(){
		
		log.info("appel de sauverFilm() avec id = "+ editId);
//		Optional<Film> f=films.stream().filter(f1 -> f1.getId()==editId)
//							.findFirst();
		Film f= filmDAO.findByCle(this.editId);
		if (f ==null &&getEditId()==0)
			f=new Film();
		if (f!=null){
			f.setTitre(getEditTitre());
			f.setAnnee(getEditAnnee());
			f.setDescription(getEditDescription());
			f.setRealisateur(getEditRealisateur());
			f.setRating(getEditRating());
			
			filmDAO.save(f);
			log.info("film sauvé: "+f);
			return SUCCESS;
		}
		else {return ERROR;
	}}
	
	
}
