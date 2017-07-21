package TodoManager.metier;

public class Tache {
	private int id;
	private String description;
	private int priorite;
	private String contexte;
	private boolean finished;	
	
	public Tache() {this(0,"",0,"",false);}
	public Tache(int id, String description, int priorite, String contexte, boolean finished) {
		setContexte(contexte);
		setDescription(description);
		setFinished(finished);
		setId(id);
		setPriorite(priorite);
	}	
	
	@Override
	public String toString() {
		return "Tache [id=" + id + ", description=" + description + ", priorite=" + priorite + ", contexte=" + contexte
				+ ", finished=" + finished + "]";
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getPriorite() {return priorite;}
	public void setPriorite(int priorite) {this.priorite = priorite;}
	public String getContexte() {return contexte;}
	public void setContexte(String contexte) {this.contexte = contexte;}
	public boolean isFinished() {return finished;}
	public void setFinished(boolean finished) {this.finished = finished;}
	
	

}
