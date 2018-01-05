package com.loncoto.todoliste.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.todoliste.metier.Tache;

public interface TacheRepository extends PagingAndSortingRepository<Tache, Integer>{

	List<Tache> findBylibelleContaining(String libelle);
	List<Tache> findByprioriteGreaterThanEqual(int priorite);
	
}
