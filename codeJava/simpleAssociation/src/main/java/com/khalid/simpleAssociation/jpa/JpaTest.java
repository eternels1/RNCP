package com.khalid.simpleAssociation.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.khalid.simpleAssociation.beans.*;

public class JpaTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		test1(emf);

        input.nextLine();
		System.out.println("--------------------------------------");
		test3(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void test1(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		em.persist(new Genre(0, "sciencefiction"));
		em.persist(new Genre(0, "comédie"));
		em.persist(new Genre(0, "Série Z"));
		em.persist(new Genre(0, "Western"));
		em.persist(new Genre(0, "Peplum"));
		
		Acteur [] tabacteurs= new Acteur[5];
		tabacteurs[0] = new Acteur(0, "Willis", "bruce");
		tabacteurs[1] = new Acteur(0, "Stalonne", "Sylvester");
		tabacteurs[2] = new Acteur(0, "Norris", "Chuck");
		tabacteurs[3] = new Acteur(0, "Lee", "Jet");
		tabacteurs[4] = new Acteur(0, "Dujardin", "Jean");
		
		for (Acteur a : tabacteurs) {
			em.persist(a);
		}
		
		String[] sujet= {"donnuts","cowbow","alien","zombie","ninja"};
		String[] liaison= {"du","des","de","contre"};
		String [] action= {"attaque","rencontre","combat","etats d'ames"};
		Random rd= new Random();
		for (int i = 0; i < 10; i++) {
			String titre= action[rd.nextInt(action.length)]+" "+
			liaison[rd.nextInt(liaison.length)]+" "+
			 sujet[rd.nextInt(sujet.length)];
			
			
			Film f = new Film(0, titre, new Date(), rd.nextInt(100)+80);
			f.setGenreduFilm(em.find(Genre.class, rd.nextInt(5)+1));
			for (Acteur a : tabacteurs) {
				if (rd.nextBoolean()) {
					f.getLstActeurs().add(a);
				}
				
				em.persist(f);
			}
			
		}
		
		
		
		//---------------------------------------------------- 
		tx.commit();
		em.close();
	}


	public static void test2(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Film f1=em.find(Film.class, 1);
		System.out.println("Titre film = "+f1.getTitre());
		System.out.println("genre film = "+f1.getGenreduFilm().getLibelle());
		
		for (Acteur a : f1.getLstActeurs()) {
			System.out.println("acteur = "+a);
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Genre g1=em.find(Genre.class, 1);
		em.remove(g1);
		         
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
}
