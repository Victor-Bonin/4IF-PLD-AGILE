package vue.etat;

import javax.swing.JPanel;

import vue.Fenetre;

public class EtatInit extends EtatDefaut{

	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {
		footer.remove(fenetre.getImportDemandeLivraisonButton());
		footer.remove(fenetre.getExportButton());
		footer.remove(fenetre.getCalculTourneeButton());
	}

}
