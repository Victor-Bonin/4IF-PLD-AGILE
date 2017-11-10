package vue.etat;

import javax.swing.JPanel;

import modele.Plan;
import vue.Fenetre;
import vue.VuePlan;
import vue.VueTournee;

/**
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
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
 * 
 * 
 * @author 4104
 */
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

		if(plan.getDemandeLivraison().getLivraisons().isEmpty() && plan.getDemandeLivraison().getEntrepot()==null) {
			vuePlan.nettoyerIcones();
		}else {
			vueTournee.initTournee();
			vueTournee.ajouterDragAndDropListener();
			if(vueTournee.getElementsTournee().size()>2){
				vueTournee.afficherBoutonsSuppression();
			}
			vuePlan.afficherIcones(plan.getDemandeLivraison());
		}
		vuePlan.activerBouton(true);
		vueTournee.ajouterBoutonPlus();
		vuePlan.activerAnnulationBouton(false);
		fenetre.ajouterEcouteursSynchro();
	}
}
