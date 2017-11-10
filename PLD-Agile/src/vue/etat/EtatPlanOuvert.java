package vue.etat;

import javax.swing.JPanel;

import vue.Fenetre;

public class EtatPlanOuvert extends EtatDefaut{

	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {
		footer.remove(fenetre.getExportButton());
		footer.remove(fenetre.getCalculTourneeButton());
		footer.add(fenetre.getImportDemandeLivraisonButton());
	}

	@Override
	public void afficherVue(Fenetre fenetre) {
		fenetre.setContent();
		fenetre.getVuePlan().activerAnnulationBouton(false);
		fenetre.requestFocus();
	}

}
