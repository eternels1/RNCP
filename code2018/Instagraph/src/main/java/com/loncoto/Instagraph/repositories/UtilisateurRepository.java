package com.loncoto.Instagraph.repositories;

import org.springframework.data.repository.CrudRepository;

import com.loncoto.Instagraph.metier.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

	Utilisateur findByUsername(String username);
}
