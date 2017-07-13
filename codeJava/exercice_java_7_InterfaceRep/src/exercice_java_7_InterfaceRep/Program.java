package exercice_java_7_InterfaceRep;

import exercice_java_7_InterfaceRep.gui.FenetrePrincipale;
import exercice_java_7_InterfaceRep.metier.Contact;
import exercice_java_8_ReflexionBean.CsvMagician;

public class Program {

	public static void main(String[] args) {
		FenetrePrincipale fp = new FenetrePrincipale();
		fp.setVisible(true);
		Contact c=new Contact(1,"Abarou","Said","abarousaid@laposte.net",'m',38,true,"Nourdin");
		
		System.out.println(CsvMagician.beanToCsv(c));
	}

}
