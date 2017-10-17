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



 */
package controleur;

import modele.Plan;
import vue.Fenetre;
import xml.DeserialiseurXML;

public class EtatInit implements Etat{

	@Override
	public void ouvrirPlan(Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
		try {
			DeserialiseurXML.charger(plan);
		}
		catch(Exception e) {
			
		}
	}

	@Override
	public void ouvrirLivraison() {}

	@Override
	public void ajouterLivraison() {}

	@Override
	public void permuterLivraison() {}

	@Override
	public void supprimerLivraison() {}

	@Override
	public void calculerListeOpt() {}

	@Override
	public void calculerItineraire() {}

	@Override
	public void exporterFeuilleDeRoute() {}

	@Override
	public void undo(ListeCommande listeCommande) {}

	@Override
	public void redo(ListeCommande listeCommande) {}

}
