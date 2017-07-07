package exercice_java_5_InterfaceGenerique.Geometrie;

public class Rectangle extends Figure implements IAffichable {
	public Rectangle(String stylebord, String stylecentre, int longeur, int hauteur) {
		super(stylebord, stylecentre);
		setHauteur(hauteur);
		setLongeur(longeur);		
	}
	public int getLongeur() {return longeur;}
	public void setLongeur(int longeur) {this.longeur = longeur;}
	public int getHauteur() {return hauteur;}
	public void setHauteur(int hauteur) {this.hauteur = hauteur;}
	
	private int longeur;
	private int hauteur;
	
	@Override
	public void display() {
		System.out.println("je suis un Rectangle");
		
	}
	
	
	
	

}
