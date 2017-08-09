package com.khalid.springKamelot.beans;

import java.util.Random;

public class QueteEpique implements IQuete{

	private String description;
	private double difficulte;
	private Random rd;
	
	@Override
	public String getDescription() {
		return description;
	}

	public QueteEpique() {
		rd= new Random();
	}
	
	
	public double getDifficulte() {return difficulte;}

	public void setDifficulte(double difficulte) {this.difficulte = difficulte;}

	public void setDescription(String description) {this.description = description;}

	
	
	@Override
	public String toString() {
		return "QueteEpique [description=" + description + ", difficulte=" + difficulte + "]";
	}

	@Override
	public boolean realiserQuete(double competence) {
		// TODO Auto-generated method stub
		return rd.nextDouble()*competence>difficulte &&
				rd.nextDouble()*competence>difficulte;
	}

}
