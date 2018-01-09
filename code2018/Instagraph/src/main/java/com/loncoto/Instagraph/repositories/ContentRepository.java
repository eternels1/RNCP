package com.loncoto.Instagraph.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.Instagraph.metier.Content;

public interface ContentRepository extends PagingAndSortingRepository<Content, Long> {

	
}
