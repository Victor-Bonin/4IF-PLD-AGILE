package controleur;

import vue.Fenetre;
import modele.Plan;
import modele.Forme;
import modele.Point;

public class EtatSupprimer extends EtatDefaut {
	// Etat atteint a partir de EtatInit a la reception de l'evenement supprimer()
	// -> Attente de la saisie d'un point p permettant de selectionner une forme a supprimer
	// -> Retour dans EtatInit a la reception de l'evenement clicDroit()

	@Override
	public void clicGauche(Controleur controleur, Fenetre fenetre, Plan plan, ListeDeCdes listeDeCdes, Point p) {
		Forme f = plan.cherche(p);
		if (f != null) 
			listeDeCdes.ajoute(new CdeInverse(new CdeAjout(plan, f)));
	}

}
