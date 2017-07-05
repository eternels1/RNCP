package exercice_java_2_A;

public class Program {

	public static void main(String[] args) {
		System.out.println("le nbr de double pour la suite de double : "
				+ "15.5, 5.02, 56, -5, -15 est : "				
				+ fonctionexoA(15.5, 5.02, 56, -5, -15));

	}

	public static double fonctionexoA(double ... valeurs) {
		int compteur= 0;
		for (int i = 0; i < valeurs.length; i++) {
			if (valeurs[i]>0) {
				compteur++;
			}
		}
		return compteur;
	}
	
}
