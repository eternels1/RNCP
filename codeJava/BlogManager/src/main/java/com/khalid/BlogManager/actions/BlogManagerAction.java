package com.khalid.BlogManager.actions;

import java.util.Date;
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

public class BlogManagerAction extends ActionSupport
								implements ApplicationAware, ModelDriven<Post>{
	
	Logger log = LogManager.getLogger(BlogManagerAction.class);
	private GenericDAO<Auteur> auteurDAO;
	private GenericDAO<Post> postDAO;
	private List<Auteur> lstAuteurs;	
	private List<Post> lstPosts;
	private Post model;	
	public List<Auteur> getLstAuteurs() {
		log.info("Appel de getAuteurs()");
		if (lstAuteurs==null) {
			lstAuteurs=auteurDAO.findAll();
		}
		return lstAuteurs;
	}
	public List<Post> getLstPosts() {
		log.info("Appel de getPosts()");
		if (lstPosts==null) {
			lstPosts=postDAO.findAll();
		}
		for (Post p : lstPosts) {
					p.setAuteur(auteurDAO.findByCle(p.getAuteurId()));
				}
		return lstPosts;
	}
	
	@Override
	public Post getModel() {
		log.info("appel de getModel()");
		if (model==null) {
			model= new Post();
		}
		return model;
	}
	@Override
	public void setApplication(Map<String, Object> ctx) {
		this.auteurDAO=(GenericDAO<Auteur>) ctx.get("auteurDAO");
		this.postDAO=(GenericDAO<Post>)ctx.get("postDAO");
	}
	
	public String listePost() {		
		log.info("Appel de listePost()");
		return SUCCESS;		
	}
	
	public String editerPost() {
		
		Post m=getModel();
		log.info("Appel de editerPost() avec id = "+m.getId());
		
		Post p=postDAO.findByCle(m.getId());
		if (p !=null) {
			m.setTitre(p.getTitre());
			m.setCorps(p.getCorps());
			m.setAuteurId(p.getAuteurId());
			m.setDateCreation(p.getDateCreation());
			m.setDateEdition(p.getDateEdition());
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public String creatPost() {
		log.info("Appel du createPost()");
		Post p=getModel();		
		p.setDateCreation(new Date());
		p.setId(0);
		p.setTitre("Titre du Post");
		p.setCorps("Raconte ton histoire...");
		p.setAuteurId(1);
				
		return SUCCESS;
	}
	
	public String sauverPost() {
		
		log.info("Appel de sauverPost()");
		Post m=getModel();
		Post p= postDAO.findByCle(m.getId());
		if (p==null && m.getId()!=0) {
			return ERROR;
		}
		else {
			m.setDateEdition(new Date());
			if (m.getId()==0) {
				m.setDateCreation(new Date());
			}
			else {
				m.setDateCreation(p.getDateCreation());
			}
			postDAO.save(m);
			log.info("Post sauvé : "+m);		
		return SUCCESS;
		}
	}
	
	public String deletePost() {
		Post m= getModel();
		log.info("Appel de deletePost() avec id = "+m.getId());
		postDAO.delete(m.getId());
		
		return SUCCESS;
	}

}
