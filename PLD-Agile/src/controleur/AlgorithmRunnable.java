package controleur;

import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

/**
 * <pre>
 * Permet de gerer le calcul de la tournee dans un thread separe
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
public class AlgorithmRunnable implements Runnable {
	private Plan plan;
	private Fenetre fenetre;
	private Controleur controleur;

	/**
	 * Constructeur.
	 * 
	 * @param plan plan sur lequel on execute les algorithmes
	 * @param ctrl le controleur de l'application
	 * @param fenetre fenetre de l'instance principale de l'application
	 */
	public AlgorithmRunnable(Plan plan, Controleur ctrl, Fenetre fenetre){
		this.plan = plan;
		controleur = ctrl;
		this.fenetre = fenetre;
	}

	/** Calcule la tournee puis indique au controleur qu'il a fini en changeant son etat  */
	public void run() {
		try {
			plan.calculTournee();
			controleur.setEtatCourant(controleur.etatCalcule);
			controleur.afficherNotif();
		} catch (Exception e) {
			fenetre.changeNotification(Textes.NOTIF_CALCUL_TOURNEE_FAILED, CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);	
			controleur.setEtatCourant(controleur.etatDemandeOuverte);
		} finally {
			controleur.afficherFenetre();
		}
	}       

}
