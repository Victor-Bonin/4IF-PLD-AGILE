package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

/**
 * Interface listant les etats possibles pour le controleur
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
