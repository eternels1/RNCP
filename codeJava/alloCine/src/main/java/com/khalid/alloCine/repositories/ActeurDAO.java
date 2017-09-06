package com.khalid.alloCine.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.khalid.alloCine.metier.Acteur;

public class ActeurDAO implements IActeurDAO {
	
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	
	@Override
	@Transactional
	public List<Acteur> findAll(){
		return em.createQuery("from Acteur",Acteur.class).getResultList();
	}
	
	@Override
	@Transactional
	public Acteur findByID(int id) {
		return em.find(Acteur.class, id);
	}

}
