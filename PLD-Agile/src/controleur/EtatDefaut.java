/**
 * @author 4104
 */
package controleur;

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
	public void ajouterLivraison() {}
	@Override
	public void permuterLivraison() {}
	@Override
	public void supprimerLivraison() {}
	@Override
	public void calculerListeOpt(Controleur controleur, Plan plan, Fenetre fenetre) {}
	@Override
	public void calculerItineraire() {}
	@Override
	public void exporterFeuilleDeRoute() {}
	@Override
	public void undo(ListeCommande listeCommande) {}
	@Override
	public void redo(ListeCommande listeCommande) {}

}
