package com.khalid.alloCine.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.khalid.alloCine.metier.Acteur;
import com.khalid.alloCine.metier.Film;

public class EtiquetageDAO implements IEtiquetageDAO {
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	@Override
	@Transactional
	public void removeActeurFromFilm(int fid, int aid) {
		Acteur a=em.find(Acteur.class, aid);
		Film f=em.find(Film.class, fid);
		if (a==null || f==null) {return;}
		f.getActeurs().remove(a);
	}
	
	@Override
	@Transactional
	public void addActeurToFilm(int fid, int aid) {
		Acteur a= em.find(Acteur.class, aid);
		Film f= em.find(Film.class, fid);
		if (a==null || f==null) {return;}
		
		if (!f.getActeurs().contains(a))f.getActeurs().add(a);	
	}
}
