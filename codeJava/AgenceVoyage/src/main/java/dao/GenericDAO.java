package dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abarou.videoTech.util.QueryBuilder;
import com.abarou.videoTech.util.QueryBuilder.TypeWhere;


public class GenericDAO<T> {
	private Connection connection;
	// nomchamp --> infos sur le champs
	private Map<String, FieldInfos> champs;
	// nom du champ cle primaire
	private String nomCle;
	
	// classe de l'entite
	private Class<T> entityClass;
	
	// requettes
	private PreparedStatement findAllStatement;
	private PreparedStatement findOneByCleStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	
	private GenericDAO(Connection connection,
						Map<String, FieldInfos> champs,
						String nomCle,
						Class<T> entityClass) {
		this.connection = connection;
		this.champs = champs;
		this.nomCle = nomCle;
		this.entityClass = entityClass;
		QueryBuilder qb = new QueryBuilder(entityClass.getSimpleName(),
					connection);
		// ajout des champs
		for (String fieldName : champs.keySet()) {
			qb.addSelectedField(fieldName);
		}
		this.findAllStatement = qb.find().build();
		
		qb.addWhereClause(nomCle, TypeWhere.EQUAL, 1);
		this.findOneByCleStatement = qb.find().build();
		
		qb.reset();
		// ajout des champs sauf cle primaire
		for (String fieldName : champs.keySet()) {
			// ne pas inserer le champ cle primaire
			if (!fieldName.equals(nomCle))
				qb.addSelectedField(fieldName);
		}
		this.insertStatement = qb.insert().build();
	
		// le where est le dernier ajouté, 
		// par exemple update set nom = ?,prix = ?,poids =? where id= ?
		// 4 champs, id est a la position 4
		qb.addWhereClause(nomCle, TypeWhere.EQUAL, champs.size());
		this.updateStatement = qb.update().build();
		
		// le delete
		qb.reset();
		qb.addWhereClause(nomCle, TypeWhere.EQUAL, 1);
		this.deleteStatement = qb.delete().build();
		
	}

	private T fillEntityFromRow(ResultSet rs) {
		// je creer l'entite vide (par exemple un produit)
		try {
			T entite = this.entityClass.newInstance();
			// je parcours mes champs
			for (String fieldName : this.champs.keySet()) {
				FieldInfos fi = this.champs.get(fieldName);
				if (fi.typeChamp.equals(int.class)) {
					fi.setter.invoke(entite, rs.getInt(fieldName));
				}
				else if (fi.typeChamp.equals(double.class)) {
					fi.setter.invoke(entite, rs.getDouble(fieldName));
				}
				else if (fi.typeChamp.equals(String.class)) {
					fi.setter.invoke(entite, rs.getString(fieldName));
				}
				else if (fi.typeChamp.equals(boolean.class)) {
					fi.setter.invoke(entite, rs.getBoolean(fieldName));
				}
				else if (fi.typeChamp.equals(Date.class)) {
					fi.setter.invoke(entite, new Date(rs.getDate(fieldName).getTime()));
				}
			}
			// on retourne l'objet remplis
			return entite;
		} catch (InstantiationException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public List<T> findAll() {
		List<T> data = new ArrayList<>();
		try {
			ResultSet rs = this.findAllStatement.executeQuery();
			while (rs.next()) {
				data.add(fillEntityFromRow(rs));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return data;
	}
	
	public T findByCle(int cle) {
		T data = null;
		try {
			this.findOneByCleStatement.clearParameters();
			this.findOneByCleStatement.setInt(1, cle);
			ResultSet rs = this.findOneByCleStatement.executeQuery();
			if (rs.next()) {
				data = fillEntityFromRow(rs);
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return data;
	}
	
	public int save(T entity) {
		try {
			int id = (int)this.champs.get(nomCle)
										.getter
										.invoke(entity);
			if (id == 0 ) {
				this.insertStatement.clearParameters();
				// insertion
				fillRequestFromEntity(entity, this.insertStatement);
				return this.insertStatement.executeUpdate();
			}
			else {
				// update
				this.updateStatement.clearParameters();
				int pos = fillRequestFromEntity(entity, this.updateStatement);
				this.updateStatement.setInt(pos, id);
				return this.updateStatement.executeUpdate();
			}
		} catch (IllegalAccessException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		return 0;
	}

	private int fillRequestFromEntity(T entity,
									 PreparedStatement statement)
			throws SQLException, IllegalAccessException, InvocationTargetException {
		int position = 1;
		// remplir la requete avec les valeurs de l'objet
		for (String fieldName : champs.keySet()) {
			// ne pas inserer le champ cle primaire
			if (!fieldName.equals(nomCle)) {
				FieldInfos fi = this.champs.get(fieldName);
				if (fi.typeChamp.equals(int.class))
						statement.setInt(position++,
							(int)fi.getter.invoke(entity));
				else if (fi.typeChamp.equals(double.class))
						statement.setDouble(position++,
							(double)fi.getter.invoke(entity));
				else if (fi.typeChamp.equals(boolean.class))
						statement.setBoolean(position++,
							(boolean)fi.getter.invoke(entity));
				else if (fi.typeChamp.equals(String.class))
						statement.setString(position++,
							(String)fi.getter.invoke(entity));
				else if (fi.typeChamp.equals(Date.class))
					statement.setDate(position++,new java.sql.Date(
							((Date)fi.getter.invoke(entity)).getTime())
							);
				
			}
		}
		return position;
	}
	
	public int delete(int cle) {
		try {
			this.deleteStatement.clearParameters();
			this.deleteStatement.setInt(1, cle);
			return this.deleteStatement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	public static class FieldInfos {
		private final String nom;
		private final Method getter;
		private final Method setter;
		private final Class typeChamp;
		
		public String getNom() {return nom;	}
		public Method getGetter() {return getter;}
		public Method getSetter() {return setter;}
		public Class getTypeChamp() {return typeChamp;}
		
		public FieldInfos(	String nom,
							Method getter,
							Method setter,
							Class typeChamp) {
			this.nom = nom;
			this.getter = getter;
			this.setter = setter;
			this.typeChamp = typeChamp;
		}
		
	}
	
	public static class Builder<T> {
		
		private Class<T> buildEntityClass;
		private Connection buildConnection;
		private String buildNomCle;
		private List<FieldInfos> buildChamps;
		
		// constructeur du builder
		// exemple --> Builder<Produit> bp 
		// 		= new GenericDao<Produit>.Builder(Produit.class...)
		public Builder(Class<T> entityClass, Connection connection, String cleNom) {
			this.buildEntityClass = entityClass;
			this.buildConnection = connection;
			this.buildNomCle = cleNom;
			this.buildChamps = new ArrayList<>();
		}
		
		private void fillFieldInfos() {
			Method[] methodes = this.buildEntityClass.getMethods();
			for (Method mgetter : methodes) {
				// examine chaque methode
				// motif -> "get" ou "is" suivi d'un caractere majuscule
				// suivi de 1 a n caracteres alphabetique,chiffre ou "_"
				if (mgetter.getName().matches("^(get|is)[A-Z][A-Za-z0-9_]+$")
					&& mgetter.getParameterTypes().length == 0
					&& 
					( mgetter.getReturnType().equals(int.class)
					 || mgetter.getReturnType().equals(double.class)
					 || mgetter.getReturnType().equals(String.class)
					 || mgetter.getReturnType().equals(boolean.class)
					 || mgetter.getReturnType().equals(Date.class))
					) {
					// quelque chose qui ressemble aun getter correct
					
					// recupération du nom du champs
					String fieldName = null;
					if (mgetter.getName().startsWith("get"))
						// "getPrixTotal" -> "P" -> "p" + "rixTotal" -> "prixTotal"
						fieldName = mgetter.getName().substring(3, 4).toLowerCase()
								 + mgetter.getName().substring(4);
					else if(mgetter.getName().startsWith("is"))
						fieldName = mgetter.getName().substring(2, 3).toLowerCase()
						 + mgetter.getName().substring(3);
					
					// recupération du setter
					try {
						Class typeChamp = mgetter.getReturnType();
						Method msetter = buildEntityClass.getMethod(
								mgetter.getName().replaceFirst("(get|is)", "set"),
								typeChamp);
						// tout est ok, nous avons:
						// 1 un getter  				-> mgetter
						// 2 un setter correspondant	-> msetter
						// 3 le nom du champs			-> fieldName
						// 4 le type de donnee			-> typeChamp
						this.buildChamps.add(
								new FieldInfos(fieldName,
												mgetter,
												msetter,
												typeChamp));
						
					} catch (NoSuchMethodException e) {e.printStackTrace();}
					catch (SecurityException e) {e.printStackTrace();}
				}
			}
		}
		
		// generateur de DAO (construction de celui-ci)
		public GenericDAO<T> build() {
			fillFieldInfos();
			final Map<String, FieldInfos> champsinfo = new HashMap<>();
			this.buildChamps.stream()
							.forEach(fi -> champsinfo.put(fi.getNom(), fi));
			// ["id"  => ("id", getter, setter, type) ; "nom" => ...] 
			return new GenericDAO<>(buildConnection,
									champsinfo,
									buildNomCle,
									buildEntityClass);
		}
		
	}
}
