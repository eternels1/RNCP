package exercice_java_5_InterfaceGenerique.Geometrie;

public abstract class Figure {
	
	private String stylebord;
	private String stylecentre;
	
	public String getStylebord() {return stylebord;}
	public void setStylebord(String stylebord) {this.stylebord = stylebord;}
	public String getStylecentre() {return stylecentre;}
	public void setStylecentre(String stylecentre) {this.stylecentre = stylecentre;}
	
	public Figure(String stylebord, String stylecentre) {
		super();
		setStylebord(stylebord);
		setStylecentre(stylecentre);
	}
	@Override
	public String toString() {
		return "Figure [stylebord=" + stylebord + ", stylecentre=" + stylecentre + "]";
	}
	
	

}
