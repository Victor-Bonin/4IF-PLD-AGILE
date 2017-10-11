package controleur;

import vue.Fenetre;
import modele.Plan;
import modele.Point;

public class EtatRectangle1 extends EtatDefaut {
	// Etat atteint a partir de EtatInit a la reception de l'evenement ajouterRectangle()
	// -> Attente de la saisie d'un premier point (correspondant a un angle du rectangle)

	@Override
	public void clicGauche(Controleur controleur, Fenetre fenetre, Plan plan, ListeDeCdes listeDeCdes, Point p) {
		if (plan.cherche(p) != null){
			fenetre.afficheMessage("Impossible d'ajouter un rectangle ici : " +
					"cliquer sur un point n'appartenant pas a une forme");
		} else {
			fenetre.afficheMessage("Ajout d'un rectangle : [Clic gauche] sur l'angle oppose du rectangle ; " +
					"[Clic droit] pour annuler");
			controleur.etatRectangle2.actionEntree(p, plan, listeDeCdes);
			controleur.setEtatCourant(controleur.etatRectangle2);
		}
	}

}
