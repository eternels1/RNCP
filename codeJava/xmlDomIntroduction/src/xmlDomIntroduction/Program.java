package xmlDomIntroduction;

import java.io.File;
import java.io.IOException;
import java.util.Random;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Program {

	public static void main(String[] args) {
		//utilisation api DOM
		
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db= dbf.newDocumentBuilder();
			
			Document doc1 = db.parse(new File("repertoire.xml"));
			System.out.println("---------------------xpath-----------------------");
			XPathFactory xpf= XPathFactory.newInstance();
			XPath xp= xpf.newXPath();
			XPathExpression xpe1= 
					xp.compile("//repertoire/entree/adresse/rue/text()");
			NodeList result= (NodeList)xpe1.evaluate(doc1, XPathConstants.NODESET);
			for (int i=0;i<result.getLength(); i++) {
				System.out.println(result.item(i).getNodeValue());
			}
			
			System.out.println("---------------------xpath-----------------------");
			
			
			xpe1= 
					xp.compile("//repertoire/entree/adresse[@codePays=2]/ville/text()");
			result= (NodeList)xpe1.evaluate(doc1, XPathConstants.NODESET);
			for (int i=0;i<result.getLength(); i++) {
				System.out.println(result.item(i).getNodeValue());
			}
			
			System.out.println("---------------------xpath-----------------------");
			
			
			xpe1= 
					xp.compile("//repertoire/entree/adresse[@codePays=2]/../nom/text()");
			result= (NodeList)xpe1.evaluate(doc1, XPathConstants.NODESET);
			for (int i=0;i<result.getLength(); i++) {
				System.out.println(result.item(i).getNodeValue());
			}
			
			
			System.out.println("---------------------xpath-----------------------");
			NodeList tags= doc1.getElementsByTagName("nom");
			for (int i=0; i < tags.getLength(); i++)
			{
				Element balise = (Element)tags.item(i);
				System.out.println("tagName: "+ balise.getTagName());
				System.out.println("texte interieur: "+balise.getTextContent());
			}
			System.out.println("----------------------------------------");
			NodeList nodes = doc1.getChildNodes();
			explore(nodes);
			 
			nodes= doc1.getElementsByTagName("entree");
			Random rd= new Random();
			
			for (int i=0; i< nodes.getLength(); i++) {
				Element entree=(Element)nodes.item(i);
				
				Element compte= doc1.createElement("compte");
				entree.appendChild(compte);
				compte.setAttribute("noCompte", "CP"+rd.nextInt(5000));
				Element solde = doc1.createElement("solde");
				compte.appendChild(solde);
				Text texte = doc1.createTextNode(""+rd.nextDouble()*1000);
				solde.appendChild(texte);				
			}
			
			TransformerFactory tfact= TransformerFactory.newInstance();
			Transformer tf=tfact.newTransformer();
			
			DOMSource source = new DOMSource(doc1);
			StreamResult destination= new StreamResult(new File("repertoire2.xml"));
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(source, destination);
			
			System.out.println("--------------------------------------------------------");
			
			Document doc2= db.newDocument();
			
			Element racine = doc2.createElement("racine");
			doc2.appendChild(racine);
			for (int j=1;j<10;j++) {
				Element el= doc2.createElement("item");
				Text t=doc2.createTextNode("texte : "+j);
				el.appendChild(t);
				racine.appendChild(el);				
			}
			source = new DOMSource(doc2);
			destination= new StreamResult(new File("export.xml"));
			tf.transform(source, destination);
			
		
		} 	catch (ParserConfigurationException e) {e.printStackTrace();} 
			catch (SAXException e) {e.printStackTrace();} 
			catch (IOException e) {e.printStackTrace();} 
			catch (TransformerConfigurationException e) {e.printStackTrace();} 
			catch (TransformerException e) {e.printStackTrace();} 
			catch (XPathExpressionException e) {e.printStackTrace();}
	}
	
	public static void explore(NodeList nodes) {
		for (int i=0; i < nodes.getLength(); i++) {
			Node n= nodes.item(i);
			
			switch (n.getNodeType()) {
			case Node.COMMENT_NODE:
				System.out.println("commentaire");break;
			case Node.TEXT_NODE:
				System.out.println("texte: "+n.getNodeValue());break;
			case Node.ELEMENT_NODE:
				System.out.println("balise: "+((Element)n).getTagName());
				System.out.println(">>>>>>>>");
				explore(n.getChildNodes());
				System.out.println("<<<<<<<<<");break;
			case Node.ATTRIBUTE_NODE:
				System.out.println("attribut: "+n.getNodeName());break;

			default:
				break;
			}
		}
	}

}
