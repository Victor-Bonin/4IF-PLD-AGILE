package controleur;

import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

public class AlgorithmRunnable implements Runnable {
	private Plan plan;
	private Etat etat;
	private Fenetre fenetre;
	private Controleur controleur;

	public AlgorithmRunnable(Plan plan, Etat et, Controleur ctrl, Fenetre fen){
		this.plan = plan;
		etat = et;
		fenetre = fen;
		controleur = ctrl;
	}
		
	public void run(){
		try {
			plan.calculTournee();
			controleur.setEtatCourant(controleur.etatCalcule);
			controleur.afficherFenetre();
			controleur.afficherNotif();
		} catch (Exception e) {
			fenetre.changeNotification(Textes.NOTIF_CALCUL_TOURNEE_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);	
			e.printStackTrace();
		}
	}       

}
