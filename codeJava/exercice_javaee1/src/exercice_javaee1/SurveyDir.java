package exercice_javaee1;


import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SurveyDir implements Runnable {

	String cheminFichier;
	private boolean arreteToi;
	
	@Override
	public String toString() {
		return "SurveyDir [cheminFichier=" + cheminFichier + "]";
	}

	public SurveyDir(String cheminFichier) {
		super();
		this.cheminFichier = cheminFichier;
		setArreteToi(false);		
	}

	public boolean isArreteToi() {return arreteToi;}
	public void setArreteToi(boolean arreteToi) {this.arreteToi = arreteToi;}

	@Override
	public void run() {

		File rep= new File(cheminFichier);
		String[] listOld= rep.list();
		do {
 
			
			String[] currentList= rep.list();
			
			List<String> lstrepOld = Arrays.asList(listOld);
			List<String> lstrepCurrent= Arrays.asList(currentList);
			
			for (String filename : currentList) {
				if (!lstrepOld.contains(filename)) {
					System.out.println("Nouveau fichier ou dossier :"+filename);	
				}	
				}
			
			for (String filename : listOld) {
				if (!lstrepCurrent.contains(filename)) {
					System.out.println("Fichier ou dossier effacé :"+filename);	
				}	
			}
			
			
			
			listOld=currentList;	
			
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} while (!arreteToi);
			

	}

}
