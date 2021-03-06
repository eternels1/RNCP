package com.abarou.videoTech.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.abarou.videoTech.metier.Film;

import dao.GenericDAO;


public class BDDListener implements ServletContextListener {

    
    public BDDListener() {
        // TODO Auto-generated constructor stub
    }

	
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("initialisation connection base");
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/videotech",
					"root",
					"");
			/*
			// instanciation du dao des produits
			ProduitDAO produitDAO = new ProduitDAO(connection);
			// mettre celui-ci a disposition des servlets (et autre) de la webapp
			sce.getServletContext().setAttribute("produitDAO", produitDAO);
			*/
			
			GenericDAO<Film> filmDAO = 
					new GenericDAO.Builder<Film>(Film.class,
													connection,
													"id")
											.build();
			sce.getServletContext().setAttribute("filmDAO", filmDAO);
			
			System.out.println("initialisation DAO terminée");
			
		} catch (ClassNotFoundException e) {e.printStackTrace();}
    	catch (SQLException e) {e.printStackTrace();}
    	
    }
	
}
