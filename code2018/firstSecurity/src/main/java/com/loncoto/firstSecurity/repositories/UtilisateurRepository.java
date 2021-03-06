package com.loncoto.firstSecurity.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.loncoto.firstSecurity.metier.Utilisateur;


@RepositoryRestResource
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface UtilisateurRepository extends PagingAndSortingRepository<Utilisateur, Integer> {

	
	
	
	
}
