package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

/**
 * <pre>
 * Interface listant les etats possibles pour le controleur
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
public interface Etat {

	void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande);
	void ouvrirLivraison(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande);
	void ajouterLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd, int position);
	void deplacerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd, int anciennePos, int nouvellePos);
	void supprimerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd, int position);
	void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre);
	void undo(ListeCommande listeCommande, Fenetre fenetre);
	void redo(ListeCommande listeCommande, Fenetre fenetre);
	void afficherFenetre(Fenetre fenetre);
	void afficherNotif(Fenetre fenetre);
	void calculerItineraire(Controleur controleur, Plan plan, Fenetre fenetre);
	void creerLivraison(Fenetre fenetre);
	void creerLivraisonApres(Fenetre fenetre, int position);
	void obtenirPlusProcheIntersection(Fenetre fenetre, Plan plan, double x, double y);
	void commencerChoixIntersection(Fenetre fenetre);
	void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande);
	void exporterFeuilleDeRoute(Fenetre fenetre, Tournee tournee);
	void annulerCreation(Fenetre fenetre);
}
