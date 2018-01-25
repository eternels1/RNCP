package com.loncoto.superMangaManiaSB.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.loncoto.superMangaManiaSB.metier.ImageCouv;
@RepositoryRestResource
public interface SuperImageCouvRepository extends SuperImageCouvRepositoryCustom, 
										PagingAndSortingRepository<ImageCouv, Integer> {

	
}
