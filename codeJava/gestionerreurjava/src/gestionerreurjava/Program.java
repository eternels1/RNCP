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
		
		System.out.println("Quel est la température?(C pour Celcius/ F pour Faranheit/ K pour Kelvin)");
		
		tempSaisie= reader.nextLine();
		
		if (tempSaisie.toUpperCase().charAt(tempSaisie.length()-1)=='C' ) {			
			tempCelcus= Double.parseDouble(tempSaisie.substring(0, tempSaisie.length()-1));	
			System.out.println(tempCelcus+" degré celsius\n"+
							(tempCelcus+ 273.15)+" degré Kelvin\n"+
							(tempCelcus*9.0/5.0+32)+" degré Faranheit");
		}
		
		if (tempSaisie.toUpperCase().charAt(tempSaisie.length()-1)=='F') {
			tempFareinheit= Double.parseDouble(tempSaisie.substring(0, tempSaisie.length()-1));
			System.out.println(tempFareinheit+" degré Faranheit\n"+
					((tempFareinheit+ 459.67) / 1.8)+" degré Kelvin\n"+
					((tempFareinheit - 32) * 5.0/9.0)+" degré Celcius");
		}
		
		if (tempSaisie.toUpperCase().charAt(tempSaisie.length()-1)=='K') {
			tempKelvin= Double.parseDouble(tempSaisie.substring(0, tempSaisie.length()-1));
			System.out.println(tempKelvin+" degré Kelvin\n"+
					((tempKelvin* 9.0/5.0) - 459.67)+" degré Faranheit\n"+
					((tempKelvin- 273.15)+" degré Celcius"));
		}
		
		
		    
		
		
		
	}
	
	
//	public static double recuptemp(String tempsaisie, char indic) {
//		if (tempsaisie.toUpperCase().charAt(tempsaisie.length()-1)==indic) {			
//			return Double.parseDouble(tempsaisie.substring(0, tempsaisie.length()-1));			
//		}
	
		
	}
	
	


