package com.khalid.alloCine.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.khalid.alloCine.metier.Film;

public interface IFilmDAO {

	List<Film> findAll();

	Film findByID(int id);

	Film save(Film f);

	void delete(int id);

}