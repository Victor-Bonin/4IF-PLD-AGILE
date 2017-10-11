package app;

import modele.Plan;
import controleur.Controleur;

public class PlaCo {
	
	private static final int echelleInitiale = 10;
	private static final int hauteurPlan = 40;
	private static final int largeurPlan = 40;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Plan plan = new Plan(largeurPlan, hauteurPlan);
		new Controleur(plan, echelleInitiale);
	}

}
