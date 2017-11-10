package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

/**
 * Interface listant les etats possibles pour le controleur
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
