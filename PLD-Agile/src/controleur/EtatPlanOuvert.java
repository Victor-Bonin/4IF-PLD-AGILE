package controleur;

import modele.ExceptionPlanCo;
import modele.Plan;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;
import xml.DeserialiseurXML;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 * <pre>
 * Classe représentant l'état de l'app après l'ouverture d'un plan.
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
 * </pre>
 * 
 *  
 * @author 4104
 */


public class EtatPlanOuvert extends EtatInit {

	/** {@inheritDoc}  */
	@Override
	public void ouvrirLivraison(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
			try{
				fenetre.changeNotification(Textes.NOTIF_LOADING, CharteGraphique.NOTIFICATION_COLOR);
				DeserialiseurXML.chargerDemandeLivraison(plan);
				listeCommande.reset();
				controleur.setEtatCourant(controleur.etatDemandeOuverte);
				controleur.afficherNotif();
			}
			catch(ExceptionPlanCo ex) {
				if(ex.getMessage() != ExceptionPlanCo.ANNULATION_OUVERTURE_FICHIER) {
					listeCommande.reset();
					plan.resetDemandeLivraison();
					controleur.setEtatCourant(controleur.etatPlanOuvert);
					fenetre.getVuePlan().nettoyerIcones();
					fenetre.getVuePlan().revalidate();
					fenetre.getVuePlan().repaint();
				}
				if(ex.getMessage() != "") {
					fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
				}else
					fenetre.changeNotification(Textes.NOTIF_IMPORT_DEMANDE_LIVRAISON_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
			} catch(SAXException | IOException ex) {
				//controleur.setEtatCourant(etat);
				fenetre.changeNotification(Textes.NOTIF_IMPORT_DEMANDE_LIVRAISON_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
			}
			catch(Exception ex) {
				listeCommande.reset();
				controleur.setEtatCourant(controleur.etatPlanOuvert);
				fenetre.changeNotification(Textes.NOTIF_IMPORT_DEMANDE_LIVRAISON_FAILED, CharteGraphique.NOTIFICATION_FORBIDDEN_COLOR);
			}finally{
				controleur.afficherFenetre();
			}
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_MUST_IMPORT_DEMANDE, CharteGraphique.NOTIFICATION_COLOR);
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherFenetre(Fenetre fenetre) {
		fenetre.setEtatCourant(fenetre.etatPlanOuvert);
		fenetre.goToVue();
	}

	/** {@inheritDoc}  */
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
		ouvrirLivraison(controleur, plan, fenetre, listeCommande);		
	}
	
}
