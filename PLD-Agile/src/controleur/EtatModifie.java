package controleur;

import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

/**
 * Classe représentant l'état de l'app lorsqu'une Tournée a été modifié.
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
public class EtatModifie extends EtatCalcule{

	//TODO DELETE ??
	//TODO // ON A PAS D'ETAT MODIFE !
	@Override
	public void calculerItineraire(Controleur controleur, Plan plan, Fenetre fenetre) {
		try {
			fenetre.changeNotification(Textes.NOTIF_CALCUL_ITINERAIRE, CharteGraphique.NOTIFICATION_COULEUR);
			// TODO : changer ça en calcul itinéraire
			plan.calculerItinerairesSeuls();
			controleur.afficherFenetre();
			controleur.afficherNotif();
		}
		catch (Exception ex) {
			fenetre.changeNotification(Textes.NOTIF_CALCUL_ITINERAIRE_FAILED, CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
		}
	}
	
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_ITINERAIRE_CALCULE, CharteGraphique.NOTIFICATION_COULEUR);
	}
	
}
