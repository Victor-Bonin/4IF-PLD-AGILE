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
	public void ajouterLivraison(Plan p, Livraison l, ListeCommande listeCmd) {}
	@Override
	public void deplacerLivraison() {}
	@Override
	public void supprimerLivraison(Plan p, Livraison l, ListeCommande listeCmd) {}
	@Override
	public void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre) {}
	@Override
	public void calculerItineraire(Controleur controleur, Plan plan, Fenetre fenetre) {}
	@Override
	public void exporterFeuilleDeRoute() {}
	@Override
	public void undo(ListeCommande listeCommande) {}
	@Override
	public void redo(ListeCommande listeCommande) {}
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
}
