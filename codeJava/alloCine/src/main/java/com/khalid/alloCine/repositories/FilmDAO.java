package com.khalid.alloCine.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.khalid.alloCine.metier.Film;

public class FilmDAO implements IFilmDAO {
	
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	
	@Override
	@Transactional
	public List<Film> findAll(){
		return em.createQuery("from Film", Film.class).getResultList();
	}
	
	@Override
	@Transactional
	public Film findByID(int id) {
		return em.find(Film.class, id);
	}
	
	@Override
	@Transactional
	public Film save(Film f) {
		if (f.getId()==0) {
			em.persist(f);
		}
		else {
			f= em.merge(f);
		}
		return f;
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		Film f= em.find(Film.class, id);
		if (f!=null) {
			em.remove(f);
		}
	}
	

}
