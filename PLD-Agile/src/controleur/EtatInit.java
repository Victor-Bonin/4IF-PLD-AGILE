/**
 * This file has been created by vbonin, on 11 oct. 2017
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr

_____   _   _____   __   _   _     _   _____   __   _   _   _   _____  
|  _  \ | | | ____| |  \ | | | |   / / | ____| |  \ | | | | | | | ____| 
| |_| | | | | |__   |   \| | | |  / /  | |__   |   \| | | | | | | |__   
|  _  { | | |  __|  | |\   | | | / /   |  __|  | |\   | | | | | |  __|  
| |_| | | | | |___  | | \  | | |/ /    | |___  | | \  | | |_| | | |___  
|_____/ |_| |_____| |_|  \_| |___/     |_____| |_|  \_| \_____/ |_____| 


@author 4104
 */
package controleur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.Plan;
import vue.Fenetre;
import vue.Textes;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

public class EtatInit extends EtatDefaut{

	@Override
	public void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {
		try {
			DeserialiseurXML.charger(plan);
			controleur.setEtatCourant(controleur.etatPlanOuvert);
			listeCommande.reset();
			fenetre.goToVue(fenetre.VUE_PLAN);
			fenetre.changeNotification(Textes.NOTIF_MUST_IMPORT_DEMANDE);
		}
		catch (ExceptionXML ex){
			if (ex.getMessage() != "")
				fenetre.changeNotification(ex.getMessage());
		}
		catch (SAXException ex) {
			fenetre.changeNotification(ex.getMessage());	
		}
		catch (Exception ex) {
			fenetre.changeNotification(Textes.NOTIF_IMPORT_PLAN_FAILED);
		}
	}
}
