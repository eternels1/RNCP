package com.loncoto.Instagraph.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.loncoto.Instagraph.metier.Image;

@RepositoryRestResource
public interface ImageRepository extends PagingAndSortingRepository<Image, Long>,
										ImageRepositoryCustom{

}
