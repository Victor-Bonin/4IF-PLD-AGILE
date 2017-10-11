package controleur;

import vue.Fenetre;
import modele.Plan;
import modele.Point;

public class EtatCercle1 extends EtatDefaut {
	// Etat atteint a partir de EtatInit a la reception de l'evenement ajouterCercle()
	// -> Attente de la saisie d'un premier point (correspondant au centre du cercle)

	@Override
	public void clicGauche(Controleur controleur, Fenetre fenetre, Plan plan, ListeDeCdes listeDeCdes, Point p) {
		if (plan.cherche(p) != null){
			fenetre.afficheMessage("Impossible d'ajouter un cercle ici : " +
					"cliquez sur un point n'appartenant pas a une forme");
		} else {
			controleur.etatCercle2.actionEntree(p, plan, listeDeCdes);
			controleur.setEtatCourant(controleur.etatCercle2);
			fenetre.afficheMessage("Ajout d'un cercle : [Clic gauche] sur le perimetre du cercle ; " +
					"[Clic droit] pour annuler");
		}
	}

}
