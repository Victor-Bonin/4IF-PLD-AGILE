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

Classe représentant la commande de permutation d'un point de livraison.
Une commande peut être exécutée et annulée.
@author 4104
 */
package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

public class CommandePermuter implements Commande {

	private Plan plan;
	private Livraison livraison;
	private int anciennePosition;
	private int nouvellePosition;
	
	/**
	 * Cree la commande qui permet de changer la position d'une livraison dans la demande de livraison
	 * @param p
	 * @param l
	 * @param positionDansListe
	 */
	public CommandePermuter(Plan pln, Livraison lvrsn, int anciennePosition, int nouvellePosition) {
		plan = pln;
		livraison = lvrsn;
		this.anciennePosition = anciennePosition;
		this.nouvellePosition = nouvellePosition;
	}
	
	@Override
	public void doCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);
		plan.ajouterPointLivraison(livraison, nouvellePosition);
	}

	@Override
	public void undoCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);
		plan.ajouterPointLivraison(livraison, anciennePosition);
	}

}
