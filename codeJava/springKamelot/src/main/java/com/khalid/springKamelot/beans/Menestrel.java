package com.khalid.springKamelot.beans;

public class Menestrel {

	public void chanterAvant(IChevalier chevalier) {
		System.out.println("Tralala sir " + chevalier.getNom()
							+ " part couragesement en quete !");
	}
	
	public void chanterApres(IChevalier chevalier) {
		System.out.println("Tralala sir " + chevalier.getNom()
							+ " revient joyeusement de quete !");
	}
}
