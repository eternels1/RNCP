package com.khalid.struts2springJpa.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.khalid.struts2springJpa.metier.Produit;

public interface IProduitDAO {

	List<Produit> findAll();
	List<Produit> findAll(boolean withCategories);
	Produit findByID(int id);
	Produit save(Produit p);
	void delete(int id);

}