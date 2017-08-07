package com.khalid.JPAUniversity.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.khalid.JPAUniversity.beans.*;

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
		test2(emf);

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
		em.persist(new Matiere(0, "Histoire"));
		em.persist(new Matiere(0, "Mathématiques"));
		em.persist(new Matiere(0, "Sport"));
		em.persist(new Matiere(0, "Economie"));
		
		em.persist(new Professeur(0, "Jean", "Mouloud", 40000.00));
		em.persist(new Professeur(0, "Alain", "Gérard", 10000.00));
		em.persist(new Professeur(0, "Rambaud", "Arthur", 20000.00));
		
		Random rd= new Random();
		
		Cours [] tabCours= new Cours[10];
		
		for (int i = 0; i < 10; i++) {			
			Cours c = new Cours(0, "cours"+i, new Date(117, 5, rd.nextInt(20)+1), 
					new Date(117, 6, rd.nextInt(20)+1), rd.nextInt(10)+10);
			
			c.setMatiere(em.find(Matiere.class, rd.nextInt(4)+1));
			c.setProfesseur(em.find(Professeur.class, rd.nextInt(3)+1));
			
			tabCours[i]=c;
			em.persist(c);			
		}
		
		
		
		for (int i = 0; i < 100; i++) {
			
			Etudiant e= new Etudiant(0, "EtudNom"+i, "EtudPrénom"+i, "mailetudiand"+i+"@gmail.com");
			
			for (Cours c : tabCours) {
				if (rd.nextDouble()>0.8) {
					c.getLstEtudiants().add(e);
				}
			}
			
			em.persist(e);
			
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
		
		TypedQuery<Etudiant> queryEtudiants= em.createQuery(
				"select distinct e from Etudiant as e join e.lstCours as c where c.dateDebut > :startDate",
				Etudiant.class);
		
		queryEtudiants.setParameter("startDate", new Date(117,05,13));
		List<Etudiant> statEtutiand = queryEtudiants.getResultList();
		System.out.println("Liste des etudiants qui participe au moins a un cours demarrant apres le 13-06-2017 : ");
		for (Etudiant etudiant : statEtutiand) {			
				System.out.println(etudiant.getNom()+" "+etudiant.getPrenom());			
		}
		
		System.out.println("------------------------------------------------------------------");
		
		Query requette= em.createQuery("select c.libelle, count(e.id) from Cours as c join c.lstEtudiants"
				+ " as e group by c ");
		
		List<Object[]> statcours=requette.getResultList();
		
		System.out.println("liste des cours(libelle) avec leur nombre de participant");
		
		for (Object[] objects : statcours) {
			System.out.println(Arrays.toString(objects));
		}
		
		System.out.println("------------------------------------------------------------------");
		

		Query requette2= em.createQuery("select c.libelle, ((count(e.id))*100)/c.capaciteMax from Cours as c join c.lstEtudiants"
				+ " as e group by c ");
		
		List<Object[]> statcours2=requette2.getResultList();
		
		System.out.println("liste des cours(libelle) avec leur taux de remplissage");
		
		for (Object[] objects : statcours2) {
			System.out.println(Arrays.toString(objects));
		}
		
		System.out.println("------------------------------------------------------------------");
		
		Query requette3= em.createQuery("select c.libelle, count(e.id) from Cours as c join "
				+ "c.lstEtudiants as e  where c.matiere.libelleMatiere=:mat_rechercher group by c.libelle");
		
		requette3.setParameter("mat_rechercher", "Mathématiques");
		
		List<Object[]> statcours3=requette3.getResultList();
		
		System.out.println("Nombre d'etudiant suivant au moins\r\n" + 
				"un cours d'une matiere données (-> Mathématiques)");
		
		for (Object[] objects : statcours3) {
			System.out.println(Arrays.toString(objects));
		}
		
		System.out.println("------------------------------------------------------------------");
		
		Query requette4= em.createQuery("select c.professeur.nom,c.professeur.prenom, count(distinct e.id) from Cours as c join "
				+ "c.lstEtudiants as e  where c.professeur.nom=:nomprof_rechercher group by c.professeur.nom");
		
		requette4.setParameter("nomprof_rechercher", "Jean");
		
		List<Object[]> statcours4=requette4.getResultList();
		
		System.out.println("Nombre d'etudiant suivant au moins\r\n" + 
				"un cours avec un prof (-> Jean Mouloud)");
		
		for (Object[] objects : statcours4) {
			System.out.println(Arrays.toString(objects));
		}
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
