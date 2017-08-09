package com.khalid.jpaHeritageTablePerClass.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.khalid.jpaHeritageTablePerClass.beans.*;

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
		
		Random rd= new Random();
		for (int i = 0; i <=10 ; i++) {
			em.persist(new Personne(UUID.randomUUID().toString(), "bob"+i,"joe"+i));
		}
		
		for (int i = 0; i <=10 ; i++) {
			em.persist(new Employe(UUID.randomUUID().toString(), "sylverstre"+i,"commandant"+i, "bourrineur"+i, rd.nextDouble()*4000.0));
		}
		
		
		for (int i = 0; i <=10 ; i++) {
			em.persist(new Client(UUID.randomUUID().toString(), "arnold"+i, "Willy"+i, "arnold@willy.com", rd.nextDouble()*2000.0));		}
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
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
