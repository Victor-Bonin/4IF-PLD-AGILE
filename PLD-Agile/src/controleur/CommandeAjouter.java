package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

/**
 * Commande qui ajoute une livraison a la demande de livraison actuelle
 * @author 4104
 */
public class CommandeAjouter implements Commande {

	private Plan plan;
	private Livraison livraison;
	private int position;
	
	/**
	 * Cree la commande qui ajoute la livraison l au plan p
	 * @param pln Plan	
	 * @param lvrsn Livraison
	 */
	public CommandeAjouter(Plan pln, Livraison lvrsn, int positionDansListe) {
		plan = pln;
		livraison = lvrsn;
		position = positionDansListe;
	}
	
	@Override
	public void doCde() throws ExceptionPlanCo {
		plan.ajouterPointLivraison(livraison, position);
	}

	@Override
	public void undoCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);
	}

}
