package com.khalid.Exercice_JPA_2.utils;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class Horodateur {
	
	@PrePersist  // avant insertion
	public void setCreation(Horodatable entity) {
		System.out.println("appel de setCreation sur " + entity);
		LocalDateTime ldt = LocalDateTime.now();
		entity.setDateCreation(ldt);
		entity.setDateEdition(ldt);
	}
	
	@PreUpdate // avant update
	public void setMiseAJour(Horodatable entity) {
		System.out.println("appel de setMiseAJour sur " + entity);
		LocalDateTime ldt = LocalDateTime.now();
		entity.setDateEdition(ldt);
	}

}
