package com.loncoto.NetFlux.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.loncoto.NetFlux.metier.Film;

public interface IFilmDepot {

	List<Film> findAll();
	Film findById(int id);
	void delete(int id);
	Film save(Film f);

}