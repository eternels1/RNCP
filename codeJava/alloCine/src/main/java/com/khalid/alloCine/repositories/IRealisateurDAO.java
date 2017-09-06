package com.khalid.alloCine.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.khalid.alloCine.metier.Realisateur;

public interface IRealisateurDAO {

	List<Realisateur> findAll();

	Realisateur findByID(int id);

}