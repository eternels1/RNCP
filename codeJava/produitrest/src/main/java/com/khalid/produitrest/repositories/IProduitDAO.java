package com.khalid.produitrest.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.khalid.produitrest.metier.Produit;

public interface IProduitDAO {

	List<Produit> findAll();

	Produit findById(int id);
	
	List<Produit> searchByName(String nom);

	Produit save(Produit p);

	void delete(int id);

}