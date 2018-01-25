package com.loncoto.superMangaManiaSB.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.loncoto.superMangaManiaSB.metier.Manga;

@CrossOrigin(origins="http://localhost:4200")@RepositoryRestResource
public interface SuperMangaRepository extends PagingAndSortingRepository<Manga, Integer> {

	@Override@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CONTRIBUTOR')")
	Manga save(Manga entity);


}
