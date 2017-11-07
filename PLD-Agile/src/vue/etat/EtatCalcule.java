package vue.etat;

import javax.swing.JPanel;

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
		vueTournee.initTournee(fenetre.getPlan().getTournee());
		vuePlan.activerBouton(true);
	}
}
