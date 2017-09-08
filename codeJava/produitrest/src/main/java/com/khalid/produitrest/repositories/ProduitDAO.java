package com.khalid.produitrest.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khalid.produitrest.metier.Produit;

@Service
public class ProduitDAO implements IProduitDAO {

	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {this.em=em;}
	

	@Override
	@Transactional
	public List<Produit> findAll(){
		return em.createQuery("from Produit", Produit.class).getResultList();
	}
	
	@Override
	@Transactional
	public Produit findById(int id) {
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
		Produit p=em.find(Produit.class, id);
		if (p!=null) {
			em.remove(p);
		}
	}
	
	@Override
	@Transactional
	public List<Produit> searchByName(String nom) {
		TypedQuery<Produit> q =em.createQuery(
					"select p from Produit as p where p.nom like :searchTerm",
					Produit.class);
		q.setParameter("searchTerm", "%" +nom+"%");
		return q.getResultList();
	}
	
	

}
