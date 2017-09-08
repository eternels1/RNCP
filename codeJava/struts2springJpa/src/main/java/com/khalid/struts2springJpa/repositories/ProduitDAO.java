package com.khalid.struts2springJpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import com.khalid.struts2springJpa.metier.Produit;

public class ProduitDAO implements IProduitDAO {
	
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/* (non-Javadoc)
	 * @see com.khalid.struts2springJpa.repositories.IProduitDAO#findAll()
	 */
	@Override
	@Transactional
	public List<Produit> findAll(boolean withCategories){
		if (withCategories) {
		return em.createQuery("select distinct(p) from Produit as p left join fetch p.categories", Produit.class).getResultList();
		}		
		else {
			return em.createQuery("from Produit", Produit.class).getResultList();
		}
	}
	@Override
	@Transactional
	public List<Produit> findAll(){			
		return findAll(false);				
	}

	@Override
	@Transactional
	public Produit findByID(int id) {
		
		return em.find(Produit.class, id);
	}

	@Override
	@Transactional
	public Produit save(Produit p) {
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
		Produit p = em.find(Produit.class, id);
		if (p!=null) {
			em.remove(p);
		}	
		
	}

}
