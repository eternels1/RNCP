package com.khalid.springmvchello.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khalid.springmvchello.metier.Message;


@Service
public class MessageDAO implements ImessageDAO {

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	@Transactional
	public List<Message> findAll(){
		return em.createQuery("from Message", Message.class)
				.getResultList();
	}

	@Override
	@Transactional
	public Message findById(int id) {
		return em.find(Message.class, id);
	}
	

	@Override
	@Transactional
	public List<Message> searchInTitre(String searchTerm){
		TypedQuery<Message> q= 
				em.createQuery(
						"select m from Message as m where m.titre like :searchterm",
						Message.class);
		q.setParameter("searchterm", "%"+searchTerm+"%");
		
		return q.getResultList();
	}
	
}
