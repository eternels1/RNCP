package com.khalid.BlogManager.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder {
	
	private final String tableName;
	private final Connection connection;
	private TypeRequette type;
	private List<String> selectedFields;
	private List<WhereClause> whereClauses;
	private List<SortClause> sortClauses;
	
	public enum TypeRequette {
		SELECT,
		UPDATE,
		INSERT,
		DELETE
	}

	public enum TypeWhere {
		LESS,
		MORE,
		EQUAL,
		NOT_EQUAl,
		LESS_OR_EQUAL,
		MORE_OR_EQUAL,
		LIKE
	}
	public static class SortClause {
		private final String fieldName;
		private final boolean direction;
		
		public String getFieldName() {return fieldName;	}
		public boolean isDirection() {return direction;	}
		
		public SortClause(String fieldName, boolean direction) {
			this.fieldName = fieldName;
			this.direction = direction;
		}
		
		@Override
		public String toString() {
			return "`" + fieldName + "` " + (direction? "ASC " : "DESC ");
		}
	}
	
	public static class WhereClause {
		private final String fieldName;
		private final TypeWhere typeWhere;
		private final int position;
		
		public String getFieldName() {return fieldName;}
		public TypeWhere getTypeWhere() {return typeWhere;}
		public int getPosition() {return position;}
		
		public WhereClause(String fieldName, TypeWhere typeWhere, int position) {
			this.fieldName = fieldName;
			this.typeWhere = typeWhere;
			this.position = position;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(" `");
			sb.append(fieldName).append("` ");
			switch (typeWhere) {
				case EQUAL: sb.append('='); break;
				case LESS: sb.append('<'); break;
				case MORE: sb.append('>'); break;
				case LESS_OR_EQUAL: sb.append("<="); break;
				case MORE_OR_EQUAL: sb.append(">="); break;
				case NOT_EQUAl: sb.append("<>"); break;
			}
			sb.append(" ?");
			return sb.toString();
		}
	}
	
	public QueryBuilder(String tableName, Connection connection) {
		super();
		this.tableName = tableName;
		this.connection = connection;
		this.type = TypeRequette.SELECT;
		this.selectedFields = new ArrayList<>();
		this.whereClauses = new ArrayList<>();
		this.sortClauses = new ArrayList<>();
	}
	
	public QueryBuilder reset() {
		this.selectedFields.clear();
		this.whereClauses.clear();
		this.sortClauses.clear();
		this.type = TypeRequette.SELECT;
		return this;
	}
	
	
	public QueryBuilder find() {
		this.type = TypeRequette.SELECT;
		return this;
	}

	public QueryBuilder update() {
		this.type = TypeRequette.UPDATE;
		return this;
	}

	public QueryBuilder insert() {
		this.type = TypeRequette.INSERT;
		return this;
	}
	
	public QueryBuilder delete() {
		this.type = TypeRequette.DELETE;
		return this;
	}

	public QueryBuilder addSelectedField(String fieldName) {
		this.selectedFields.add(fieldName);
		return this;
	}
	public QueryBuilder addWhereClause(String fieldName, TypeWhere type, int position) {
		this.whereClauses.add(new WhereClause(fieldName, type, position));
		return this;
	}
	
	public QueryBuilder addSortClause(String fieldName, boolean direction) {
		this.sortClauses.add(new SortClause(fieldName, direction));
		return this;
	}
	
	
	public PreparedStatement build() {
		try {
			switch(this.type) {
				case SELECT: return buildSelect();
				case UPDATE: return buildUpdate();
				case INSERT: return buildInsert();
				case DELETE: return buildDelete();
			}
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	private PreparedStatement buildDelete() throws SQLException {
		StringBuilder sb = new StringBuilder("DELETE FROM `");
		sb.append(tableName).append("` WHERE ");
		if (this.whereClauses.isEmpty())
			throw new SQLException("what are you doing!! use where clause with delete");
	
		// ajout du where
		List<String> clauses 
			= this.whereClauses.stream()
				.sorted((c1, c2) -> Integer.compare(c1.position, c2.position))
				.map(c -> c.toString())
				.collect(Collectors.toList());
		
		sb.append(String.join(" AND ", clauses));
		System.out.println("requete = " + sb.toString());
		return connection.prepareStatement(sb.toString());
	}	
	
	private PreparedStatement buildInsert() throws SQLException {
		StringBuilder sb = new StringBuilder("INSERT INTO `");
		sb.append(tableName).append("` (`");
		if (this.selectedFields.isEmpty())
			throw new SQLException("can not insert with no field selected");
		sb.append(String.join("`,`", this.selectedFields))
		  .append("`) VALUES (")
		  .append(String.join(",",
				  this.selectedFields.stream()
				  					 .map(c -> "?")
				  					 .collect(Collectors.toList())));
		sb.append(")");
		System.out.println("requette = " + sb.toString());
		return connection.prepareStatement(sb.toString());
	}
	
	
	private PreparedStatement buildUpdate() throws SQLException {
		StringBuilder sb = new StringBuilder("UPDATE ");
		if (this.selectedFields.isEmpty())
			throw new SQLException("can not update with no field selected");
		sb.append('`').append(tableName).append("` SET `");
		sb.append(String.join("` = ?, `", this.selectedFields))
		  .append("` = ? ");
		// ajout des where
		if (!whereClauses.isEmpty()) {
			// ajout du where
			List<String> clauses 
				= this.whereClauses.stream()
					.sorted((c1, c2) -> Integer.compare(c1.position, c2.position))
					.map(c -> c.toString())
					.collect(Collectors.toList());
			
			sb.append(" WHERE ")
			  .append(String.join(" AND ", clauses));
		}
		System.out.println("requete = " + sb.toString());
		return connection.prepareStatement(sb.toString());
	}
	
	private PreparedStatement buildSelect() throws SQLException {
		StringBuilder sb = new StringBuilder("SELECT ");
		if (this.selectedFields.isEmpty()) {
			sb.append("*");
		}
		else {
			sb.append('`')
			  .append(String.join("`,`", this.selectedFields))
			  .append('`');
		}
		sb.append(" FROM `")
		  .append(tableName)
		  .append('`');
		// ajout des where et des tri
		if (!whereClauses.isEmpty()) {
			// ajout du where
			List<String> clauses 
				= this.whereClauses.stream()
					.sorted((c1, c2) -> Integer.compare(c1.position, c2.position))
					.map(c -> c.toString())
					.collect(Collectors.toList());
			
			sb.append(" WHERE ")
			  .append(String.join(" AND ", clauses));
		}
		if (!sortClauses.isEmpty()) {
			List<String> clauses  = this.sortClauses.stream()
										.map(c -> c.toString())
										.collect(Collectors.toList());
			sb.append(" ORDER BY ")
			  .append(String.join(" , ", clauses));
		}
			
		
		System.out.println("requette = " + sb.toString());
		return connection.prepareStatement(sb.toString());
	}
	

	
	
	
	

}
