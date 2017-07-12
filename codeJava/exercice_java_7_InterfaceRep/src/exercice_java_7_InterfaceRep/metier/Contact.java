package exercice_java_7_InterfaceRep.metier;

import java.util.Comparator;
import java.util.function.Predicate;

public class Contact {
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private char genre;
	private int age;
	private boolean gold;
	private String referent;
		
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public char getGenre() {return genre;}
	public void setGenre(char genre) {this.genre = genre;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public boolean isGold() {return gold;}
	public void setGold(boolean gold) {this.gold = gold;}
	public String getReferent() {return referent;}
	public void setReferent(String referent) {this.referent = referent;}
	
	
	
	
	
	public static final Comparator<Contact> ID_SORT
	= (p1, p2) -> Integer.compare(p1.getId(), p2.getId());
	
	
public static final Comparator<Contact> NOM_SORT
	= (p1, p2) -> p1.getNom().compareTo(p2.getNom());
	
public static final Comparator<Contact> PRENOM_SORT
	= (p1, p2) -> p1.getPrenom().compareTo(p2.getPrenom());
	
	public static final Comparator<Contact> NOMPRENOM_SORT
	= (p1, p2) -> {
		int companomtemp = p1.getNom().compareTo(p2.getNom());
		if (companomtemp!=0) {return companomtemp;}
		else return p1.getPrenom().compareTo(p2.getPrenom());
	};
	
public static final Comparator<Contact> EMAIL_SORT
	= (p1, p2) -> p1.getEmail().compareTo(p2.getEmail());
	
	public static final Comparator<Contact> AGE_SORT
	= (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
	
	
	
	
	
	
	public static Predicate<Contact> getFilter(final String referentName) {
		if (referentName.equals("tous"))
			return p -> true;
		else
			return  p -> p.getReferent().equals(referentName);
	}
	public static Predicate<Contact> getFilterAgeRecherche (final int age){
		
		return p -> p.getAge()<=age;
		
	}
	
	public static Predicate<Contact> getGoldFilter(final boolean onlygold) {
		return c -> c.isGold() || !onlygold;
		
		// ce qui revient en fait à faire
//		if (onlygold)
//			return c->c.isGold();
//		else 
//			return true;				
	}
	
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", genre=" + genre
				+ ", age=" + age + ", gold=" + gold + ", referent=" + referent + "]";
	}
	public Contact() {		
		
	}

	public Contact(int id, String nom, String prenom, String email, char genre, int age, boolean gold,
			String referent) {		
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setGenre(genre);
		setAge(age);
		setGold(gold);
		setReferent(referent);
	}

}
