package controleur;
import vue.Fenetre;
import modele.Plan;
import modele.Point;
import modele.Cercle;

public class EtatCercle2 extends EtatDefaut {
	// Etat atteint a partir de EtatCercle1 a la reception de l'evenement clicGauche(p)
	// -> actionEntree cree le cercle de centre p
	// -> Mise a jour de cercle a chaque fois que la souris est bougee
	// -> Mise a jour de cercle quand un deuxieme point est saisi, puis retour dans EtatInit

	private Cercle cercle;

	@Override
	public void clicGauche(Controleur controleur, Fenetre fenetre, Plan plan, ListeDeCdes listeDeCdes, Point p) {
		cercle.setEstSelectionne(false);
		cercle.miseAJourTaille(p, plan);
		if (cercle.getRayon() == 0) 
			listeDeCdes.annule();
		controleur.setEtatCourant(controleur.etatInit);
		fenetre.afficheMessage("");
		fenetre.autoriseBoutons(true);
	}
	
	@Override
	public void sourisBougee(Plan plan, Point p) {
		cercle.miseAJourTaille(p, plan);
	}
	
	@Override
	public void clicDroit(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes) {
		listeDeCdes.annule(); // Le cercle en cours de construction est definitivement enleve listeDeCdes
		super.clicDroit(controleur, fenetre, listeDeCdes);
	}

	/**
	 * Methode appelee avant d'entrer dans l'etat this : ajout dans plan d'un cercle de centre p et de rayon 1
	 * @param p le centre du cercle a ajouter
	 * @param plan
	 * @param listeDeCdes
	 */
	protected void actionEntree(Point p, Plan plan, ListeDeCdes listeDeCdes) {
		cercle = new Cercle(p, 1);
		cercle.setEstSelectionne(true);
		listeDeCdes.ajoute(new CdeAjout(plan, cercle));
	}

}
