package fonctionJava;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Quel est votre nom?");
		String saisie= reader.nextLine();
		salutation(saisie);
	}
	
	private static void salutation(String nom) {
		System.out.println("bonjour, "+ nom);

	}

}
