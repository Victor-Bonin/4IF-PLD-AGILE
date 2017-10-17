/**
 * This file has been created by vbonin, on 11 oct. 2017
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr

_____   _   _____   __   _   _     _   _____   __   _   _   _   _____  
|  _  \ | | | ____| |  \ | | | |   / / | ____| |  \ | | | | | | | ____| 
| |_| | | | | |__   |   \| | | |  / /  | |__   |   \| | | | | | | |__   
|  _  { | | |  __|  | |\   | | | / /   |  __|  | |\   | | | | | |  __|  
| |_| | | | | |___  | | \  | | |/ /    | |___  | | \  | | |_| | | |___  
|_____/ |_| |_____| |_|  \_| |___/     |_____| |_|  \_| \_____/ |_____| 



 */
package controleur;

import modele.Plan;
import vue.Fenetre;

public class Controleur {
	private Plan plan;
	private ListeCommande listeCommande;
	private Etat etatCourant;
	private Fenetre fenetre;

	//Instances associees a chaque etat possible du controleur
	protected final EtatInit etatInit = new EtatInit();
	protected final EtatDemandeOuverte etatDemandeOuverte = new EtatDemandeOuverte();
	protected final EtatPlanOuvert etatPlanOuvert = new EtatPlanOuvert();
	protected final EtatModifie etatModifie = new EtatModifie();
	protected final EtatCalcule etatCalcule = new EtatCalcule();

	/**
	 * Cr�e le controleur de l'app
	 * @param plan le plan
	 */
	public Controleur(Plan plan) {
		this.plan = plan;
		etatCourant = etatInit;
		listeCommande = new ListeCommande();
		fenetre = new Fenetre(this, plan);
	}

	/**
	 * Change l'�tat courant du controleur
	 * @param etat le nouvel �tat courant
	 */
	protected void setEtatCourant(Etat etat) {
		etatCourant = etat;
	}

	/**
	 * Methode appelee apres un clic sur le bouton "Importer un plan"
	 * ou "Changer de plan"
	 */
	public void ouvrirPlan() {
		//etatCourant.ouvrirPlan(this, plan, fenetre, listeCommande);
		fenetre.goToPlanOpened();
	}

	/**
	 * M�thode appel�e apr�s un clic sur le bouton "Importer une demande de livraison"
	 * ou "Importer une nouvelle demande de livraison"
	 */
	public void ouvrirLivraison() {
		etatCourant.ouvrirLivraison(this, plan, fenetre, listeCommande);
	}


	/**
	 * M�thode appel�e apr�s un clic sur le bouton "Optimisier l'ordre des livraisons"
	 */
	public void calculerListeOpt() {
		etatCourant.calculerListeOpt(this, plan, fenetre);
	}

	/**
	 * M�thode appel�e apr�s cr�e avoir un point de livraison via la carte ou le bouton "+"
	 */
	public void ajouterLivraison() {
		etatCourant.ajouterLivraison();
	}

	/**
	 * M�thode appel�e apr�s avoir permuter un point de livraison avec un autre
	 */
	public void permuterLivraison() {
		etatCourant.permuterLivraison();
	}

	/**
	 * M�thode appel�e apr�s avoir supprimer un point de livraison
	 */
	public void supprimerLivraison() {
		etatCourant.supprimerLivraison();
	}

	/**
	 * M�thode appel�e apr�s avoir JE SAIS PO...
	 */
	public void calculerItineraire() {
		etatCourant.calculerItineraire();
	}

	/**
	 * M�thode appel�e apr�s un clic sur le bouton "Exporter feuille de route"
	 */
	public void exporterFeuilleDeRoute() {
		etatCourant.exporterFeuilleDeRoute();
	}

	/**
	 * M�thode appel�e apr�s un clic sur le bouton "Undo" ou apr�s press� Ctrl+Z
	 */
	public void undo() {
		etatCourant.undo(listeCommande);
	}

	/**
	 * M�thode appel�e apr�s un clic sur le bouton "Redo" ou apr�s press� Ctrl+Y
	 */
	public void redo() {
		etatCourant.redo(listeCommande);
	}
}
