package com.abarou.AgenceVoyage.actions;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.abarou.AgenceVoyage.metier.Voyage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.GenericDAO;

public class VoyageAction extends ActionSupport 
							implements ApplicationAware, ModelDriven<Voyage>{
	
	Logger log = LogManager.getLogger(VoyageAction.class);
	private GenericDAO<Voyage> voyageDAO;
	private List<Voyage> voyages;		
	public List<Voyage> getVoyages() {
		log.info("Appel de getVoyages()");
		if (voyages==null) {
			voyages= voyageDAO.findAll();
		}
		return voyages;
	}

	private Voyage model;
	@Override
	public Voyage getModel() {
		log.info("appel de getModel()");
		if (model==null) {
			model= new Voyage();
		}
		return model;
	}

	public String listeVoyage() {
		log.info("Appel de listeVoyage()");
		return SUCCESS;
	}
	
	public String editerVoyage() {
		
		Voyage m=getModel();
		log.info("Appel de editerVoyage() avec id = "+this.getModel().getId());
		
		Voyage v= voyageDAO.findByCle(this.getModel().getId());
		if (v !=null) {
			m.setLibelle(v.getLibelle());
			m.setDestination(v.getDestination());
			m.setPrix(v.getPrix());
			m.setAgence(v.getAgence());
			m.setPasseport(v.isPasseport());
			m.setDateDepart(v.getDateDepart());
			m.setDateArrivee(v.getDateArrivee());
				return SUCCESS;
		}else
		{ return ERROR;}		
	}
	
	public String creerVoyage() {
		log.info("Appel de creerVoyage() ");
		Voyage m= getModel();
		
		m.setId(0);
		m.setLibelle("un libelle...");
		m.setDestination("une destination ...");
		m.setPrix(00.00);
		m.setAgence("une agence...");
		m.setPasseport(false);
		LocalDate today= LocalDate.now();
		LocalDate inOneWeek= today.plusDays(7);
		m.setDateDepart(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		m.setDateArrivee(Date.from(inOneWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		return SUCCESS;	
		
	}
	
	public String deleteVoyage() {
		log.info("Appel de deleteVoyage(); avec id = "+getModel().getId());
		voyageDAO.delete(getModel().getId());
		return SUCCESS;
	}
	
	public String sauverVoyage() {
		Voyage m=getModel();
		log.info("appel de sauverVoyage(); avec id = " +getModel().getId());
		
		Voyage v= voyageDAO.findByCle(m.getId());
		if (v==null &&m.getId()!=0) {
			return ERROR;
		}else {
			voyageDAO.save(m);
			log.info("Voyage sauvé : "+m);
			return SUCCESS;
		}
	}
		
	@Override
	public void setApplication(Map<String, Object> ctx) {
		this.voyageDAO=(GenericDAO<Voyage>) ctx.get("voyagesDAO");
	}

}
