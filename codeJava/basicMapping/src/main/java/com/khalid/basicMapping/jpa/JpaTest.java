package com.khalid.basicMapping.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.khalid.basicMapping.beans.*;

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
//		test2(emf);
		
		
		input.nextLine();
		System.out.println("--------------------------------------");
//		test4(emf);
			
			
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
		em.persist(new Produit(0,"steak de lama", 19.99,0.5,15, new Date()));
		em.persist(new Produit(0,"tofou tofou", 19.99,0.5,15, new Date()));
		em.persist(new Produit(0,"quinoa des andes", 19.99,0.5,15, new Date()));
		em.persist(new Produit(0,"steak de velociraptor", 15.99,0.5,3, new Date()));
		em.persist(new Produit(0,"tacos", 35.99,0.5,20, new Date()));
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
//	public static void test3(EntityManagerFactory emf)
//	{
//		// on recupere un entityManager
//		EntityManager em = emf.createEntityManager();
//		// et une transaction
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		//----------------------------------------------------
//		Produit p1=em.find(Produit.class,1);
//		p1.setPrix(p1.getPrix()+15);
//		System.out.println("nouveau prix : "+p1);
//		System.out.println("----------------------------------------------------------------------------------------");
//
//		TypedQuery<Produit> q1=em.createQuery("from Produit", Produit.class);
//		
//		List<Produit> lstproduits= q1.getResultList();
//		for (Produit p: lstproduits)
//			System.out.println(p);
//		
//		
//		//----------------------------------------------------
//		tx.commit();
//		em.close();
//	}
	
//	public static void test4(EntityManagerFactory emf)
//	{
//		// on recupere un entityManager
//		EntityManager em = emf.createEntityManager();
//		// et une transaction
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		//----------------------------------------------------
//		Produit p1= em.find(Produit.class, 1);
//		Produit p2= em.find(Produit.class, 2);
//		
//		em.remove(p2);
//		p1.setStock(42);
//		
//		
//		//----------------------------------------------------
//		tx.commit();
//		em.close();
//		
//		p1.setStock(54);
//		System.out.println("p1 = "+p1);
//		
//	}


//	public static void test2(EntityManagerFactory emf)
//	{
//		// on recupere un entityManager
//		EntityManager em = emf.createEntityManager();
//		// et une transaction
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		//----------------------------------------------------
//		Produit p1= em.find(Produit.class, 1);
//		System.out.println(p1);
//		
//		System.out.println("-------------------------------------");
//		
//		TypedQuery<Produit> q1=em.createQuery("from Produit", Produit.class);
//		
//		List<Produit> lstproduits= q1.getResultList();
//		for (Produit p: lstproduits)
//			System.out.println(p);
//		
//		System.out.println("----------------------------------------------------------------------------------------");
//		
//		TypedQuery<Produit> q2=em.createQuery("select p from Produit as p where p.prix> :pmin", Produit.class);
//		q2.setParameter("pmin", 20.0);
//		lstproduits= q2.getResultList();
//		for (Produit p: lstproduits)
//			System.out.println(p);
//		
//		System.out.println("----------------------------------------------------------------------------------------");
//		
//		Query q3=em.createQuery("select p.nom, p.prix from Produit as p where p.stock > :stockmin");
//		q3.setParameter("stockmin", 18);
//		
//		List<Object[]> data=q3.getResultList();
//		for (Object[] ligne : data) {
//			System.out.println(Arrays.toString(ligne));
//		}
//		
//		
//		
//		//----------------------------------------------------
//		tx.commit();
//		em.close();
//	}

}
