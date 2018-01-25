package com.loncoto.superMangaManiaSB.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.loncoto.superMangaManiaSB.metier.Utilisateur;

@Service
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	Utilisateur findByUsername(String username);
}
