package controleur;

import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

public class EtatCalculEnCours extends EtatDefaut {

	@Override
	public void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre) {
		AlgorithmRunnable runnable = new AlgorithmRunnable(plan, this, controleur, fenetre);
		Thread t = new Thread(runnable);
		t.start();
	}
	
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_CALCUL_TOURNEE, CharteGraphique.NOTIFICATION_COLOR);	
	}
	
	@Override
	public void afficherFenetre(Fenetre fenetre) {
		fenetre.goToVue(fenetre.VUE_TOURNEE_CALCUL_EN_COURS);
	}
}
