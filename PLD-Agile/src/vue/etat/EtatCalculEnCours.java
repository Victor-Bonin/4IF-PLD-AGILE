package vue.etat;

import javax.swing.JPanel;

import vue.Fenetre;
import vue.PersoButton;

/**
 * <pre>
 * Etat de la vue en cours de calcul d'itineraire
 * 
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o . . o.  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  '==="  /|
 *             \      .: /|\
 *             )\_   .: / |:"--___
 *         __-:|\ """ _-  |:::::::
 *       _-::::\ "-_.-   /::::::::
 *    _--:::::::| .|"-_  |::::::::
 *  -"::::::::::\  | { -_|::::::::
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr
 * </pre>
 * 
 * 
 * @author 4104
 */
public class EtatCalculEnCours extends EtatDefaut {

	/** {@inheritDoc}  */
	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {
		footer.remove(fenetre.getImportDemandeLivraisonButton());
		footer.remove(fenetre.getExportButton());
		PersoButton calculTourneeButton = fenetre.getCalculTourneeButton();
		footer.add(calculTourneeButton);
		calculTourneeButton.setEnabled(false);
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherVue(Fenetre fenetre) {
		fenetre.getVuePlan().activerBoutonImportDemande(false);
		fenetre.requestFocus();
	}

}
