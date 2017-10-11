package controleur;

import vue.Fenetre;
import modele.Plan;
import modele.Forme;

public class EtatDeplacer2 extends EtatDefaut {
	// Etat atteint a partir de EtatDeplacer1 a la reception de l'evenement clicGauche(p) quand p est un point 
	// appartenant a une forme f
	// -> actionEntree initialise formeSelectionnee avec f
	// -> A chaque fois qu'une fleche est tapee au clavier, formeSelectionnee est deplacee d'une unite dans la direction correspondante
	// -> Retour dans EtatInit a la reception de l'evenement clicDroit()

	private Forme formeSelectionnee;
	
	@Override
	public void carSaisi(Plan plan, ListeDeCdes listeDeCdes, int codeCar){
		int deltaX = 0;
		int deltaY = 0;
		switch (codeCar){
		case 37: // code de fleche gauche
			deltaX = -1;
			break;
		case 39: // code de fleche droite
			deltaX = 1;
			break;
		case 38: // code de fleche haut
			deltaY = -1;
			break;
		case 40: // code de fleche bas
			deltaY = 1;
			break;
		default: 
			break;
		}
		if ((deltaX != 0) || (deltaY != 0)){
			if (formeSelectionnee.deplacementPossible(deltaX, deltaY, plan))
				listeDeCdes.ajoute(new CdeDeplace(formeSelectionnee,deltaX,deltaY));
		}
	}
	
	@Override
	public void clicDroit(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes) {
		formeSelectionnee.setEstSelectionne(false);
		fenetre.autoriseBoutons(true);
		fenetre.afficheMessage("");
		controleur.setEtatCourant(controleur.etatInit);
	}

	protected void actionEntree(Forme formeSelectionnee) {
		this.formeSelectionnee = formeSelectionnee;
		formeSelectionnee.setEstSelectionne(true);
	}


}
