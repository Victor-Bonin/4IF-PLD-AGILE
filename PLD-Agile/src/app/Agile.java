package app;

import modele.Plan;

import controleur.Controleur;

public class Agile {
 	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Plan plan = new Plan();
		new Controleur(plan);
	}

}
