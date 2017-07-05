package exercice_java_2_D;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("Saisir une phrase pour trouver la fréquence des voyelles :");
		Scanner reader = new Scanner(System.in);
		
		String saisie= reader.nextLine();
		System.out.println(statistiquePhrase(saisie));

	}
	
	public static String statistiquePhrase(String saisie) {
		String reponse="";
		int compteurVoyelle= 0;
		int compteurVoyelleA= 0;
		int compteurVoyelleE= 0;
		int compteurVoyelleI= 0;
		int compteurVoyelleO= 0;
		int compteurVoyelleU= 0;
		int compteurVoyelleY= 0;
		for (int i = 0; i < saisie.length(); i++) {
			if (saisie.charAt(i)=='a' || saisie.charAt(i)=='o'|| saisie.charAt(i)=='u'
					|| saisie.charAt(i)=='e'|| saisie.charAt(i)=='i'|| saisie.charAt(i)=='y') {
								
				compteurVoyelle++;				
				
			}			
		}
		
		compteurVoyelleA=nbrvoyelespecifique('a',saisie.toLowerCase());
		compteurVoyelleE=nbrvoyelespecifique('e',saisie.toLowerCase());
		compteurVoyelleI=nbrvoyelespecifique('i',saisie.toLowerCase());
		compteurVoyelleO=nbrvoyelespecifique('o',saisie.toLowerCase());
		compteurVoyelleU=nbrvoyelespecifique('u',saisie.toLowerCase());
		compteurVoyelleY=nbrvoyelespecifique('y',saisie.toLowerCase());
		
		double statA= ((double)compteurVoyelleA/compteurVoyelle)*100;
		double statE= ((double)compteurVoyelleE/compteurVoyelle)*100;
		double statI= ((double)compteurVoyelleI/compteurVoyelle)*100;
		double statO= ((double)compteurVoyelleO/compteurVoyelle)*100;
		double statU= ((double)compteurVoyelleU/compteurVoyelle)*100;
		double statY= ((double)compteurVoyelleY/compteurVoyelle)*100;
		
		
		
		reponse="Le nombre de voyelle totale est de :  "+compteurVoyelle+"\nle pourcentage de A est : "
				+statA+"\nle pourcentage de E est : "+statE+"\nle pourcentage de I est : "+statI
				+"\nle pourcentage de O est : "+statO+"\nle pourcentage de U est : "+statU
				+"\nle pourcentage de Y est : "+statY;
		
		return reponse;
		
	}
	
	public static int nbrvoyelespecifique(char lettre, String saisie) {
		int compteurVoyelleSpec=0;
		for (int j = 0; j < saisie.length(); j++) {
			if (saisie.charAt(j)==lettre) {
			compteurVoyelleSpec++;}
		}
		
		return compteurVoyelleSpec;
		
	}

}
