package com.khalid.visageLivre.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khalid.visageLivre.metier.Profil;

@Service
public class ProfilDAO implements IProfilDAO {
	
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}
	
	@Override
	@Transactional
	public List<Profil> findAll(){
		return em.createQuery("from Profil", Profil.class).getResultList();
	}
	
	@Override
	@Transactional
	public Profil findById(int id) {
		return em.find(Profil.class, id);
	}
	
	@Override
	@Transactional
	public Profil save(Profil p) {
		if (p.getId()==0) {
			em.persist(p);
		}
		else {
			p=em.merge(p);
		}
		return p;
	}	
	
	@Override
	@Transactional
	public void delete(int id) {
		Profil p=em.find(Profil.class, id);
		if (p!=null) {
			em.remove(p);
		}
	}
	
	@Override
	@Transactional
	public List<Profil> searchByName(String nom){
		TypedQuery<Profil> q=em.createQuery(
				"select p from Profil as p where p.nom like :searchTerm",
				Profil.class
				);
		q.setParameter("searchTerm", "%"+nom+"%");
		return q.getResultList();
	}

	@Override
	@Transactional
	public List<Profil> searchByTown(String searchTermVille) {
		TypedQuery<Profil> q=em.createQuery(
				"select p from Profil as p where p.ville like :searchTerm",
				Profil.class
				);
		q.setParameter("searchTerm", "%"+searchTermVille+"%");
		return q.getResultList();
	}
	
	@Override
	@Transactional
	public List<Profil> searchByNameAndTown(String searchTermName, String searchTermVille) {
		TypedQuery<Profil> q=em.createQuery(
				"select p from Profil as p where p.nom like :searchTermName AND p.ville like :searchTermVille",
				Profil.class
				);
		q.setParameter("searchTermName", "%"+searchTermName+"%");
		q.setParameter("searchTermVille", "%"+searchTermVille+"%");
		return q.getResultList();
	}

}
