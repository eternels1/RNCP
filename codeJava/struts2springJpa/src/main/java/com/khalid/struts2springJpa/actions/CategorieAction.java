package com.khalid.struts2springJpa.actions;

import java.util.List;

import com.khalid.struts2springJpa.metier.Categorie;
import com.khalid.struts2springJpa.repositories.ICategorieDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategorieAction 	extends ActionSupport 
							implements ModelDriven<Categorie>
{
	private Categorie model;	
	private ICategorieDAO categorieDAO;
	private List<Categorie> categories;
	
	public List<Categorie> getCategories() {return categories;}
	public ICategorieDAO getProduitDAO() {return categorieDAO;}
	public void setCategorieDAO(ICategorieDAO categorieDAO) {this.categorieDAO = categorieDAO;}
	
	public String liste() {
		this.categories=categorieDAO.findAll();
		return SUCCESS;
	}
	
	public String edit() {
		Categorie toEdit= categorieDAO.findByID((getModel().getId()));
		if (toEdit==null) {
			return ERROR;
		}
		this.model.setLibelle(toEdit.getLibelle());
	
		return SUCCESS;
	}
	
	public String create() {		
		return SUCCESS;
	}
	
	public String save() {
		categorieDAO.save(getModel());
		return SUCCESS;
	}
	
	public String delete() {
		categorieDAO.delete(getModel().getId());
		return SUCCESS;
	}
	@Override
	public Categorie getModel() {
		if (model==null) {
			model= new Categorie();
		}
		return model;
	}

}
