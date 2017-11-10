package controleur;

import modele.ExceptionPlanCo;
import modele.Plan;
import vue.Fenetre;
import vue.CharteGraphique;
import vue.Textes;
import xml.DeserialiseurXML;

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
public class EtatInit extends EtatDefaut{

	@Override
	public void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {
		try {
			fenetre.changeNotification(Textes.NOTIF_LOADING, CharteGraphique.NOTIFICATION_COLOR);
			listeCommande.reset();
			DeserialiseurXML.charger(plan);
			controleur.setEtatCourant(controleur.etatPlanOuvert);
			controleur.afficherFenetre();
			controleur.afficherNotif();
		}
		catch (ExceptionPlanCo ex){
			if (ex.getMessage() != "") 
				fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
			else
				fenetre.changeNotification(Textes.NOTIF_IMPORT_PLAN_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
		}
		catch (Exception ex) {
			fenetre.changeNotification(Textes.NOTIF_IMPORT_PLAN_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
		}
	}
	
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_MUST_IMPORT, CharteGraphique.NOTIFICATION_COLOR);
	}

	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
		ouvrirPlan(controleur, plan, fenetre, listeCommande);		
	}

}
