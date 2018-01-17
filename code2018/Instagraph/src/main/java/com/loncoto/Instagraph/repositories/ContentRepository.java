package com.loncoto.Instagraph.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.loncoto.Instagraph.metier.Content;

@RepositoryRestResource
public interface ContentRepository extends PagingAndSortingRepository<Content, Long> {

	
}
