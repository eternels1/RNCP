package exercice_java_5_InterfaceGenerique.Geometrie;

public class Label implements IAffichable{
	private String texte ;

	public String getTexte() {return texte;}	
	public void setTexte(String texte) {this.texte = texte;}
	
public Label(String texte) {
		super();
		this.texte = texte;
	}
@Override
public void display() {
	System.out.println("je suis un label");
	
}
}
