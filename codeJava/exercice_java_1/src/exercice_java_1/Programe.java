package exercice_java_1;

import java.util.Scanner;

public class Programe {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		
		double note;
		double max=0;
		double min=0;
		double moy=0;
		int compteur=0;
		double total=0;
		
		System.out.println("Saisir chiffre(0=Exit)");
		do {
			compteur++;
			
			String saisie= reader.nextLine();	
			note =Double.parseDouble(saisie); 
			total= total+note;
			
			if (min==0) {min=note;}
			if (note>max) { max=note;} 
			if (note<min && note!=0) {min=note;}
			
			
			
		} while (note!=0);
		moy=total/compteur;
		System.out.println("Valeur Max : "+ max + "; \nValeur Min : "+min+
				"; \nValeur Moy : "+moy+";");
	}

}
