package com.abarou.videoTech.actions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.abarou.videoTech.metier.Film;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.GenericDAO;

public class FilmActionMD extends ActionSupport 
							implements ApplicationAware, ModelDriven<Film>{
	Logger log = LogManager.getLogger(FilmAction.class);
	private GenericDAO<Film> filmDAO;
	@Override
	public void setApplication(Map<String, Object> ctx) {
		this.filmDAO= (GenericDAO<Film>) ctx.get("filmDAO");
	}
	
	private List<Film> films;
	public List<Film> getFilms() {
		log.info("appel de getFilm()");
		if (films==null) {
			films=filmDAO.findAll();
		}
	return films; 
	}
	
	private Film model;
	@Override
	public Film getModel() {
		log.info("appel de getModel()");
		if (model==null) {
			model= new Film();
		}
		return model;
	}		
public String listeFilm() {
		
		//lister les films et repondra à l'url /film
		log.info("appel de listeFilm()");
		return SUCCESS;
	}
public String editerFilm() {
	Film m=getModel();
	log.info("appel dediterFilm() avec id = "+this.getModel().getId());

	Film f= filmDAO.findByCle(this.getModel().getId());
	if (f !=null) {
		m.setTitre((f.getTitre()));
		m.setAnnee(f.getAnnee());
		m.setRealisateur(f.getRealisateur());
		m.setDescription(f.getDescription());
		m.setRating(f.getRating());		
		return SUCCESS;
	}
	else {
		return ERROR;
	}
}

public String creerFilm() {
	log.info("appel creerFilm()");

	Film m= getModel();
	
		m.setId(0);
		m.setTitre("un titre..");
		m.setAnnee(LocalDate.now().getYear());
		m.setRealisateur("realisateur");
		m.setRating(1);
		m.setDescription("un film...");
		
		return SUCCESS;
	
}

public String deleteFilm() {
	log.info("appel de deleteFilm() avec id = "+ getModel().getId());
			
	filmDAO.delete(getModel().getId());
	return SUCCESS;
	
}

public String sauverFilm(){
	Film m=getModel();
	log.info("appel de sauverFilm() avec id = "+ getModel().getId());

	Film f= filmDAO.findByCle(m.getId());
	if (f ==null && m.getId()!=0)
		return ERROR;	
		filmDAO.save(m);
		log.info("film sauvé: "+m);
		return SUCCESS;
	}



}
