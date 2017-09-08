package com.khalid.springAcademy.beans;

import java.util.List;
import java.util.Random;

public class Luthier {
	
	
	
	public Violon genererViolon() {
		
		Random rd= new Random();
		Violon violontemp=new Violon();
		if (rd.nextBoolean()) {
			violontemp.setNom("stradivarius");
		}else {
			violontemp.setNom("violonPourri");
		}
		
		return violontemp;
	}

	
	

}
