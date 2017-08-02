package com.khalid.BlogManager.actions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.khalid.BlogManager.metier.Auteur;
import com.khalid.BlogManager.metier.Post;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.GenericDAO;

public class AuteurAction extends ActionSupport 
							implements ApplicationAware, ModelDriven<Auteur>{

	Logger log = LogManager.getLogger(AuteurAction.class);
	private GenericDAO<Auteur> auteurDAO;
	private GenericDAO<Post>   postDAO;
	private Auteur model;
	private List<Auteur> lstAuteurs;
	
	
	public GenericDAO<Auteur> getAuteurDAO() {return auteurDAO;}
	public GenericDAO<Post> getPostDAO() {return postDAO;}
	public List<Auteur> getLstAuteurs() {
		log.info("Appel de getAuteurs()");
		if (lstAuteurs==null) {
			lstAuteurs=auteurDAO.findAll();
		}
		return lstAuteurs;}

	public String listeAuteur() {		
		log.info("Appel de listeAuteur()");		
		return SUCCESS;
	}
	
	public String deleteAuteur() {
		log.info("Appel de deleteAuteur() avec id : "+getModel().getId());
		auteurDAO.delete(getModel().getId());		
		return SUCCESS;
	}
	
	public String editerAuteur() {
		log.info("Appel de editerAuteur() avec id = "+ this.getModel().getId());
		Auteur m=getModel();
		Auteur a= auteurDAO.findByCle(this.getModel().getId());
		if (a!=null) {
			m.setNom(a.getNom());
			m.setPrenom(a.getPrenom());
			m.setEmail(a.getEmail());
			
			return SUCCESS;
		}
		else {
			return ERROR;
		}		
	}
	
	
	@Override
	public Auteur getModel() {
		log.info("Appel de getModel()");
		if (model==null) {
			model=new Auteur();
		}
		return model;
	}

	@Override
	public void setApplication(Map<String, Object> ctx) {
		this.auteurDAO=(GenericDAO<Auteur>)ctx.get("auteurDAO");
		this.postDAO=(GenericDAO<Post>)ctx.get("postDAO");
	}
	
	

}
