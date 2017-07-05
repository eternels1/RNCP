package exercice_java_2_B;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("Entrée un mot : ");
		Scanner reader= new Scanner(System.in);
		String saisie= reader.nextLine();
		System.out.println(reverseword(saisie));
				

	}

	public static String reverseword(String valeur) {
		String temp= "";
//		StringBuffer sb = (new StringBuffer(valeur)).reverse();
//		temp=sb.toString();		
		
		for (int i = valeur.length()-1 ; i >= 0; i--) {			
			temp+= valeur.charAt(i);			
		}		
		return temp;
	}	
}
