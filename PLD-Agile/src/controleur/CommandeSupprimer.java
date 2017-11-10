package controleur;

import modele.ExceptionPlanCo;
import modele.Livraison;
import modele.Plan;

/**
 * Commande de suppression d'un point de livraison de la demande de livraison.
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
public class CommandeSupprimer implements Commande {

	private Plan plan;
	private Livraison livraison;
	private int position;
	
	/**
	 * Cree la commande qui supprime la livraison l du plan p
	 * @param p
	 * @param l
	 */
	public CommandeSupprimer(Plan p, Livraison l, int positionDansListe) {
		plan = p;
		livraison = l;
		position = positionDansListe;
	}
	
	@Override
	public void doCde() throws ExceptionPlanCo {
		plan.supprimerPointLivraison(livraison);		
		plan.calculerItinerairesSeuls();
	}

	@Override
	public void undoCde() throws ExceptionPlanCo {
		plan.ajouterPointLivraison(livraison, position);
		plan.calculerItinerairesSeuls();
	}

}
