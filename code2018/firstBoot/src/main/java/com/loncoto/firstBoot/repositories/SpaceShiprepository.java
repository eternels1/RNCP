package com.loncoto.firstBoot.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.loncoto.firstBoot.metier.SpaceShip;

public interface SpaceShiprepository extends PagingAndSortingRepository<SpaceShip, Integer> {

}
