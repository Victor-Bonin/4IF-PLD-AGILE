package vue.etat;

import javax.swing.JPanel;

import modele.Plan;
import vue.Fenetre;
import vue.VuePlan;
import vue.VueTournee;

public class EtatCalcule extends EtatDefaut {

	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {
		footer.remove(fenetre.getImportDemandeLivraisonButton());
		footer.remove(fenetre.getCalculTourneeButton());
		footer.add(fenetre.getExportButton());
	}

	@Override
	public void afficherVue(Fenetre fenetre) {
		VueTournee vueTournee = fenetre.getVueTournee();
		VuePlan vuePlan = fenetre.getVuePlan();
		Plan plan = fenetre.getPlan();

		vueTournee.initTournee(plan.getTournee());
		vuePlan.afficherIcones(plan.getTournee());
		vuePlan.activerBouton(true);
		
		vueTournee.ajouterBoutonPlus();
		vuePlan.activerAnnulationBouton(true);
		fenetre.ajouterEcouteursSynchro();
	}
}
