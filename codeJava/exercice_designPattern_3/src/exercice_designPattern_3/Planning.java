package exercice_designPattern_3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Planning {

	private int annee;
	private List<Rdv> lstRdv;
	
	
	
	public Planning ajoutRdv(LocalDateTime debutrdv, LocalDateTime finrdv) {
		
		Rdv rdvtemp= new Rdv(debutrdv, finrdv);
		
		
		if (lstRdv.size()!=0) {
			
			for (Rdv rdv : lstRdv) {
				if (!(rdvtemp.fin.isBefore(rdv.debut) || rdvtemp.debut.isAfter(rdv.fin))) {
					return this;
				}
			} 					
		}
		
		lstRdv.add(rdvtemp);
		
		return this;		
	}
	
public static class Rdv {
	
	private LocalDateTime debut;
	private LocalDateTime fin;
	
	public LocalDateTime getDebut() {return debut;}

	public void setDebut(LocalDateTime debut) {this.debut = debut;}

	public LocalDateTime getFin() {return fin;}

	public void setFin(LocalDateTime fin) {this.fin = fin;}

	public Rdv(LocalDateTime debut, LocalDateTime fin) {
	setDebut(debut);
	setFin(fin);
	}

	
	
	
	
}
}



