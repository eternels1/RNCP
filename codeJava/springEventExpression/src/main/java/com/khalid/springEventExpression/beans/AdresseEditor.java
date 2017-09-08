package com.khalid.springEventExpression.beans;

import java.beans.PropertyEditorSupport;

public class AdresseEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		// TODO Auto-generated method stub
		System.out.println("conversion de l'adresse vers texte");
		Adresse a= (Adresse)getValue();
		StringBuilder sb= new StringBuilder();
		
		sb	.append(a.getRue()).append(';')
			.append(a.getVille()).append(';')
			.append(a.getCodepostal()).append(';')
			.append(a.getPays());
		return sb.toString();
		
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text==null || text.isEmpty()) {
			throw new IllegalArgumentException("adresse incorect, null ou vide");			
		}
		
		String[] champs= text.split(";");
		if (champs.length !=4) {
			throw new IllegalArgumentException("adresse incorect, format invalide");
		}
		System.out.println("conversion de texte veers adresse");
		setValue(new Adresse(champs[0],champs[1], champs[2], champs[3]));
	}

	
}
