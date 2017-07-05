package structureControle;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("Quelle heure est-il?");
		Scanner reader= new Scanner(System.in);
		
		String saisie=reader.nextLine();
		
		int heure= Integer.parseInt(saisie);
		
		if (heure < 11) {
			System.out.println("bonjour, café?");
		}
		else if(heure < 14) {
			System.out.println("bonjour, bon appétit");
		}
		else {
			System.out.println(("bien le bonjour!"));
		}
		
		
		
		
		
		

	}

}
