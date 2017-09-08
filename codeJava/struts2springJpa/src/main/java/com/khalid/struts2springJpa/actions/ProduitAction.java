package com.khalid.struts2springJpa.actions;

import java.util.List;

import com.khalid.struts2springJpa.metier.Produit;
import com.khalid.struts2springJpa.repositories.IProduitDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProduitAction 	extends ActionSupport 
							implements ModelDriven<Produit>
{
	private Produit model;	
	private IProduitDAO produitDAO;
	private List<Produit> produits;
	
	public List<Produit> getProduits() {return produits;}
	public IProduitDAO getProduitDAO() {return produitDAO;}
	public void setProduitDAO(IProduitDAO produitDAO) {this.produitDAO = produitDAO;}
	
	public String liste() {
		this.produits=produitDAO.findAll();
		return SUCCESS;
	}
	
	public String edit() {
		Produit toEdit= produitDAO.findByID((getModel().getId()));
		if (toEdit==null) {
			return ERROR;
		}
		this.model.setNom(toEdit.getNom());
		this.model.setPrix(toEdit.getPrix());
		this.model.setPoids(toEdit.getPoids());
		this.model.setCategories(toEdit.getCategories());
		return SUCCESS;
	}
	
	public String create() {		
		return SUCCESS;
	}
	
	public String save() {
		produitDAO.save(getModel());
		return SUCCESS;
	}
	
	public String delete() {
		produitDAO.delete(getModel().getId());
		return SUCCESS;
	}
	@Override
	public Produit getModel() {
		if (model==null) {
			model= new Produit();
		}
		return model;
	}

}
