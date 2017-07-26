   package exercice_designPattern_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder {

	private String nomtable;
	private Connection conexx;
	private PreparedStatement preparedstatement;
	private StringBuilder requette;
	private ArrayList<String> lstchamps;
	private ArrayList<List<String>> lstaddwhere;
	private ArrayList<List<String>> lstaddsort;
	private boolean isFindAll;
	
	public QueryBuilder(String nomtable, Connection conexx) {
		
		this.nomtable = nomtable;
		this.conexx = conexx;
		this.preparedstatement = null;
		this.requette = new StringBuilder();
		this.lstchamps = new ArrayList<>();
		this.lstaddwhere = new ArrayList<>();
		this.lstaddsort = new ArrayList<>();
		this.isFindAll = false; 
	}
	
	public QueryBuilder findAll() {
		this.isFindAll = true;
		return this;
	}
	
	public QueryBuilder addSlectedChamp(String champ) {
		
		this.lstchamps.add(champ);
		
		return this;
	}
	
	public QueryBuilder addWhere(String nomChamp, String comparatif, String position) {
		
		this.lstaddwhere.add(Arrays.asList(nomChamp,comparatif, position));
		
		return this;
	}
	
public QueryBuilder addSort(String nomChamp, String direction) {
		
		this.lstaddsort.add(Arrays.asList(nomChamp,direction));
		
		return this;
	}
	
	public PreparedStatement build() {
		
		if (this.isFindAll) {
			requette.append("select ");
		}
		
		if (this.lstchamps.size()!=0) {
			
			requette.append(String.join(",",lstchamps));					
		}
		else {
			requette.append(" * ");
		}
		
		if (this.isFindAll) {
			requette.append(" from "+nomtable);
		}
		
		
		if (this.lstaddwhere.size()!=0) {
			
			List<String> lstTemp=
			lstaddwhere.stream()
						.map(lgn -> String.join(" ", lgn.get(0), lgn.get(1), "?"))
						.collect(Collectors.toList());
			
			
			requette.append(" where "+String.join(" and ", lstTemp));	
						
		}
		
		if (this.lstaddsort.size()!=0) {
					
					List<String> lstTemp=
					lstaddsort.stream()
								.map(lgn -> String.join(" ", lgn.get(0), lgn.get(1)))
								.collect(Collectors.toList());
					
					
					requette.append(" order by "+String.join(" , ", lstTemp));	
								
				}
		
		
		
		try {
			System.out.println("requette = "+requette);
			preparedstatement=conexx.prepareStatement(requette.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return preparedstatement;
	}
	
}
