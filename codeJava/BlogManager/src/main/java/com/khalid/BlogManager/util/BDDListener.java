package com.khalid.BlogManager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.khalid.BlogManager.metier.Auteur;
import com.khalid.BlogManager.metier.Post;

import dao.GenericDAO;


public class BDDListener implements ServletContextListener {

    public BDDListener() {
        // TODO Auto-generated constructor stub
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("initialisation connection base");
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/blogmanager",
					"root",
					"");
			/*
			// instanciation du dao des produits
			ProduitDAO produitDAO = new ProduitDAO(connection);
			// mettre celui-ci a disposition des servlets (et autre) de la webapp
			sce.getServletContext().setAttribute("produitDAO", produitDAO);
			*/
			
			GenericDAO<Auteur> auteurDAO = 
					new GenericDAO.Builder<Auteur>(Auteur.class,
													connection,
													"id")
											.build();
			sce.getServletContext().setAttribute("auteurDAO", auteurDAO);
			
			GenericDAO<Post> postDAO= 
					new GenericDAO.Builder<Post>(Post.class,connection,"id").build();
			sce.getServletContext().setAttribute("postDAO", postDAO);
			
			
			System.out.println("initialisation DAO terminée");
			
		} catch (ClassNotFoundException e) {e.printStackTrace();}
    	catch (SQLException e) {e.printStackTrace();}
    	
    }
    
	
}
