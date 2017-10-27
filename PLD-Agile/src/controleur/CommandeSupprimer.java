package controleur;

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
	public void doCde() {
		// Appeller plan.supprimerLivraison(Livraison)		
	}

	@Override
	public void undoCde() {
		// Appeller plan.ajouterLivraison(Livraison)
	}

}
