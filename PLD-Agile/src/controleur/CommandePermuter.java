package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

/**
 * Commande qui permute la position d'une livraison de la demande de livraison actuelle.
 * <br/><br/>
 * Authors : 									<br/>
 * romain.goutte-fangeas@insa-lyon.fr			<br/>
 *               ____							<br/>
 *           __--    --_						<br/>
 *          /   -        -						<br/>
 *         / /-- ------\  \						<br/>
 *        / /           \  |					<br/>
 *        | |           ?  |					<br/>
 *        | ? _--   -== \ /?					<br/>
 *         \| 'o > < o>  |||					<br/>
 *         \\    / \      )|					<br/>
 *          \\   .| )    |_/					<br/>
 *           |  :_____: :|						<br/>
 *            \  <==="  /|						<br/>
 *             \      .: /|\					<br/>
 *             )\_   .: / |:"--___				<br/>
 *         __-:|\ """ _-  |:::::::				<br/>
 *       _-::::\ "-_.-   /::::::::				<br/>
 *    _--:::::::| .|"-_  |::::::::				<br/>
 *  -"::::::::::\  | { -_|::::::::				<br/>
 * lucas.ouaniche-herbin@insa-lyon.fr			<br/>
 * lucas.marie@insa-lyon.fr						<br/>
 * clara.pourcel@insa-lyon.fr					<br/>
 * pierrick.chauvet@insa-lyon.fr				<br/>
 * bastien.guiraudou@insa-lyon.fr				<br/>
 * victor.bonin@insa-lyon.fr					<br/>
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
