package controleur;

import java.io.IOException;

import modele.ExceptionPlanCo;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.CharteGraphique;
import vue.Fenetre;
import vue.Textes;

/**
 * <pre>
 * Classe representant l'etat du controleur lorsque la tournee a ete calculee.
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
public class EtatCalcule extends EtatPlanOuvert {

	/** {@inheritDoc}  */
	@Override 
	public void obtenirPlusProcheIntersection(Fenetre vue, Plan p ,double x, double y) {
		Intersection i = p.obtenirPlusProcheIntersection(x, y);
		vue.ajouterIcone(i);
	}

	/** {@inheritDoc}  */
	@Override 
	public void commencerChoixIntersection(Fenetre vue) {
		vue.commencerChoixIntersection();
	}

	/** {@inheritDoc}  */
	@Override
	public void creerLivraison(Fenetre fenetre) {
		fenetre.getVueTournee().creerLivraison();
	} 

	/** {@inheritDoc}  */
	@Override
	public void creerLivraisonApres(Fenetre fenetre, int position) {
		fenetre.getVueTournee().creerLivraisonApres(position);
	} 

	/** {@inheritDoc}  */
	@Override
	public void ajouterLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd, int position) {
		try {
			listeCmd.ajoute(new CommandeAjouter(p, l, position));
			fenetre.changeNotification(Textes.NOTIF_LIVRAISON_AJOUTEE, CharteGraphique.NOTIFICATION_COULEUR);
		}catch (ExceptionPlanCo ex){
			fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
			// TODO : traiter l'exception
		}finally {
			fenetre.setEtatCourant(fenetre.etatModifie);
			fenetre.goToVue();
		}
	}

	/** {@inheritDoc}  */
	@Override
	public void deplacerLivraison(Fenetre fenetre, Plan plan, Livraison livraison, ListeCommande listeCmd, int anciennePos, int nouvellePos) {
		try {
			listeCmd.ajoute(new CommandePermuter(plan, livraison, anciennePos, nouvellePos));
			fenetre.setEtatCourant(fenetre.etatModifie);
			fenetre.goToVue();
		} catch(ExceptionPlanCo ex){
			fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
		}
	}

	/** {@inheritDoc}  */
	@Override
	public void supprimerLivraison(Fenetre fenetre, Plan p, Livraison l, ListeCommande listeCmd, int position) {
		try {
			listeCmd.ajoute(new CommandeSupprimer(p, l, position));
			fenetre.changeNotification(Textes.NOTIF_LIVRAISON_SUPPRIMEE, CharteGraphique.NOTIFICATION_COULEUR);
		}catch (ExceptionPlanCo ex){
			fenetre.changeNotification(ex.getMessage(), CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
		}finally {
			fenetre.setEtatCourant(fenetre.etatModifie);
			fenetre.goToVue();
		}
	}

	/** {@inheritDoc}  */
	@Override
	public void undo(ListeCommande listeCommande, Fenetre fenetre) {
		try {
			listeCommande.undo();
			fenetre.changeNotification(Textes.NOTIF_UNDO, CharteGraphique.NOTIFICATION_COULEUR);
			fenetre.goToVue();
		} catch (ExceptionPlanCo e) {
			// TODO Gérer exception
		}
	}

	/** {@inheritDoc}  */
	@Override
	public void redo(ListeCommande listeCommande, Fenetre fenetre) {
		try {
			listeCommande.redo();
			fenetre.changeNotification(Textes.NOTIF_REDO, CharteGraphique.NOTIFICATION_COULEUR);
			fenetre.goToVue();
		} catch (ExceptionPlanCo e) {
			// TODO Gérer exception
		}
	}

	/** {@inheritDoc}  */
	@Override
	public void exporterFeuilleDeRoute(Fenetre fenetre, Tournee tournee) {
		try {
			fenetre.annulerCreation();
			tournee.exportFeuilleDeRoute();
			fenetre.changeNotification(Textes.NOTIF_FDR_EXPORTEE, CharteGraphique.NOTIFICATION_COULEUR);

		} catch (IOException e) {
			fenetre.changeNotification(e.getMessage(), CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
		} catch (ExceptionPlanCo e) {
			fenetre.changeNotification(e.getMessage(), CharteGraphique.NOTIFICATION_INTERDIT_COULEUR);
		}
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherNotif(Fenetre fenetre) {
		fenetre.changeNotification(Textes.NOTIF_TOURNEE_CALCULE, CharteGraphique.NOTIFICATION_COULEUR);
	}

	/** {@inheritDoc}  */
	@Override
	public void afficherFenetre(Fenetre fenetre) {
		fenetre.setEtatCourant(fenetre.etatCalcule);
		fenetre.goToVue();
	}

	/** {@inheritDoc}  */
	@Override
	public void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande) {
		this.exporterFeuilleDeRoute(fenetre, plan.getTournee());
	}

	/** {@inheritDoc}  */
	@Override 
	public void annulerCreation(Fenetre fenetre) {
		fenetre.annulerCreation();
	}
}
