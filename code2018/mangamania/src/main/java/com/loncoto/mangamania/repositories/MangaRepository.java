package com.loncoto.mangamania.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.mangamania.metier.Manga;

public interface MangaRepository extends PagingAndSortingRepository<Manga, Integer>{

	List<Manga> findByTitreContaining(String titre);
	Page<Manga> findByTitreContaining(String titre, Pageable page);
}
