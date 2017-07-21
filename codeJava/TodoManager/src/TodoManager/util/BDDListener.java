package TodoManager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import TodoManager.dao.TacheDAO;


@WebListener
public class BDDListener implements ServletContextListener {

   
    public BDDListener() {
        // TODO Auto-generated constructor stub
    }

	   public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println("Initialisation connection base...");
         
         try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection= DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/todobase",
					"root",
					"");
			
			TacheDAO tacheDAO= new TacheDAO(connection);
			
			sce.getServletContext().setAttribute("tacheDAO", tacheDAO);
			
			System.out.println("Initialisation DAO terminer OK");
					
		} catch (ClassNotFoundException e) {e.printStackTrace();} 
         catch (SQLException e) {e.printStackTrace();}         
    }
	   
	   
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	
 
	
}
