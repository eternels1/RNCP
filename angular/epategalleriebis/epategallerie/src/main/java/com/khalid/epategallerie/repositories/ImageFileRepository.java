package com.khalid.epategallerie.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.khalid.epategallerie.metier.ImageFile;

public interface ImageFileRepository extends CrudRepository<ImageFile, Integer>, 
									ImageFileRepositoryCustom {

	@Query("select distinct i from ImageFile as i left join fetch i.tags")
	Iterable<ImageFile> findAllFull();
	
	
	Iterable<ImageFile> findByNameContaining(String name);
	
	@Query("select distinct i from ImageFile as i left join fetch i.tags where i.name like :search")
	Iterable<ImageFile> findFullByNameContaining(@Param("search")String name);
}
