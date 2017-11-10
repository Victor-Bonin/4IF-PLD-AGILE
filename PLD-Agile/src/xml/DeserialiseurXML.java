package xml;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.ExceptionPlanCo;
import modele.Plan;

/**
 * Classe permattant de faire le lien entre les fichiers XML et les objets Java. Elle extrait du XML les informations permettant d'instancier l'objet Plan.
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
 *             \      .: /|\
 *             )\_   .: / |:"--___
 *         __-:|\ """ _-  |:::::::
 *       _-::::\ "-_.-   /::::::::
 *    _--:::::::| .|"-_  |::::::::
 *  -"::::::::::\  | { -_|::::::::
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr
 * 
 * 
 * @author 4104
 */
public class DeserialiseurXML {
	/**
	 * Ouvre un fichier xml et remplit le plan a partir du contenu du fichier
	 * @param plan Objet plan deja instancie
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionPlanCo
	 */
	public static void charger(Plan plan) throws ExceptionPlanCo, ParserConfigurationException, SAXException, IOException {
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
		chargerFichier(plan, xml);
	}

	/**
	 * Remplit le plan a partir du contenu du fichier
	 * @param plan
	 * @param xml
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionPlanCo
	 */
	public static void chargerFichier(Plan plan, File xml) throws ParserConfigurationException, SAXException, IOException, ExceptionPlanCo {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
		Document document = docBuilder.parse(xml);
		Element racine = document.getDocumentElement();
		if (racine.getNodeName().equals("reseau")) {
			construireAPartirDeDOMXML(racine, plan);
		}
		else
			throw new ExceptionPlanCo(ExceptionPlanCo.DOCUMENT_NON_CONFORME);
	}

	/**
	 * Ouvre un fichier xml et remplit la demande de livraison du plan a partir du contenu du fichier
	 * @param plan Objet plan déjà instancié
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParseException 
	 * @throws ExceptionPlanCo 
	 * @throws Exception
	 */
	public static void chargerDemandeLivraison(Plan plan) throws ParserConfigurationException, SAXException, IOException, ExceptionPlanCo, ParseException{
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
		chargerDemandeLivraisonFichier(plan, xml);
	}

	/**
	 * Remplit la demande de livraison du plan a partir du contenu du fichier
	 * @param plan
	 * @param xml
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionPlanCo
	 * @throws ParseException
	 */
	public static void chargerDemandeLivraisonFichier(Plan plan, File xml) throws ParserConfigurationException, SAXException, IOException, ExceptionPlanCo, ParseException{
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
		Document document = docBuilder.parse(xml);
		Element racine = document.getDocumentElement();
		if (racine.getNodeName().equals("demandeDeLivraisons")) {
			construireDemandeLivraisonAPartirDeDOMXML(racine, plan);
		}
		else
			throw new ExceptionPlanCo(ExceptionPlanCo.DOCUMENT_NON_CONFORME);

	}

	/**
	 * Extrait les donnees du noeud racine à partir du fichier xml et remplit le plan avec ces donnees
	 * @param noeudDOMRacine Noeud racine du fichier xml
	 * @param plan Objet plan déjà instancie
	 * @throws ExceptionPlanCo
	 * @throws NumberFormatException
	 */
	private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws ExceptionPlanCo, NumberFormatException
	{
		plan.reset();	
		NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("noeud");
		for (int i = 0; i < listeNoeuds.getLength(); i++) {
			Element xmlNoeud = (Element) listeNoeuds.item(i);
			plan.ajouterIntersection(Integer.parseInt(xmlNoeud.getAttribute("x")), Integer.parseInt(xmlNoeud.getAttribute("y")), Long.parseLong(xmlNoeud.getAttribute("id")));
		}
		NodeList listeTroncons = noeudDOMRacine.getElementsByTagName("troncon");
		for (int i = 0; i < listeTroncons.getLength(); i++) {
			Element xmlTroncon = (Element) listeTroncons.item(i);
			try {
				if(Float.parseFloat(xmlTroncon.getAttribute("longueur")) < 0)
				{
					throw new ExceptionPlanCo(ExceptionPlanCo.LONGUEUR_NEGATIVE);
				}
				plan.ajouterTroncon(Long.parseLong(xmlTroncon.getAttribute("origine")), Long.parseLong(xmlTroncon.getAttribute("destination")), Float.parseFloat(xmlTroncon.getAttribute("longueur")), xmlTroncon.getAttribute("nomRue"));
			} catch (Exception e) {
				throw new ExceptionPlanCo(e.getMessage());
			}
		}
	}
	/**
	 * Extrait les donnees du noeud racine à partir du fichier xml et remplit la demande de livraison du plan avec ces donnees
	 * @param noeudDOMRacine Noeud racine du fichier xml
	 * @param plan Objet plan déjà instancie
     * @throws ExceptionPlanCo 
     * @throws ParseException 
     * @throws Exception
     */
    private static void construireDemandeLivraisonAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws ExceptionPlanCo, ParseException
    {
	    	plan.resetDemandeLivraison();
	    	NodeList listeEntrepot = noeudDOMRacine.getElementsByTagName("entrepot");
	    	Element entrepot = (Element) listeEntrepot.item(0);
	    	String dateAsString = entrepot.getAttribute("heureDepart");
	    	Calendar cal = Calendar.getInstance();
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    	cal.setTime(dateFormat.parse(dateAsString));
	    	long entrepotId = Long.parseLong(entrepot.getAttribute("adresse"));
	    	plan.setEntrepot(entrepotId, cal);
	
	    	NodeList listeAdresse = noeudDOMRacine.getElementsByTagName("livraison");
	    	for (int i = 0; i < listeAdresse.getLength(); i++) {
	    		Element xmlAdresse = (Element) listeAdresse.item(i);
	    		Long id = Long.parseLong(xmlAdresse.getAttribute("adresse"));
	    		int duree = Integer.parseInt(xmlAdresse.getAttribute("duree"));
	    		if(duree < 0) {
	    			throw new ExceptionPlanCo(ExceptionPlanCo.DUREE_NEGATIVE);
	    		}
	    		String debutPlageAsString = xmlAdresse.getAttribute("debutPlage");
	    		String finPlageAsString = xmlAdresse.getAttribute("finPlage");
	    		if(!debutPlageAsString.isEmpty() || !finPlageAsString.isEmpty())
	    		{
	    			Calendar debutPlage = Calendar.getInstance();
		    		debutPlage.setTime(dateFormat.parse(debutPlageAsString));
		    		Calendar finPlage = Calendar.getInstance();
		    		finPlage.setTime(dateFormat.parse(xmlAdresse.getAttribute("finPlage")));
		    		if(!debutPlage.before(finPlage)) {
		    			throw new ExceptionPlanCo(ExceptionPlanCo.PLAGE_HORAIRE_INCORRECTE);
		    		}
		    		plan.ajouterPointLivraison(id, duree, debutPlage, finPlage);
	    		} else {
	    			plan.ajouterPointLivraison(id, duree, null, null);
	    		}
	    	}
    }
}
