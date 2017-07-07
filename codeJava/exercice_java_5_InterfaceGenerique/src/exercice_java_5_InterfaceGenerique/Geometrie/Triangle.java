package exercice_java_5_InterfaceGenerique.Geometrie;

public class Triangle extends Figure implements IAffichable{
	
	private int longeur;
	private int largeur;

	public int getLongeur() {
		return longeur;
	}
	public void setLongeur(int longeur) {
		this.longeur = longeur;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public Triangle(String stylebord, String stylecentre, int longeur, int largeur) {
		super(stylebord, stylecentre);
		setLongeur(longeur);
		setLargeur(largeur);
	}
	@Override
	public void display() {
		System.out.println("je suis un : Triangle");
		
	}

	
	
	

}
