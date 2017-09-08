package com.khalid.jpaInclusion.jpa;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.khalid.jpaInclusion.beans.*;

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
		Adresse a1= new Adresse("221 baker street",25864,"London","England");
		Adresse a2= new Adresse("21 jump street",897564,"New yord","USA");
		
		Site s1= new Site(0,"Holmes Home",a1, new Geolocalisation(10.0,15.0));
		Site s2= new Site(0,"Officier Thomas",a2, new Geolocalisation(10.0,15.0));
		
		em.persist(s1);
		em.persist(s2);
		
		Livre l1= new Livre(new CleLivre("3548464",LocalDate.of(2017, 8, 8)),"Jpa forever",855);
		em.persist(l1);
		
		
		
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
		
		Site s1=em.find(Site.class, 1);
		System.out.println(s1);
		
		TypedQuery<Site> q1=em.createQuery("select s from Site as s where s.adresse.pays=:pays",Site.class);
		q1.setParameter("pays", "USA");
		
		List<Site> sites=q1.getResultList();
		for (Site s : sites) {
			System.out.println(s);
		}
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
