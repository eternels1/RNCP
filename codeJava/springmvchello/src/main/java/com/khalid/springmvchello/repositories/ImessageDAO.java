package com.khalid.springmvchello.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.khalid.springmvchello.metier.Message;

public interface ImessageDAO {

	List<Message> findAll();
	Message findById(int id);
	List<Message> searchInTitre(String searchTerm);

}