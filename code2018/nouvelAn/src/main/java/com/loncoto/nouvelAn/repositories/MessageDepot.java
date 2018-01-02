package com.loncoto.nouvelAn.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loncoto.nouvelAn.metier.Message;



@Service
public class MessageDepot implements IMessageDepot {

	//injection de l'entity manager
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Message> findAll(){
		//createQuery créer la requette , getResultList l'execute
		return em.createQuery("from Message", Message.class).getResultList();
	}


	@Override
	@Transactional
	public Message save(Message m) {
		if (m.getId()==0)
			em.persist(m); //insertion
		else 
			m= em.merge(m); //update
		return m;
	}
}
