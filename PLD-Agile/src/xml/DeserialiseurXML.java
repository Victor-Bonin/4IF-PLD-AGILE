package xml;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.Plan;

/**
 * Classe permattant de faire le lien entre les fichiers XML et les objets Java. Elle extrait du XML les informations permettant d'instancier l'objet Plan.
 * @author 4104
 *
 */
public class DeserialiseurXML {
	/**
	 * Ouvre un fichier xml et remplit le plan a partir du contenu du fichier
	 * @param plan Objet plan deja instancie
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void charger(Plan plan) throws ParserConfigurationException, SAXException, IOException, ExceptionXML{
		plan.reset();
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        if (racine.getNodeName().equals("reseau")) {
           construireAPartirDeDOMXML(racine, plan);
        }
        else
        		throw new ExceptionXML("Document non conforme");
	}
	
	/**
	 * Ouvre un fichier xml et remplit la demande de livraison du plan a partir du contenu du fichier
	 * @param plan Objet plan déjà instancié
	 * @throws Exception
	 */
	public static void chargerDemandeLivraison(Plan plan)throws Exception{
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        if (racine.getNodeName().equals("demandeDeLivraisons")) {
        		construireDemandeLivraisonAPartirDeDOMXML(racine, plan);
        }
        else
        		throw new ExceptionXML("Document non conforme");
	}

	/**
	 * Extrait les donnees du noeud racine à partir du fichier xml et remplit le plan avec ces donnees
	 * @param noeudDOMRacine Noeud racine du fichier xml
	 * @param plan Objet plan déjà instancie
	 * @throws ExceptionXML
	 * @throws NumberFormatException
	 */
    private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws ExceptionXML, NumberFormatException
    {
    		NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("noeud");
       	for (int i = 0; i < listeNoeuds.getLength(); i++) {
       		Element xmlNoeud = (Element) listeNoeuds.item(i);
       		plan.ajoute(Integer.parseInt(xmlNoeud.getAttribute("x")), Integer.parseInt(xmlNoeud.getAttribute("y")), Long.parseLong(xmlNoeud.getAttribute("id")));
       	}
       	NodeList listeTroncons = noeudDOMRacine.getElementsByTagName("troncon");
       	for (int i = 0; i < listeTroncons.getLength(); i++) {
       		Element xmlTroncon = (Element) listeTroncons.item(i);
          	try {
				plan.ajoute(Long.parseLong(xmlTroncon.getAttribute("origine")), Long.parseLong(xmlTroncon.getAttribute("destination")), Float.parseFloat(xmlTroncon.getAttribute("longueur")), xmlTroncon.getAttribute("nomRue"));
			} catch (Exception e) {
				throw new ExceptionXML(e.getMessage());
			}
       	}
    }
    /**
	 * Extrait les donnees du noeud racine à partir du fichier xml et remplit la demande de livraison du plan avec ces donnees
	 * @param noeudDOMRacine Noeud racine du fichier xml
	 * @param plan Objet plan déjà instancie
     * @throws Exception
     */
    private static void construireDemandeLivraisonAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws Exception
    {
    		NodeList listeEntrepot = noeudDOMRacine.getElementsByTagName("entrepot");
    		Element entrepot = (Element) listeEntrepot.item(0);
    		String dateAsString = entrepot.getAttribute("heureDepart");
    		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    		Date date = dateFormat.parse(dateAsString);
    		long entrepotId = Long.parseLong(entrepot.getAttribute("adresse"));
    		plan.setEntrepot(entrepotId, date);
    		
    		NodeList listeAdresse = noeudDOMRacine.getElementsByTagName("livraison");
       	for (int i = 0; i < listeAdresse.getLength(); i++) {
       		Element xmlAdresse = (Element) listeAdresse.item(i);
          	Long id = Long.parseLong(xmlAdresse.getAttribute("adresse"));
          	int duree = Integer.parseInt(xmlAdresse.getAttribute("duree"));
          	plan.ajouterPointLivraison(id,  duree);
       	}
    }
}
