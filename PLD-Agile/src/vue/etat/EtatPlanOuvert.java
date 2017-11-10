package vue.etat;

import javax.swing.JPanel;

import vue.Fenetre;

/**
 * <pre>
 * Etat de la vue apres qu'un plan ait ete ouvert
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
public class EtatPlanOuvert extends EtatDefaut{

	/** {@inheritDoc}  */
	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {
		footer.remove(fenetre.getExportButton());
		footer.remove(fenetre.getCalculTourneeButton());
		footer.add(fenetre.getImportDemandeLivraisonButton());
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherVue(Fenetre fenetre) {
		fenetre.setContent();
		fenetre.getVuePlan().activerAnnulationBouton(false);
		fenetre.requestFocus();
	}

}
