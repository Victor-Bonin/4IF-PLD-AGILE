package vue.etat;

import javax.swing.JPanel;

import vue.Fenetre;
import vue.PersoButton;

public class EtatCalculEnCours extends EtatDefaut {

	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {
		footer.remove(fenetre.getImportDemandeLivraisonButton());
		footer.remove(fenetre.getExportButton());
		PersoButton calculTourneeButton = fenetre.getCalculTourneeButton();
		footer.add(calculTourneeButton);
		calculTourneeButton.setEnabled(false);
	}

	@Override
	public void afficherVue(Fenetre fenetre) {
		fenetre.getVuePlan().activerBouton(false);
		fenetre.requestFocus();
	}

}
