/**
 * @author 4104
 */
package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

public abstract class EtatDefaut implements Etat{

	@Override
	public void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {}
	@Override
	public void ouvrirLivraison(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {}
	@Override
	public void ajouterLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd) {}
	@Override
	public void deplacerLivraison() {}
	@Override
	public void supprimerLivraison(Fenetre f, Plan p, Livraison l, ListeCommande listeCmd) {}
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
	public void obtenirPlusProcheIntersection(Fenetre fenetre, Plan plan ,double x, double y) {}
	@Override 
	public void commencerChoixIntersection(Fenetre fenetre) {}
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {}
	@Override
	public void annulerCreation(Fenetre fenetre) {}
}
