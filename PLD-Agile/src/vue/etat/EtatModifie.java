package vue.etat;

import modele.Plan;
import vue.Fenetre;
import vue.VuePlan;
import vue.VueTournee;

public class EtatModifie extends EtatCalcule implements Etat {

	@Override
	public void afficherVue(Fenetre fenetre) {
		VuePlan vuePlan = fenetre.getVuePlan();
		VueTournee vueTournee = fenetre.getVueTournee();
		Plan plan = fenetre.getPlan();
		
		if(plan.getDemandeLivraison().getLivraisons().isEmpty()) {
			vuePlan.nettoyerIcones();
		}else {
			vueTournee.initTournee(plan.getDemandeLivraison());
			vuePlan.afficherIcones(plan.getDemandeLivraison());
		}
		vuePlan.activerAnnulationBouton(true);
	}
}
