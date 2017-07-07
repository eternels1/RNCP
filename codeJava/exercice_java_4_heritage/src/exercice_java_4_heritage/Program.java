package exercice_java_4_heritage;

import exercice_java_4_heritage.metier.Client;
import exercice_java_4_heritage.metier.Employe;

public class Program {

	public static void main(String[] args) {
		
		Employe emp1 = new Employe("karim","kaz","test@yaho.fr",1900.56,"Chanteur");
		Client client1 = new Client("tarik","abarou","tes2@haot.com",49,300);
		
		
		System.out.println(emp1.toString()+"\n"+client1.contacter()+
				"\n"+emp1.contacter()+"\n"+client1.sauver()+"\n"+emp1.sauver());

	}

}
