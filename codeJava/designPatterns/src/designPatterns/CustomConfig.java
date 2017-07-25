package designPatterns;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomConfig {

	private static CustomConfig theInstance=null;
	private LocalDateTime dateCreated;
	private Map<String, String> configvalues;
	
	private CustomConfig() {
		this.dateCreated= LocalDateTime.now();
		this.configvalues= new HashMap();
	}
	
	public void addValueToConfig(String cle, String valeur) {
		this.configvalues.put(cle, valeur);
	}
	
	public String getValueFromConfig(String cle) {
		return this.configvalues.get(cle);
	}
	
	public void saveToPropertiesFile(String filename) {
		Properties prop= new Properties();
		OutputStream output= null;
		
		try {
			output = new FileOutputStream(filename);
			this.configvalues.
			keySet().
			stream().
			forEach(cle -> prop.setProperty(cle, this.configvalues.get(cle)));
			
			prop.store(output, null);
			output.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadFromPropertiesFile(String filename) {
		this.configvalues.clear();
		Properties prop= new Properties();
		InputStream input= null;
		
		try {
			input = new FileInputStream(filename);
			prop.load(input);
			
			prop.
			keySet().
			stream().
			map(cle -> cle.toString())
			.forEach(cle -> prop.setProperty(cle, this.configvalues.get(cle)));
			
			input.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public static CustomConfig getInstance() {
		if (theInstance==null) {
			theInstance=new CustomConfig();
			
		}
		
		return theInstance;
	}


	@Override
	public String toString() {
		return "CustomConfig [dateCreated=" + dateCreated + "]";
	}
	
}
