package gestionerreurjava;

import java.io.Reader;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		double tempCelcus;
		double tempFareinheit;
		double tempKelvin;
		String tempSaisie;
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Quel est la temp�rature?(C pour Celcius/ F pour Faranheit/ K pour Kelvin)");
		
		tempSaisie= reader.nextLine();
		
		if (tempSaisie.toUpperCase().charAt(tempSaisie.length()-1)=='C' ) {			
			tempCelcus= Double.parseDouble(tempSaisie.substring(0, tempSaisie.length()-1));	
			System.out.println(tempCelcus+" degr� celsius\n"+
							(tempCelcus+ 273.15)+" degr� Kelvin\n"+
							(tempCelcus*9.0/5.0+32)+" degr� Faranheit");
		}
		
		if (tempSaisie.toUpperCase().charAt(tempSaisie.length()-1)=='F') {
			tempFareinheit= Double.parseDouble(tempSaisie.substring(0, tempSaisie.length()-1));
			System.out.println(tempFareinheit+" degr� Faranheit\n"+
					((tempFareinheit+ 459.67) / 1.8)+" degr� Kelvin\n"+
					((tempFareinheit - 32) * 5.0/9.0)+" degr� Celcius");
		}
		
		if (tempSaisie.toUpperCase().charAt(tempSaisie.length()-1)=='K') {
			tempKelvin= Double.parseDouble(tempSaisie.substring(0, tempSaisie.length()-1));
			System.out.println(tempKelvin+" degr� Kelvin\n"+
					((tempKelvin* 9.0/5.0) - 459.67)+" degr� Faranheit\n"+
					((tempKelvin- 273.15)+" degr� Celcius"));
		}
		
		
		    
		
		
		
	}
	
	
//	public static double recuptemp(String tempsaisie, char indic) {
//		if (tempsaisie.toUpperCase().charAt(tempsaisie.length()-1)==indic) {			
//			return Double.parseDouble(tempsaisie.substring(0, tempsaisie.length()-1));			
//		}
	
		
	}
	
	


