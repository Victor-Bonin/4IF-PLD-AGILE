package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

/**
 * <pre>
 * Commande qui permute la position d'une livraison de la demande de livraison actuelle.
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
public class CommandePermuter implements Commande {

	private Plan plan;
	private Livraison livraison;
	private int anciennePosition;
	private int nouvellePosition;
	
	/**
	 * Cree la commande qui permet de changer la position d'une livraison dans la demande de livraison
	 * @param plan plan contenant la livraison
	 * @param livraison livraison dont on veut changer la position
	 * @param anciennePosition index de la livraison avant le changement de plance
	 * @param predecesseurCible index de l'element survole, donc le predecesseur : -1 pour l'entrepot
	 */
	public CommandePermuter(Plan plan, Livraison livraison, int anciennePosition, int predecesseurCible) {
		this.plan = plan;
		this.livraison = livraison;
		this.anciennePosition = anciennePosition;
		if(predecesseurCible <= anciennePosition)
			this.nouvellePosition = predecesseurCible + 1;
		else
			this.nouvellePosition = predecesseurCible;
	}

	/** {@inheritDoc}  */
	@Override
	public void doCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);
		plan.ajouterPointLivraison(livraison, nouvellePosition);
	}

	/** {@inheritDoc}  */
	@Override
	public void undoCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);
		plan.ajouterPointLivraison(livraison, anciennePosition);
	}

}
