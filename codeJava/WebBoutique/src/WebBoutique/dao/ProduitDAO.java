package WebBoutique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import WebBoutique.metier.Produit;

public class ProduitDAO {
	
	public static final String SELECT_ALL_SQL="select * from produits";
	private Connection connection;
	private PreparedStatement select_all_statement;
	
	
	public ProduitDAO(Connection connection) {
		this.connection= connection;
		try {
			select_all_statement= connection.prepareStatement(SELECT_ALL_SQL);
		} catch (SQLException e) {e.printStackTrace();}
	}
	public List<Produit> findAll(){
		ResultSet rs;
		List<Produit> produits = new ArrayList<>();
		try {
			rs = this.select_all_statement.executeQuery();
			
			
			while(rs.next()) {
				produits.add(new Produit(rs.getInt("id"),
										rs.getString("nom"),
										rs.getDouble("prix"),
										rs.getDouble("poids")));
		}
			rs.close();
			
		}
			
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return produits;
	}

}
