package com.khalid.alloCine.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.khalid.alloCine.metier.Realisateur;

public class RealisateurDAO implements IRealisateurDAO {
	
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	@Override
	@Transactional
	public List<Realisateur> findAll(){
		return em.createQuery("from Realisateur",Realisateur.class).getResultList();
	}
	
	@Override
	@Transactional
	public Realisateur findByID(int id) {
		return em.find(Realisateur.class, id);
	}
	

}
