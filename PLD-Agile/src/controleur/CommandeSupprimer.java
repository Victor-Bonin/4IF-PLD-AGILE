package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

/**
 * Commande de suppression d'un point de livraison de la demande de livraison.
 * @author 4104
 */
public class CommandeSupprimer implements Commande {

	private Plan plan;
	private Livraison livraison;
	
	/**
	 * Cree la commande qui supprime la livraison l du plan p
	 * @param p
	 * @param l
	 */
	public CommandeSupprimer(Plan p, Livraison l) {
		plan = p;
		livraison = l;
	}
	
	@Override
	public void doCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);		
		plan.calculerItinerairesSeuls();
	}

	@Override
	public void undoCde() throws ExceptionPlanCo {
		plan.ajouterPointLivraison(livraison);
		plan.calculerItinerairesSeuls();
	}

}
