package vue.etat;

import modele.Plan;
import vue.Fenetre;
import vue.VuePlan;
import vue.VueTournee;

public class EtatModifie extends EtatDefaut {

	@Override
	public void afficherVue(Fenetre fenetre) {
		VuePlan vuePlan = fenetre.getVuePlan();
		vuePlan.activerAnnulationBouton(true);

		VueTournee vueTournee = fenetre.getVueTournee();
		vueTournee.autoriserClicDroit();
		fenetre.ajouterEcouteursSynchro();
		fenetre.requestFocus();
	}
}
