package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

/**
 * <pre>
 * Commande de suppression d'un point de livraison de la demande de livraison.
 * 
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o . . o.  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  '==="  /|
 *             \      .: /|\
 *             )\_   .: / |:"--___
 *         __-:|\ """ _-  |:::::::
 *       _-::::\ "-_.-   /::::::::
 *    _--:::::::| .|"-_  |::::::::
 *  -"::::::::::\  | { -_|::::::::
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr
 * </pre>
 * 
 *  
 * @author 4104
 */
public class CommandeSupprimer implements Commande {

	private Plan plan;
	private Livraison livraison;
	private int position;
	
	/**
	 * Cree la commande qui supprime une livraison du plan
	 * @param plan plan contenant la livraison a supprimer
	 * @param livraison la livraison a supprimer
	 * @param positionDansListe index de la livraison a supprimer. Permet de la rajouter ensuite !
	 */
	public CommandeSupprimer(Plan plan, Livraison livraison, int positionDansListe) {
		this.plan = plan;
		this.livraison = livraison;
		position = positionDansListe;
	}

	/** {@inheritDoc}  */
	@Override
	public void doCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);		
		plan.calculerItinerairesSeuls();
	}

	/** {@inheritDoc}  */
	@Override
	public void undoCde() throws ExceptionPlanCo {
		plan.ajouterPointLivraison(livraison, position);
		plan.calculerItinerairesSeuls();
	}

}
