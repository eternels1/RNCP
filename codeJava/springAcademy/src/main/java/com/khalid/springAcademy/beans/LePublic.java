package com.khalid.springAcademy.beans;

import java.util.Random;

public class LePublic {
	
	public void applaudirAvant(IArtiste artiste) {
		System.out.println("clap clap clap... bravo "+artiste.getNom()+
				" ca a l'air vraiment bien");
	}
	
	public void applaudirApres(IArtiste artiste) {
		Random rd= new Random();
		if (rd.nextDouble()>0.5) {
			System.out.println("clap clap clap... bravo "+artiste.getNom()+
				" c'était vraiment bien");
		}else {
			System.out.println("bouuuu bouuuu c'était null "+
		artiste.getNom()+" rembourser nous!!!");
		}
		
	}

}
