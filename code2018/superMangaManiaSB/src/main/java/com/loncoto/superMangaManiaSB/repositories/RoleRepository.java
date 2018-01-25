package com.loncoto.superMangaManiaSB.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.loncoto.superMangaManiaSB.metier.Role;

@Service
public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	Role findByRoleName(String rolename);

}
