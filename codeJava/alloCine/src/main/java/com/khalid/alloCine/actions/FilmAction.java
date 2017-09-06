package com.khalid.alloCine.actions;

import java.util.List;

import com.khalid.alloCine.metier.Acteur;
import com.khalid.alloCine.metier.Film;
import com.khalid.alloCine.metier.Realisateur;
import com.khalid.alloCine.repositories.IActeurDAO;
import com.khalid.alloCine.repositories.IEtiquetageDAO;
import com.khalid.alloCine.repositories.IFilmDAO;
import com.khalid.alloCine.repositories.IRealisateurDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FilmAction extends ActionSupport
						implements ModelDriven<Film>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Film model;
	@Override
	public Film getModel() {
		if (model==null) {
			model= new Film();
		}
		return model;
	}
	
	private int rid;
	public int getRid() {return rid;}
	public void setRid(int rid) {this.rid = rid;}

	private int fid;
	private int aid;	
	public int getFid() {return fid;}
	public void setFid(int fid) {this.fid = fid;}
	public int getAid() {return aid;}
	public void setAid(int aid) {this.aid = aid;}
	 
	
	public String removeActeurFromFilm() {
		etiquetageDAO.removeActeurFromFilm(fid, aid);
		return SUCCESS;
	}

	public String addActeurToFilm() {
		etiquetageDAO.addActeurToFilm(fid, aid);
		return SUCCESS;
	}
	

	private List<Realisateur> allRealisateurs;
	public List<Realisateur> getAllRealisateurs(){
		if (allRealisateurs==null) {
			this.allRealisateurs=realisateurDAO.findAll();
		}
		return allRealisateurs;
	}
	private List<Acteur> allActeurs;
	public List<Acteur> getAllActeurs(){
		return allActeurs;
	}
	private IFilmDAO filmDAO;
	public void setFilmDAO(IFilmDAO filmDAO) {
		this.filmDAO=filmDAO;
	}
	private IActeurDAO acteurDAO;
	public void setActeurDAO(IActeurDAO acteurDAO) {
		this.acteurDAO=acteurDAO;
	}
	private IRealisateurDAO realisateurDAO;
	public void setRealisateurDAO(IRealisateurDAO realisateurDAO) {
		this.realisateurDAO=realisateurDAO;
	}
	private IEtiquetageDAO etiquetageDAO;
	public void setEtiquetageDAO(IEtiquetageDAO etiquetageDAO) {
		this.etiquetageDAO=etiquetageDAO;
	}
	
	private List<Film> films;
	public List<Film> getFilms(){
		return films;
	}
	
	private List<Acteur> acteurs;
	public List<Acteur> getActeurs(){
		return acteurs;
	}
	
	public String liste() {
		this.films= filmDAO.findAll();
		return SUCCESS;
	}
	
	public String restliste() {
		this.films= filmDAO.findAll();
		return SUCCESS;
	}
	
	public String edit() {
		Film toEdit= filmDAO.findByID(getModel().getId());
		if (toEdit==null)return ERROR;
		
		this.model.setTitre(toEdit.getTitre());
		this.model.setSynopsys(toEdit.getSynopsys());
		this.model.setAnnee(toEdit.getAnnee());
		this.model.setRealisateur(toEdit.getRealisateur());
		this.model.setActeurs(toEdit.getActeurs());
		this.allActeurs=acteurDAO.findAll();
		
		rid= this.model.getRealisateur().getId();
		
		return SUCCESS;
	}
	
	public String create() {
		this.allRealisateurs=realisateurDAO.findAll();
		return SUCCESS;
	}
	
	public String save() {
		Realisateur r= realisateurDAO.findByID(rid);
		getModel().setRealisateur(r);
		filmDAO.save(getModel());
		
		return SUCCESS;
	}
	
	public String delete() {
		filmDAO.delete(getModel().getId());
		return SUCCESS;
	}

}
