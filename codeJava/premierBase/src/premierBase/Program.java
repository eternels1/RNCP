package premierBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/firstbase", 
					"root", 
					"");
			
			System.out.println("bravo, vous etes connecter!");
			
			
			
			Statement stat = connection.createStatement();
			ResultSet results= stat.executeQuery("select * from `client`");
			
			while (results.next()) {
				System.out.println("id="+results.getInt("id")
									+" nom ="+ results.getString("nom")
									+" prenom ="+ results.getString("prenom")
									+" email ="+ results.getString("email")
									+" solde ="+ results.getDouble("solde"));
			}
			results.close();
			
			
			
			Scanner reader= new Scanner(System.in);
			
			/*
			System.out.println("nom nouveau client?");
			String nom = reader.nextLine();
			System.out.println("prenom nouveau client?");
			String prenom = reader.nextLine();
			System.out.println("email nouveau client?");
			String email = reader.nextLine();
			System.out.println("solde nouveau client?");
			Double solde = Double.parseDouble(reader.nextLine());
			
			/*Statement insertStatement = connection.createStatement();
			
			String insertSql="INSERT INTO CLIENT(NOM,PRENOM,EMAIL,SOLDE)"+
			"VALUES('"+nom+"','"+prenom+"','"+email+"',"+solde+")";
			
			System.out.println("requette : "+insertSql);
			
			int nbLignes= insertStatement.executeUpdate(insertSql);
			System.out.println(nbLignes+" insérées");
			
			PreparedStatement insertStatement= connection.prepareStatement(
					"INSERT INTO CLIENT(NOM,PRENOM,EMAIL,SOLDE)"+
					"VALUES(?,?,?,?)");
			insertStatement.setString(1, nom);
			insertStatement.setString(2, prenom);
			insertStatement.setString(3, email);
			insertStatement.setDouble(4,solde);
			
			int nbLignes=insertStatement.executeUpdate();
			System.out.println(nbLignes+" insérées");
			
			
			System.out.println("quelle augmentation de solde");
			double augmentation= Double.parseDouble(reader.nextLine());
			System.out.println("concerne les solde au dessus de combien?");
			double seuil= Double.parseDouble(reader.nextLine());
			
			PreparedStatement updateStatement= connection.prepareStatement(
					"UPDATE CLIENT SET SOLDE=SOLDE +? WHERE SOLDE> ?");
					
			updateStatement.setDouble(1, augmentation);
			updateStatement.setDouble(2, seuil);
			
			System.out.println(updateStatement.executeUpdate()+" lignes modifiées");
			
			
			PreparedStatement deleteStatement = connection.prepareStatement(
					"DELETE FROM CLIENT WHERE ID=?");
			System.out.println("identidiant à supprimer?");
			int cid = Integer.parseInt(reader.nextLine());
			deleteStatement.setInt(1, cid);
			
			System.out.println(deleteStatement.executeUpdate()+" lignes modifiées");*/
			
			
			PreparedStatement countStatement = connection.prepareStatement(
					"SELECT COUNT(ID) FROM CLIENT WHERE SOLDE < ?");
			
			System.out.println("solde maximum à compter?");
			double max= Double.parseDouble(reader.nextLine());
			countStatement.setDouble(1, max);
			
			ResultSet rs= countStatement.executeQuery();
			
			if (rs.next()) {
				System.out.println("nb clients sous le seuil = "+ rs.getInt(1));
			}
			
			rs.close();
			
			
			connection.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
