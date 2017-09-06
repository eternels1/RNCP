package com.khalid.visageLivre.repositories;

import java.util.List;

import com.khalid.visageLivre.metier.Profil;

public interface IProfilDAO {

	List<Profil> findAll();

	Profil findById(int id);

	Profil save(Profil p);

	void delete(int id);

	List<Profil> searchByName(String nom);

	List<Profil> searchByTown(String searchTermVille);

	List<Profil> searchByNameAndTown(String searchTermName, String searchTermVille);

}