package xmlSax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Program {

	public static void main(String[] args) {
		try {
			XMLReader reader= XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new MyContentHandler());
			reader.parse(new InputSource(new FileInputStream("repertoire.xml")));
			
			System.out.println("----------------------------------------------");
			
			OutputStream sortie= new FileOutputStream(new File("sortie.xml"));
			
			XMLStreamWriter writer= 
					XMLOutputFactory.newInstance()
									.createXMLStreamWriter(sortie, "utf-8");
			writer.writeStartDocument();
			writer.writeStartElement("doc");
				writer.writeStartElement("titre");
					writer.writeCharacters("mon document");
				writer.writeEndElement();
				writer.writeStartElement("description");
					writer.writeCharacters("le ciel est furieux");
				writer.writeEndElement();
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.close();
			
		} 	catch (SAXException e) {	e.printStackTrace();} 
			catch (FileNotFoundException e) {e.printStackTrace();} 
			catch (IOException e) {e.printStackTrace();} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}

}
