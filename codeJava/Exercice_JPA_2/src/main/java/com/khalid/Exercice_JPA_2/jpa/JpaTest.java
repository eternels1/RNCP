package com.khalid.Exercice_JPA_2.jpa;

import java.awt.Window.Type;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.security.auth.callback.LanguageCallback;

import com.khalid.Exercice_JPA_2.beans.*;

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
		for (int i = 0; i < 6; i++) {
			em.persist(new Tag(0,"libelTag"+i,null));
		}
		
		Gallerie[] tabgallerie=new Gallerie[3];
		for (int i = 0; i < 3; i++) {
			Gallerie gtemp=new Gallerie(0,"gallerie"+i,"titre"+i);
			em.persist(gtemp);
			tabgallerie[i]=gtemp;
		}
		
		for (int i = 0; i < 10; i++) {
			em.persist(new DocumentPdf(0, "doc"+i,"titre"+i, "nomAuteur"+i));
		}
		
		for (int i = 0; i < 87	; i++) {
			Image imgtemp= new Image(0, "nom"+i, "fileName"+i, "typeImg"+i);
			em.persist(imgtemp);
			imgtemp.setGallerie(tabgallerie[rd.nextInt(3)]);
		}
		
		
		List<Content> lstcontent=em.createQuery("from Content",Content.class)
									.getResultList();
		List<Gallerie> lstgallerie= em.createQuery("from Gallerie",Gallerie.class)
										.getResultList();
		
		for (Content c : lstcontent) {
			
			for (int i = 1; i < 6; i++) {
				Tag t=em.find(Tag.class, i);
				if (rd.nextBoolean()) {
					c.getTags().add(t);
				}
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
		
		TypedQuery<Image> queryImagesTagee= em.createQuery(
				"select i from Image as i join i.tags as t "
				+ "where t.libelle=:nomtag",Image.class);
		queryImagesTagee.setParameter("nomtag", "libelTag0");
		List<Image> data=queryImagesTagee.getResultList();
		for (Image img : data) {
			System.out.println(img);
		}
		
		System.out.println("---------------------------------------------------------------------------");
		
		List<Object[]> lstResutRequet2 = em.createQuery(
				"select g.titre, count(i.id) from Gallerie as g join g.images as i "
				+ "group by g.titre ").getResultList();
		for (Object[] row : lstResutRequet2) {
			System.out.println(Arrays.toString(row));
		}
	
		System.out.println("---------------------------------------------------------------------------");
		
		List<Object[]> lstResutRequet4 = em.createQuery(
				"select t.libelle, count(c.id) from Tag as t join t.contents as c "
				+ "group by t.libelle ").getResultList();
		for (Object[] row : lstResutRequet4) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("---------------------------------------------------------------------------");
		TypedQuery<Content> q3= em.createQuery
				("select c from Content as c "
						+ " join c.tags as t1 "
						+ " join c.tags as t2 "
						+ " where t1.id=:tid1 AND t2.id=:tid2",
						Content.class);
		q3.setParameter("tid1", 1);
		q3.setParameter("tid2", 2);
		List<Content> contenus= q3.getResultList();
		
		for (Content c : contenus) {
			System.out.println(c);  
		}
		
		System.out.println("---------------------------------------------------------------------------");
		
		TypedQuery<Content> q5= em.createQuery(
				"select c from Content as c where c.dateEdition > :date",
				Content.class);
		q5.setParameter("date", LocalDateTime.now().minusSeconds(10));
		
		List<Content> lstcontent = q5.getResultList();
		
		for (Content c : lstcontent) {
			System.out.println(c);
		}
		
		System.out.println("---------------------------------------------------------------------------");
		
		TypedQuery<Image> q6= em.createQuery(
				"select img from Image as img join img.tags as t "
				+ " where t.id= :tid1 AND NOT EXISTS ("
				+ " select img2 from Image as img2 join img2.tags as t2 "
				+ " where t2.id=:tid2 AND img2.id=img.id)",
				Image.class);
		q6.setParameter("tid1", 1);
		q6.setParameter("tid2", 2);
		
		List<Image> lstimages= q6.getResultList();
		for (Image image : lstimages) {
			System.out.println(image);
		}
		
		
		
		
		//----------------------------------------------------  
		tx.commit();
		em.close();
	}

}
