package TodoManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TodoManager.metier.Tache;

public class TacheDAO {
	public static final String SELECT_ALL_SQL="select * from taches";
	public static final String INSERT_ONE_SQL="insert into taches "
			+ "(description,priorite,contexte,finished) VALUES(?,?,?,?)";
	public static final String FIND_ONE_BY_ID_SQL="select * from taches where id=?";
	public static final String UPDATE_ONE_SQL="update taches set "
			+ "description=?,priorite=?,contexte=?,finished=? where id=?";
	public static final String DELETE_ONE_BY_ID_SQL="delete from taches where id=?";
	
	
	private Connection connection;
	private PreparedStatement select_all_statement;
	private PreparedStatement insert_one_statement;
	private PreparedStatement find_one_by_id_statement;
	private PreparedStatement update_one_by_id_statement;
	private PreparedStatement delete_one_by_id_statement;
	
	public TacheDAO(Connection connection) {
		this.connection=connection;
		try {
			select_all_statement=connection.prepareStatement(SELECT_ALL_SQL);
			insert_one_statement= connection.prepareStatement(INSERT_ONE_SQL);
			find_one_by_id_statement= connection.prepareStatement(FIND_ONE_BY_ID_SQL);
			update_one_by_id_statement= connection.prepareStatement(UPDATE_ONE_SQL);
			delete_one_by_id_statement=connection.prepareStatement(DELETE_ONE_BY_ID_SQL);
			
			
		} catch (SQLException e) {e.printStackTrace();}		
	}
	
	public List<Tache> findAll(){
		ResultSet rs;
		List<Tache> lstTaches= new ArrayList<>();
		
		try {
			rs = this.select_all_statement.executeQuery();
			
			while(rs.next()) {
				lstTaches.add(new Tache(rs.getInt("id"),
											rs.getString("description"),
											rs.getInt("priorite"),
											rs.getString("contexte"),
											rs.getBoolean("finished")));
			}
			rs.close();
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return lstTaches;
	}
	
	
	public Tache findOne(int id) {
		Tache t= new Tache();
		
		try {
			find_one_by_id_statement.clearParameters();
			find_one_by_id_statement.setInt(1, id);
			ResultSet rs = find_one_by_id_statement.executeQuery();
			
			if (rs.next()) {
				
				t.setId(rs.getInt("id"));
				t.setDescription(rs.getString(""));
				t.setPriorite(rs.getInt("priorite"));
				t.setContexte(rs.getString("contexte"));
				t.setFinished(rs.getBoolean("finished"));
			}
			else {
				t=null;
			}
			rs.close();
			return t;
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return null;	
	}
	
	public int saveTache(Tache t) {
		if (t.getId()==0) {
			try {
				insert_one_statement.clearParameters();
				insert_one_statement.setString(1, t.getDescription());
				insert_one_statement.setInt(2,t.getPriorite());
				insert_one_statement.setString(3, t.getContexte());
				insert_one_statement.setBoolean(4, t.isFinished());
				
				return insert_one_statement.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}			
		}
		else {			
					
			try {
				insert_one_statement.clearParameters();	
				insert_one_statement.setString(1, t.getDescription());
				insert_one_statement.setInt(2,t.getPriorite());
				insert_one_statement.setString(3, t.getContexte());
				insert_one_statement.setBoolean(4, t.isFinished());
				insert_one_statement.setInt(5,t.getId());
				
				return update_one_by_id_statement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}			
		}
		return 0;
	}
	
	public int deleteTache(int id) {
		try {
			delete_one_by_id_statement.clearParameters();
			delete_one_by_id_statement.setInt(1, id);
			return delete_one_by_id_statement.executeUpdate();			
		} catch (SQLException e) {e.printStackTrace();}
		
		return 0;
	}
	
	
	
}
