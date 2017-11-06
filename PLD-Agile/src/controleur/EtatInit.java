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

import modele.Plan;
import vue.Fenetre;
import vue.CharteGraphique;
import vue.Textes;
import xml.AnnulationXML;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

public class EtatInit extends EtatDefaut{

	@Override
	public void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {
		try {
			fenetre.changeNotification(Textes.NOTIF_LOADING, CharteGraphique.NOTIFICATION_COLOR);
			DeserialiseurXML.charger(plan);
			listeCommande.reset();
			controleur.setEtatCourant(controleur.etatPlanOuvert);
			controleur.afficherFenetre();
			controleur.afficherNotif();
		}
		catch (AnnulationXML ex){
			controleur.afficherNotif();
		}
		catch (ExceptionXML ex){
			if (ex.getMessage() != "")
				fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
			else
				fenetre.changeNotification(Textes.NOTIF_IMPORT_PLAN_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
		}
		catch (Exception ex) {
			fenetre.changeNotification(Textes.NOTIF_IMPORT_PLAN_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
		}
	}
	
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_MUST_IMPORT, CharteGraphique.NOTIFICATION_COLOR);
	}

	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
		ouvrirPlan(controleur, plan, fenetre, listeCommande);		
	}

}
