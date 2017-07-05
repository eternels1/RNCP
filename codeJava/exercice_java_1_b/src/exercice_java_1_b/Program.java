package exercice_java_1_b;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Saisir une valeur");
		
		String saisie = reader.nextLine();
		int taille= Integer.parseInt(saisie);
		
		for (int i = 0; i < taille ; i++) {System.out.print("*");}
		
		for (int i = 0; i < taille; i++) {
		
		System.out.println();		
		System.out.print("*");
		for (int x = 0; x < taille-i; x++) {System.out.print("-");}
		System.out.print("*");
		}
		System.out.print("\n**\n*");
		
		
	}

}
