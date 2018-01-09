package com.loncoto.Instagraph.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.Instagraph.metier.Tag;

public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {

}
