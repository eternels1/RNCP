 package exercice_javaee1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Program {

	public static void main(String[] args) {
		
		List<SurveyDir> listSurvey = new ArrayList<>();
		
		try {
			Scanner scannerfichierconfig = new Scanner(new File("C:\\ConfigexojavaThread\\config.txt"));
			
			while (scannerfichierconfig.hasNextLine()) {
				listSurvey.add(new SurveyDir(scannerfichierconfig.nextLine()));
			}
			
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ExecutorService tpe = Executors.newFixedThreadPool(listSurvey.size());

		listSurvey.stream().forEach(s-> tpe.submit(s));
		
		System.out.println("appuyer sur entrée pour finir le thread!");
		Scanner reader = new Scanner(System.in);
		
		//attend la saisie utilisateur
		reader.nextLine();
		
		listSurvey.stream().forEach(s-> s.setArreteToi(true));
		
		tpe.shutdown();
		try {
			tpe.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("fini");
	}

}
