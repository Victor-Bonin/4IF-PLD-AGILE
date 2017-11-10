package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

/**
 * Commande qui permute la position d'une livraison de la demande de livraison actuelle
 * @author 4104
 */
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
		if(nouvellePosition <= anciennePosition)
			this.nouvellePosition = nouvellePosition + 1;
		else
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
