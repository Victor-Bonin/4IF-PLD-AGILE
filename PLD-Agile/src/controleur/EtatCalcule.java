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

Classe représentant l'état de l'app lorsque la tournée a été calculé.
@author 4104
 */
package controleur;

import modele.Livraison;
import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

public class EtatCalcule extends EtatDemandeOuverte {

	@Override
	public void creerLivraison(Fenetre fenetre) {
		fenetre.goToVue(fenetre.VUE_TOURNEE_AJOUT);
	}

	@Override
	public void ajouterLivraison(Plan p, Livraison l, ListeCommande listeCmd) {
		listeCmd.ajoute(new CommandeAjouter(p, l));
	}

	@Override
	public void deplacerLivraison() {
		// TODO Auto-generated method stub
	}

	@Override
	public void supprimerLivraison(Plan p, Livraison l, ListeCommande listeCmd) {
		listeCmd.ajoute(new CommandeSupprimer(p, l));
	}
	
	@Override
	public void undo(ListeCommande listeCommande) {
		listeCommande.undo();
	}
	@Override
	public void redo(ListeCommande listeCommande) {
		listeCommande.redo();
	}

	@Override
	public void exporterFeuilleDeRoute() {
		
	}

	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_TOURNEE_CALCULE, CharteGraphique.NOTIFICATION_COLOR);
	}

	@Override
	public void afficherFenetre(Fenetre fenetre) {
		fenetre.goToVue(Fenetre.VUE_TOURNEE_CALCULEE);
	}

}
