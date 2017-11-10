package controleur;

import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

/**
 * <pre>
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
 * @see controleur.EtatPlanOuvert
 * @author 4104
 */
public class EtatDemandeOuverte extends EtatPlanOuvert{

	/** {@inheritDoc}  */
	@Override
	public void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre) {
		controleur.setEtatCourant(controleur.etatCalculEnCours);
		controleur.afficherFenetre();
		controleur.afficherNotif();
		controleur.calculerTournee();
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_MUST_CALCUL_TOURNEE, CharteGraphique.NOTIFICATION_COLOR);
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherFenetre(Fenetre fenetre) {
		fenetre.setEtatCourant(fenetre.etatDemandeOuverte);
		fenetre.goToVue();
	}

	/** {@inheritDoc}  */
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
		calculerTournee(controleur, plan, fenetre);		
	}
}

