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
	public static final String INSERT_ONE_SQL="insert into produits (nom,prix,poids) VALUES(?,?,?)";
	public static final String FIND_ONE_BY_ID_SQL="select * from produits where id=?";
	public static final String UPDATE_ONE_SQL="update produits set nom=?,prix=?,poids=? where id=?";
	public static final String DELETE_ONE_BY_ID_SQL="delete from produits where id=?";
	
	
	private Connection connection;
	private PreparedStatement select_all_statement;
	private PreparedStatement insert_one_statement;
	private PreparedStatement find_one_by_id_statement;
	private PreparedStatement update_one_by_id_statement;
	private PreparedStatement delete_one_by_id_statement;
	
	
	public ProduitDAO(Connection connection) {
		this.connection= connection;
		try {
			select_all_statement= connection.prepareStatement(SELECT_ALL_SQL);
			insert_one_statement= connection.prepareStatement(INSERT_ONE_SQL);
			find_one_by_id_statement= connection.prepareStatement(FIND_ONE_BY_ID_SQL);
			update_one_by_id_statement= connection.prepareStatement(UPDATE_ONE_SQL);
			delete_one_by_id_statement=connection.prepareStatement(DELETE_ONE_BY_ID_SQL);
			
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
	
	public Produit findOne(int id) {
		try {
			
			Produit p= new Produit();
			
			find_one_by_id_statement.clearParameters();
			find_one_by_id_statement.setInt(1, id);
			ResultSet rs= find_one_by_id_statement.executeQuery();
			
			if(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setNom(rs.getString("nom"));
				p.setPoids(rs.getDouble("poids"));
				p.setPrix(rs.getDouble("prix"));
			}
			else {
				p=null;
			}
			rs.close();
			return p;
			
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return null;
	}
	
	
	public int saveProduit(Produit p) {
		if (p.getId()==0) {
			try {
				insert_one_statement.clearParameters();
				insert_one_statement.setString(1, p.getNom());
				insert_one_statement.setDouble(2, p.getPrix());
				insert_one_statement.setDouble(3, p.getPoids());
				return insert_one_statement.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			try {
				update_one_by_id_statement.clearParameters();
				update_one_by_id_statement.setString(1, p.getNom());
				update_one_by_id_statement.setDouble(2, p.getPrix());
				update_one_by_id_statement.setDouble(3, p.getPoids());
				update_one_by_id_statement.setInt(4, p.getId());
				
				return update_one_by_id_statement.executeUpdate();
								
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		return 0;
		
	}
	
	public int deleteProduit(int id) {
		try {
			delete_one_by_id_statement.clearParameters();
			delete_one_by_id_statement.setInt(1, id);
			return delete_one_by_id_statement.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();}
	
		return 0;
	}

}
