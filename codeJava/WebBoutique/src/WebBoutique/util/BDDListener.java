package WebBoutique.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import WebBoutique.dao.ProduitDAO;


@WebListener
public class BDDListener implements ServletContextListener {

    
    public BDDListener() {
        // TODO Auto-generated constructor stub
    }

    
	 public void contextInitialized(ServletContextEvent sce)  { 
		 System.out.println("initialisation connection base");
         try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/boutique",
					"root",
					"");
			
			ProduitDAO produitDAO= new ProduitDAO(connection);
			
			sce.getServletContext().setAttribute("produitDAO", produitDAO);
			
			System.out.println("initialisation DAO terminée");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	 
	 
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	
   
	
}
