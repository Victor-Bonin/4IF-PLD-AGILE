package app;

import modele.Plan;

import controleur.Controleur;

public class Agile {
 	/**
 	 * Point d'entree du programme
	 * @param args
	 */
	public static void main(String[] args) {
		Plan plan = new Plan();
		new Controleur(plan);
	}

}
