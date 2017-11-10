package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

/**
 * <pre>
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
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
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
public abstract class EtatDefaut implements Etat{

	/** {@inheritDoc}  */
	@Override
	public void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {}

	/** {@inheritDoc}  */
	@Override
	public void ouvrirLivraison(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {}

	/** {@inheritDoc}  */
	@Override
	public void ajouterLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd, int position) {}

	/** {@inheritDoc}  */
	@Override
	public void deplacerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd, int anciennePos, int nouvellePos) {}

	/** {@inheritDoc}  */
	@Override
	public void supprimerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd, int position) {}

	/** {@inheritDoc}  */
	@Override
	public void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void calculerItineraire(Controleur controleur, Plan plan, Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void exporterFeuilleDeRoute(Fenetre fenetre, Tournee tournee) {}

	/** {@inheritDoc}  */
	@Override
	public void undo(ListeCommande listeCommande, Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void redo(ListeCommande listeCommande, Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void afficherFenetre(Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void afficherNotif(Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void creerLivraison(Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void creerLivraisonApres(Fenetre fenetre, int position) {}

	/** {@inheritDoc}  */
	@Override 
	public void obtenirPlusProcheIntersection(Fenetre fenetre, Plan plan ,double x, double y) {}

	/** {@inheritDoc}  */
	@Override 
	public void commencerChoixIntersection(Fenetre fenetre) {}

	/** {@inheritDoc}  */
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {}

	/** {@inheritDoc}  */
	@Override
	public void annulerCreation(Fenetre fenetre) {}
}
