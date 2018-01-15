package com.loncoto.firstSecurity.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.firstSecurity.metier.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

}
