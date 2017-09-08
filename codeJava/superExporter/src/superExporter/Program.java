package superExporter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Program {

	public static void main(String[] args) {
		
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		
		try {
			
			String nombase = JOptionPane.showInputDialog("nom base a exporter?");
			
			DocumentBuilder docxml = dbf.newDocumentBuilder();
			Document docExo=docxml.newDocument();
			Element racine= docExo.createElement("data");
			docExo.appendChild(racine);
			Element table= docExo.createElement("table");
			racine.appendChild(table);
			String saisieTableUser= JOptionPane.showInputDialog("nom table a exporter?");
			table.setAttribute("name", saisieTableUser);
			Element columns= docExo.createElement("columns");
			table.appendChild(columns);
			
			
			
			TransformerFactory tfact= TransformerFactory.newInstance();
			Transformer tf=tfact.newTransformer();
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection base = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + nombase,
					"root",
					"");
			
			
			Statement query = base.createStatement();
			ResultSet rs = query.executeQuery("select * from " + saisieTableUser);
			
			// recupere les informations sur les colonnes renvoyées
			// par la requette
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				Element column= docExo.createElement("column");
				
				column.setAttribute("name", rsmd.getColumnName(i));
				column.setAttribute("type", rsmd.getColumnTypeName(i));			
				columns.appendChild(column);
			}
			
			while (rs.next()) {
				Element row = docExo.createElement("row");			
				table.appendChild(row);
				
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						Element cell= docExo.createElement("cell");
						row.appendChild(cell);
						cell.setAttribute("name",rsmd.getColumnName(i));
						Text t=docExo.createTextNode(rs.getString(i));
						cell.appendChild(t);						
				}
				
			}
			
			
			rs.close();
			
			DOMSource source = new DOMSource(docExo);
			StreamResult destination= new StreamResult(new File("resultexoXML.xml"));
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			tf.transform(source, destination);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {e.printStackTrace();} 
		  catch (TransformerConfigurationException e) {e.printStackTrace();} 
		  catch (TransformerException e) {e.printStackTrace();}
		

	}

}
