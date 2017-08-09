package com.khalid.jpaHeritageSingleTable.beans;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(callSuper=true)
public class Client extends Personne {

	private String email;
	private double soldCompte;
	
	public Client(int id, String nom, String prenom, String email, double soldCompte) {
		super(id, nom, prenom);
		this.email = email;
		this.soldCompte = soldCompte;
	}
	

	
	
}
