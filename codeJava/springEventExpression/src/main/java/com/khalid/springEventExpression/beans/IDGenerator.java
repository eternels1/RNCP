package com.khalid.springEventExpression.beans;

public class IDGenerator {
	
	private int compteur;
	public IDGenerator(int valeurInitiale) {
		this.compteur=valeurInitiale;
	}
	
	public int nextID() {
		return compteur++;
	}

}
