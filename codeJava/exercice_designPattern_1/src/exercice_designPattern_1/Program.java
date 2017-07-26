package exercice_designPattern_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection= DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/todobase",
					"root",
					"");
			
			QueryBuilder testexo= new QueryBuilder("taches", connection);
			
			//ResultSet rs=
			PreparedStatement preparedstatementtest=	testexo.findAll()
								.addSlectedChamp("id")
								.addSlectedChamp("description")
								.addWhere("id", ">", "1")
								.addSort("description", "asc")
								.build();
			
			preparedstatementtest.setInt(1, 0);
			
			ResultSet rs=preparedstatementtest.executeQuery();
			
			
			
			
			while (rs.next()) {
				System.out.println("id = " + rs.getInt("id")+
						" description = "+ rs.getString("description"));
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
