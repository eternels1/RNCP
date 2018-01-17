package com.loncoto.Instagraph.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.loncoto.Instagraph.metier.Tag;

@RepositoryRestResource
public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {

}
