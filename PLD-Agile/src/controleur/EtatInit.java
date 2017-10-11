package controleur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import modele.Plan;
import vue.Fenetre;
import xml.DeserialiseurXML;
import xml.ExceptionXML;
import xml.SerialiseurXML;

public class EtatInit extends EtatDefaut {
	// Etat initial 
	
	@Override
	public void ajouterCercle(Controleur controleur, Fenetre fenetre) {
		fenetre.autoriseBoutons(false);
		fenetre.afficheMessage("Ajout d'un cercle : [Clic gauche] sur le centre du cercle ; " +
				"[Clic droit] pour annuler");
		controleur.setEtatCourant(controleur.etatCercle1);
	}

	@Override
	public void ajouterRectangle(Controleur controleur, Fenetre fenetre) {
		fenetre.autoriseBoutons(false);
		fenetre.afficheMessage("Ajout d'un rectangle : [Clic gauche] sur un angle du rectangle ; " +
				"[Clic droit] pour annuler");
		controleur.setEtatCourant(controleur.etatRectangle1);
	}

	@Override
	public void supprimer(Controleur controleur, Fenetre fenetre){
		fenetre.autoriseBoutons(false);
		fenetre.afficheMessage("Suppression : [Clic gauche] sur la forme a supprimer ; " +
				"[Clic droit] pour sortir du mode suppression");
		controleur.setEtatCourant(controleur.etatSupprimer);
	}

	@Override
	public void deplacer(Controleur controleur, Fenetre fenetre){
		fenetre.autoriseBoutons(false);
		fenetre.afficheMessage("Deplacer une forme : [Clic gauche] pour selectionner la forme a deplacer ; " +
				"[Clic droit] pour annuler");
		controleur.setEtatCourant(controleur.etatDeplacer1);
	}
	
	@Override
	public void diminuerEchelle(Fenetre fenetre) {
		int echelle = fenetre.getEchelle();
		if (echelle > 1){
			fenetre.setEchelle(echelle-1);
			fenetre.afficheMessage("Nouvelle echelle = "+(echelle-1));
		}
		else fenetre.afficheMessage("L'echelle ne peut pas etre diminuee.");
	}
	
	@Override
	public void augmenterEchelle(Fenetre fenetre) {
		int echelle = fenetre.getEchelle();
		if (echelle < 15){
			fenetre.setEchelle(echelle+1);		
			fenetre.afficheMessage("Nouvelle echelle = "+(echelle+1));
		}
		else fenetre.afficheMessage("L'echelle ne peut pas etre augmentee.");
	}

	@Override
	public void undo(ListeDeCdes listeDeCdes){
		listeDeCdes.undo();
	}

	@Override
	public void redo(ListeDeCdes listeDeCdes){
		listeDeCdes.redo();
	}

	@Override
	public void sauver(Plan plan, Fenetre fenetre){
		try {
			SerialiseurXML.getInstance().sauver(plan);
		} catch (ParserConfigurationException
				| TransformerFactoryConfigurationError
				| TransformerException | ExceptionXML e) {
			fenetre.afficheMessage(e.getMessage());
		}
	}


	@Override
	public void ouvrir(Plan plan, ListeDeCdes listeDeCdes, Fenetre fenetre){
		int largeur = plan.getLargeur();
		int hauteur = plan.getHauteur();
		try {
			DeserialiseurXML.charger(plan);
		} catch (ParserConfigurationException 
				| SAXException | IOException 
				| ExceptionXML | NumberFormatException e) {
			fenetre.afficheMessage(e.getMessage());
			plan.reset(largeur, hauteur);
		}
		listeDeCdes.reset();
	}


}
