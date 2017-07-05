package exercice_java_2_C;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("Saisisser un mot :");
		Scanner reader = new Scanner(System.in);
		
	    String sasie= reader.nextLine();
	    System.out.println(palindrome(sasie));
		

	}

	public static boolean palindrome(String saisie) {
		String temp= "";		
		for (int i = saisie.length()-1 ; i >= 0; i--) {			
			temp+= saisie.charAt(i);
		}
		
		if (saisie.equals(temp)) {
			return true;
		}
		else return false;
		
	}
	
	
}
