package com.loncoto.firstSecurity.repositories;

import org.springframework.transaction.annotation.Transactional;

import com.loncoto.firstSecurity.metier.Role;
import com.loncoto.firstSecurity.metier.Utilisateur;


public interface IInternalRepository {

	long countUsers();

	Role createRole(String roleName);

	Utilisateur createUser(String userName, String password, Role... roles);

}