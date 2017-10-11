package xml;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modele.VisiteurPourAfficherFormes;
import modele.Cercle;
import modele.Forme;
import modele.Plan;
import modele.Rectangle;

public class SerialiseurXML implements VisiteurPourAfficherFormes{// Singleton
	
	private Element racineForme;
	private Document document;
	private static SerialiseurXML instance = null;
	private SerialiseurXML(){}
	public static SerialiseurXML getInstance(){
		if (instance == null)
			instance = new SerialiseurXML();
		return instance;
	}
 
	/**
	 * Ouvre un fichier xml et ecrit dans ce fichier une description xml de plan
	 * @param plan
	 * @throws ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 * @throws ExceptionXML
	 */
	public void sauver(Plan plan) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException, ExceptionXML{
		File xml = OuvreurDeFichierXML.getInstance().ouvre(false);
  		StreamResult result = new StreamResult(xml);
       	document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
       	document.appendChild(creerElementPlan(plan));
        DOMSource source = new DOMSource(document);
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xformer.transform(source, result);
	}

	private Element creerElementPlan(Plan plan) {
		Element racine = document.createElement("plan");
		creerAttribut(racine,"hauteur",Integer.toString(plan.getHauteur()));
		creerAttribut(racine,"largeur",Integer.toString(plan.getLargeur())); 
		Iterator<Forme> it = plan.getIterateurFormes();
		while (it.hasNext()){
			it.next().affiche(this);
			racine.appendChild(racineForme);
		}
		return racine;
	}
	
    private void creerAttribut(Element racine, String nom, String valeur){
    	Attr attribut = document.createAttribute(nom);
    	racine.setAttributeNode(attribut);
    	attribut.setValue(valeur);
    }
   
	@Override
	public void affiche(Cercle c) {
        racineForme = document.createElement("cercle");
        creerAttribut(racineForme,"x",Integer.toString(c.getCentre().getX()));
        creerAttribut(racineForme,"y",Integer.toString(c.getCentre().getY()));
        creerAttribut(racineForme,"rayon",Integer.toString(c.getRayon()));
	}

	@Override
	public void affiche(Rectangle r) {
        racineForme = document.createElement("rectangle");
        creerAttribut(racineForme,"x",Integer.toString(r.getCoin().getX()));
        creerAttribut(racineForme,"y",Integer.toString(r.getCoin().getY()));
        creerAttribut(racineForme,"largeur",Integer.toString(r.getLargeur()));
        creerAttribut(racineForme,"hauteur",Integer.toString(r.getHauteur()));
	}
}
