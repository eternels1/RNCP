package com.khalid.struts2springJpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.khalid.struts2springJpa.metier.Categorie;

public class CategorieDAO implements ICategorieDAO {
	
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	
	@Override
	@Transactional
	public List<Categorie> findAll(){
		return em.createQuery("from Categorie", Categorie.class).getResultList();
	}
	
	@Override
	@Transactional
	public Categorie findByID(int id) {
		
		return em.find(Categorie.class, id);
	}

	
	@Override
	@Transactional
	public Categorie save(Categorie c) {
		if (c.getId()==0) {
			em.persist(c);
		}
		else {
			c=em.merge(c);
		}
		return c;
	}

	
	@Override
	@Transactional
	public void delete(int id) {
		Categorie p = em.find(Categorie.class, id);
		if (p!=null) {
			em.remove(p);
		}	
		
	}

}
