package app;

import modele.Plan;

import controleur.Controleur;

public class Agile {
	private static final int echelleInitiale = 10;
	private static final int hauteurPlan = 40;
	private static final int largeurPlan = 40;
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Plan plan = new Plan();
		Controleur ctrl = new Controleur(plan);
	}

}
