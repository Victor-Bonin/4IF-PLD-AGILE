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

import modele.ExceptionPlanCo;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;
import vue.VuePlan;
import xml.AnnulationXML;
import xml.DeserialiseurXML;

public class EtatCalcule extends EtatPlanOuvert {
	
	@Override 
	public void obtenirPlusProcheIntersection(Fenetre vue, Plan p ,double x, double y) {
		Intersection i = p.obtenirPlusProcheIntersection(x, y);
		vue.ajouterIcone(i);
	}
	
	@Override 
	public void commencerChoixIntersection(Fenetre vue) {
		vue.commencerChoixIntersection();
	}

	@Override
	public void creerLivraison(Fenetre fenetre) {
		fenetre.setEtatCourant(fenetre.etatAjoutLivraison);
		fenetre.goToVue();
	}

	@Override
	public void ajouterLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd) {
		//TODO : supprimer fenetre quand pattern en place
		try {
			listeCmd.ajoute(new CommandeAjouter(p, l));
			p.ajouterPointLivraison(l);
			fenetre.initialiserTournee();
			fenetre.repaint();
		}
		catch (ExceptionPlanCo ex){fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);// TODO : traiter l'exception
		}
	}

	@Override
	public void deplacerLivraison() {
		// TODO Auto-generated method stub
	}

	@Override
	public void supprimerLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd) {
		try {
			listeCmd.ajoute(new CommandeSupprimer(p, l));
			p.supprimerPointLivraison(l);
			fenetre.initialiserTournee();
			fenetre.repaint();
		}
		catch (ExceptionPlanCo ex){
			fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
		}
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
		fenetre.setEtatCourant(fenetre.etatCalcule);
		fenetre.goToVue();
	}
	
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
			
	}
	
	@Override 
	public void annulerCreation(Fenetre fenetre) {
		fenetre.annulerCreation();
	}

}
