package com.khalid.springEventExpression.beans;

import org.springframework.context.ApplicationEvent;

import org.springframework.context.ApplicationListener;

public class EcouteurGeneral implements ApplicationListener<ApplicationEvent>{

	
	private String nom;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}

	@Override
	public void onApplicationEvent(ApplicationEvent evt) {
		System.out.println(getNom()+ " a recu l'événement "+ evt);
		
	}
	
	

}
