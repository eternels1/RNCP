package xmlSax;

import java.util.spi.LocaleNameProvider;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MyContentHandler implements ContentHandler {

	@Override
	public void startDocument() throws SAXException {
		// qd on demarre l eparsing du doc
		System.out.println("début du document");

	}
	
	@Override
	public void endDocument() throws SAXException {
		// qd on termine le parsing le doc
		System.out.println("fin du document");
	}

	@Override
	public void startElement(String arg0, String localname, String arg2, Attributes arg3) throws SAXException {
		System.out.println("balise ouvrante -->"+ localname );

	}
	@Override
	public void endElement(String arg0, String localname, String arg2) throws SAXException {
		System.out.println("balise fermante ---> "+ localname );

	}
	
	
	@Override
	public void characters(char[] arg0, int start, int length) throws SAXException {
		// qd on rencontre du texte
		String texte= new String(arg0, start, length);
		System.out.println("texte  : '"+texte+"'");

	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void startPrefixMapping(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub

	}

}
