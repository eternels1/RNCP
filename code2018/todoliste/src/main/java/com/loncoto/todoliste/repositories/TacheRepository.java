package com.loncoto.todoliste.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.todoliste.metier.Tache;

public interface TacheRepository extends PagingAndSortingRepository<Tache, Integer>{

	Page<Tache> findBylibelleContaining(String libelle,Pageable page);
	Page<Tache> findByprioriteGreaterThanEqual(int priorite,Pageable page);
	
}
