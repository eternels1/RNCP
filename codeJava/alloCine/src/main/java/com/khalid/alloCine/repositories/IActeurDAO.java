package com.khalid.alloCine.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.khalid.alloCine.metier.Acteur;

public interface IActeurDAO {

	List<Acteur> findAll();

	Acteur findByID(int id);

}