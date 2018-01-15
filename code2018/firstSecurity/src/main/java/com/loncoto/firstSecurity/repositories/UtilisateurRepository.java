package com.loncoto.firstSecurity.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.firstSecurity.metier.Utilisateur;

public interface UtilisateurRepository extends PagingAndSortingRepository<Utilisateur, Integer> {

}
