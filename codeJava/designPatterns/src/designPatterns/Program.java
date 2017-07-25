package designPatterns;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
//		System.out.println("debut program");
//		
//		CustomConfig cf = CustomConfig.getInstance();
//		System.out.println("cf = "+cf);
//		
//		Scanner reader= new Scanner(System.in);
//		System.o ut.println("appuyer sur entrée pour continuer");
//		reader.nextLine();
//		
//		CustomConfig cf2= CustomConfig.getInstance();
//		System.out.println("cf2 = "+cf2);
//		
//		
//		cf.addValueToConfig("couleur", "red");
//		cf.addValueToConfig("database", "boutique");
//		cf.addValueToConfig("databasedriver", "com.mysql.jdbc.driver");
//		
//		cf.saveToPropertiesFile("config.properties"); 
//		
		
		Adresse a1= 
				new Adresse("221b baker street", "londres", "12345", "Angletterre");
		Client c1 = new Client("Sherlock Holmes", a1);
		Client c2 = new Client("Dr John Watson", a1);
		
		System.out.println(c1);
		System.out.println(c2);
		
		c2.setAdresse(new Adresse("nkb", "kbbbbbbbu", "684", "lkjb"));
		
	}

}
