package controleur;

import modele.ExceptionPlanCo;
import modele.Plan;
import vue.Fenetre;
import vue.CharteGraphique;
import vue.Textes;
import xml.DeserialiseurXML;

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
 * @see controleur.EtatDefaut
 * @author 4104
 */
public class EtatInit extends EtatDefaut{

	/** {@inheritDoc}  */
	@Override
	public void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, 
			ListeCommande listeCommande) {
		try {
			fenetre.changeNotification(Textes.NOTIF_LOADING, CharteGraphique.NOTIFICATION_COULEUR);
			listeCommande.reset();
			DeserialiseurXML.charger(plan);
			controleur.setEtatCourant(controleur.etatPlanOuvert);
			controleur.afficherFenetre();
			controleur.afficherNotif();
		}
		catch (ExceptionPlanCo ex){
			if (ex.getMessage() != "") 
				fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
			else
				fenetre.changeNotification(Textes.NOTIF_IMPORT_PLAN_FAILED, CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
		}
		catch (Exception ex) {
			fenetre.changeNotification(Textes.NOTIF_IMPORT_PLAN_FAILED, CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
		}
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_MUST_IMPORT, CharteGraphique.NOTIFICATION_COULEUR);
	}

	/** {@inheritDoc}  */
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
		ouvrirPlan(controleur, plan, fenetre, listeCommande);		
	}

}
