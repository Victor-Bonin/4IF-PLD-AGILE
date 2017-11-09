/**
 * @author 4104
 */
package controleur;

import modele.Livraison;
import modele.Plan;
import vue.Fenetre;

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
	public void exporterFeuilleDeRoute() {}
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
