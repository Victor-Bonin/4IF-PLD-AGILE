package controleur;

import modele.Plan;
import vue.Fenetre;

/**
 * Permet de gerer le calcul de la tournee dans un thread separe
 * @author 4104
 */
public class AlgorithmRunnable implements Runnable {

	private Plan plan;
	private EtatDemandeOuverte etat;
	private Fenetre fenetre;
	private Controleur controleur;

	public AlgorithmRunnable(Plan plan, EtatDemandeOuverte et, Controleur ctrl, Fenetre fen){
		this.plan = plan;
		etat = et;
		fenetre= fen;
		controleur = ctrl;
	}

	/**
	 * Calcule la tournee puis indique au controleur lorsqu'il a fini
	 */
	public void run() {
		try {
			plan.calculTournee();
		} catch (Exception e) {
			System.out.println("Erreur lors de l'execution du thread");
			e.printStackTrace();
		}
		etat.finAlgo(controleur, fenetre);
	}       

}
