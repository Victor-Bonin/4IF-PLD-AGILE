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

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

public interface Etat {

	void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande);
	void ouvrirLivraison(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande);
	void ajouterLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd);
	void deplacerLivraison();
	void supprimerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd);
	void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre);
	void undo(ListeCommande listeCommande);
	void redo(ListeCommande listeCommande);
	void afficherFenetre(Fenetre fenetre);
	void afficherNotif(Fenetre fenetre);
	void calculerItineraire(Controleur controleur, Plan plan, Fenetre fenetre);
	void creerLivraison(Fenetre fenetre);
	void obtenirPlusProcheIntersection(Fenetre fenetre, Plan plan, double x, double y);
	void commencerChoixIntersection(Fenetre fenetre);
	void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande);
	void exporterFeuilleDeRoute(Tournee tournee);
	void annulerCreation(Fenetre fenetre);
}
