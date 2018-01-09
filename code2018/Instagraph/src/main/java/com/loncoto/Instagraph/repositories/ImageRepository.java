package com.loncoto.Instagraph.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.Instagraph.metier.Image;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long>,
										ImageRepositoryCustom{

}
