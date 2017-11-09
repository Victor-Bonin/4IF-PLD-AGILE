package vue.etat;

import modele.Plan;
import vue.Fenetre;
import vue.VuePlan;
import vue.VueTournee;

public class EtatModifie extends EtatCalcule {

	@Override
	public void afficherVue(Fenetre fenetre) {
		super.afficherVue(fenetre);
		VuePlan vuePlan = fenetre.getVuePlan();
		vuePlan.activerAnnulationBouton(true);
	}
}
