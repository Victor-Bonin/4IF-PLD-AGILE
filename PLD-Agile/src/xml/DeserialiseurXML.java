package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.Cercle;
import modele.Plan;
import modele.Point;
import modele.PointFactory;
import modele.Rectangle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DeserialiseurXML {
	/**
	 * Ouvre un fichier xml et cree plan a partir du contenu du fichier
	 * @param plan
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void charger(Plan plan) throws ParserConfigurationException, SAXException, IOException, ExceptionXML{
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        if (racine.getNodeName().equals("plan")) {
           construireAPartirDeDOMXML(racine, plan);
        }
        else
        	throw new ExceptionXML("Document non conforme");
	}

    private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws ExceptionXML, NumberFormatException{
    	int hauteur = Integer.parseInt(noeudDOMRacine.getAttribute("hauteur"));
        if (hauteur <= 0)
        	throw new ExceptionXML("Erreur lors de la lecture du fichier : La hauteur du plan doit etre positive");
        int largeur = Integer.parseInt(noeudDOMRacine.getAttribute("largeur"));
        if (largeur <= 0)
        	throw new ExceptionXML("Erreur lors de la lecture du fichier : La largeur du plan doit etre positive");
       	plan.reset(largeur,hauteur);
       	NodeList listeCercles = noeudDOMRacine.getElementsByTagName("cercle");
       	for (int i = 0; i < listeCercles.getLength(); i++) {
        	plan.ajoute(creeCercle((Element) listeCercles.item(i)));
       	}
       	NodeList listeRectangles = noeudDOMRacine.getElementsByTagName("rectangle");
       	for (int i = 0; i < listeRectangles.getLength(); i++) {
          	plan.ajoute(creeRectangle((Element) listeRectangles.item(i)));
       	}
    }
    
    private static Cercle creeCercle(Element elt) throws ExceptionXML{
   		int x = Integer.parseInt(elt.getAttribute("x"));
   		int y = Integer.parseInt(elt.getAttribute("y"));
   		Point p = PointFactory.creePoint(x, y);
   		if (p == null)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Coordonnees d'un point en dehors du plan");
   		int rayon = Integer.parseInt(elt.getAttribute("rayon"));
   		if (rayon <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Cercle de rayon negatif ou nul");
   		return new Cercle(p, rayon);
    }
    
    private static Rectangle creeRectangle(Element elt) throws ExceptionXML{
   		int x = Integer.parseInt(elt.getAttribute("x"));
   		int y = Integer.parseInt(elt.getAttribute("y"));
   		Point p = PointFactory.creePoint(x, y);
   		if (p == null)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Coordonnees d'un point en dehors du plan");
      	int largeurRectangle = Integer.parseInt(elt.getAttribute("largeur"));
   		if (largeurRectangle <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Rectangle de largeur negative ou nulle");
      	int hauteurRectangle = Integer.parseInt(elt.getAttribute("hauteur"));
   		if (hauteurRectangle <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Rectangle de hauteur negative ou nulle");
   		return new Rectangle(p, largeurRectangle, hauteurRectangle);
    }
 
}
