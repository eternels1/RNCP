package exercice_java_5_InterfaceGenerique;



import java.util.ArrayList;

import exercice_java_5_InterfaceGenerique.Geometrie.IAffichable;
import exercice_java_5_InterfaceGenerique.Geometrie.Label;
import exercice_java_5_InterfaceGenerique.Geometrie.Rectangle;
import exercice_java_5_InterfaceGenerique.Geometrie.Triangle;

public class Program {

	public static void main(String[] args) {
		ArrayList<IAffichable> tabIaff = new ArrayList<>();
		
		tabIaff.add(new Triangle("*", "-",10 , 15));
		tabIaff.add(new Rectangle("*","-",16,10));
		tabIaff.add(new Label("testLabelattributtext"));
		
	    tabIaff.stream().forEachOrdered(a -> a.display());
	}

}
