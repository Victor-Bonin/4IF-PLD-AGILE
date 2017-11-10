package controleur;

import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

/**
 * <pre>
 * 
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
	public void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre) {
		AlgorithmRunnable runnable = new AlgorithmRunnable(plan, controleur, fenetre);
		Thread t = new Thread(runnable);
		t.start();
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_CALCUL_TOURNEE, CharteGraphique.NOTIFICATION_COLOR);	
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherFenetre(Fenetre fenetre) {
		fenetre.setEtatCourant(fenetre.etatCalculEnCours);
		fenetre.goToVue();
	}
}
