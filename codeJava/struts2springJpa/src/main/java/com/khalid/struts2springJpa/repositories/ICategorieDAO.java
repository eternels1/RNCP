package com.khalid.struts2springJpa.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.khalid.struts2springJpa.metier.Categorie;

public interface ICategorieDAO {

	List<Categorie> findAll();

	Categorie findByID(int id);

	Categorie save(Categorie c);

	void delete(int id);

}