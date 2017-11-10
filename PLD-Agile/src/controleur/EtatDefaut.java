package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

/**
 * Classe representant l'etat du controleur lorsque la tournee a ete calculee.
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
 * 
 *  
 * @author 4104
 */
public abstract class EtatDefaut implements Etat{

	@Override
	public void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {}
	@Override
	public void ouvrirLivraison(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {}
	@Override
	public void ajouterLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd, int position) {}
	@Override
	public void deplacerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd, int anciennePos, int nouvellePos) {}
	@Override
	public void supprimerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd, int position) {}
	@Override
	public void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre) {}
	@Override
	public void calculerItineraire(Controleur controleur, Plan plan, Fenetre fenetre) {}
	@Override
	public void exporterFeuilleDeRoute(Fenetre fenetre, Tournee tournee) {}
	@Override
	public void undo(ListeCommande listeCommande, Fenetre fenetre) {}
	@Override
	public void redo(ListeCommande listeCommande, Fenetre fenetre) {}
	@Override
	public void afficherFenetre(Fenetre fenetre) {}
	@Override
	public void afficherNotif(Fenetre fenetre) {}
	@Override
	public void creerLivraison(Fenetre fenetre) {}
	@Override
	public void creerLivraisonApres(Fenetre fenetre, int position) {}
	@Override 
	public void obtenirPlusProcheIntersection(Fenetre fenetre, Plan plan ,double x, double y) {}
	@Override 
	public void commencerChoixIntersection(Fenetre fenetre) {}
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {}
	@Override
	public void annulerCreation(Fenetre fenetre) {}
}
