package controleur;

import java.util.Set;

import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

/**
 * Controleur
 * <br/><br/>
 * Authors : 									<br/>
 * romain.goutte-fangeas@insa-lyon.fr			<br/>
 *               ____							<br/>
 *           __--    --_						<br/>
 *          /   -        -						<br/>
 *         / /-- ------\  \						<br/>
 *        / /           \  |					<br/>
 *        | |           ?  |					<br/>
 *        | ? _--   -== \ /?					<br/>
 *         \| 'o > < o>  |||					<br/>
 *         \\    / \      )|					<br/>
 *          \\   .| )    |_/					<br/>
 *           |  :_____: :|						<br/>
 *            \  <==="  /|						<br/>
 *             \      .: /|\					<br/>
 *             )\_   .: / |:"--___				<br/>
 *         __-:|\ """ _-  |:::::::				<br/>
 *       _-::::\ "-_.-   /::::::::				<br/>
 *    _--:::::::| .|"-_  |::::::::				<br/>
 *  -"::::::::::\  | { -_|::::::::				<br/>
 * lucas.ouaniche-herbin@insa-lyon.fr			<br/>
 * lucas.marie@insa-lyon.fr						<br/>
 * clara.pourcel@insa-lyon.fr					<br/>
 * pierrick.chauvet@insa-lyon.fr				<br/>
 * bastien.guiraudou@insa-lyon.fr				<br/>
 * victor.bonin@insa-lyon.fr					<br/>
 * 
 *  
 * @author 4104
 */
public class Controleur {
	private Plan plan;
	private Tournee tournee;
	private ListeCommande listeCommande;
	private Etat etatCourant;
	private Fenetre fenetre;

	//Instances associees a chaque etat possible du controleur
	protected final EtatInit etatInit = new EtatInit();
	protected final EtatPlanOuvert etatPlanOuvert = new EtatPlanOuvert();
	protected final EtatDemandeOuverte etatDemandeOuverte = new EtatDemandeOuverte();
	protected final EtatCalculEnCours etatCalculEnCours = new EtatCalculEnCours();
	protected final EtatCalcule etatCalcule = new EtatCalcule();
	protected final EtatModifie etatModifie = new EtatModifie();

	/**
	 * Cree le controleur de l'app
	 * @param plan le plan
	 */
	public Controleur(Plan plan) {
		this.plan = plan;
		etatCourant = etatInit;
		listeCommande = new ListeCommande();
		fenetre = new Fenetre(this, plan);
	}

	/**
	 * Change l'etat courant du controleur
	 * @param etat le nouvel etat courant
	 */
	protected void setEtatCourant(Etat etat) {
		etatCourant = etat;
	}

	/**
	 * Methode appelee apres un clic sur le bouton "Importer un plan"
	 * ou "Changer de plan"
	 */
	public void ouvrirPlan() {
		etatCourant.ouvrirPlan(this, plan, fenetre, listeCommande);
	}

	/**
	 * Methode appelee apres un clic sur le bouton "Importer une demande de livraison"
	 * ou "Importer une nouvelle demande de livraison"
	 */
	public void ouvrirLivraison() {
		etatCourant.ouvrirLivraison(this, plan, fenetre, listeCommande);
	}


	/**
	 * Methode appelee apres un clic sur le bouton "Optimiser l'ordre des livraisons"
	 */
	public void calculerTournee() {
		etatCourant.calculerTournee(this, plan, fenetre);
	}

	/**
	 * Methode appelee apres cree avoir un point de livraison via la carte ou le bouton "+"
	 */
	public void ajouterLivraison(Livraison l, int position) {
		etatCourant.ajouterLivraison(fenetre, plan, l, listeCommande, position);
	}

	/**
	 * Methode appelee apres avoir permuter un point de livraison avec un autre
	 */
	public void permuterLivraison(Livraison livraison, int anciennePos, int nouvellePos) {
		etatCourant.deplacerLivraison(fenetre, plan, livraison, listeCommande, anciennePos, nouvellePos);
	}

	/**
	 * Methode appelee apres avoir supprimer un point de livraison
	 */
	public void supprimerLivraison(Livraison l, int position) {
		etatCourant.supprimerLivraison(fenetre, plan, l, listeCommande, position);
	}

	/**
	 * Methode appelee apres un clic sur le bouton "Calculer Itineraire"
	 */
	public void calculerItineraire() {
		etatCourant.calculerItineraire(this, plan, fenetre);
	}

	/**
	 * Methode appelee apres un clic sur le bouton "Exporter feuille de route"
	 */
	public void exporterFeuilleDeRoute() {
		this.tournee = plan.getTournee();
		etatCourant.exporterFeuilleDeRoute(fenetre, tournee);
	}

	/**
	 * Methode appelee apres un clic sur le bouton "Undo" ou apres presse Ctrl+Z
	 */
	public void undo() {
		etatCourant.undo(listeCommande, fenetre);
	}

	/**
	 * Methode appelee apres un clic sur le bouton "Redo" ou apres presse Ctrl+Y
	 */
	public void redo() {
		etatCourant.redo(listeCommande, fenetre);
	}
	
	/**
	 * Methode changeant l'etat de la fenetre
	 */
	public void afficherFenetre() {
		etatCourant.afficherFenetre(fenetre);
	}
	
	/**
	 * Methode permettant de modifier le contenu de la zone de notification
	 */
	public void afficherNotif() {
		etatCourant.afficherNotif(fenetre);
	}
	
	/**
	 * Methode appelee apres un clic droit -> Ajouter après sur l'un des éléments de la tournée
	 */
	public void creerLivraisonApres(int position) {
		etatCourant.creerLivraisonApres(fenetre, position);
	}
	
	/**
	 * Methode appelee apres un clic sur le bouton + ou apres presse Ctrl+N
	 */
	public void creerLivraison() {
		etatCourant.creerLivraison(fenetre);
	}
	
	/**
	 * Methode permettant de trouver l'inersection la plus proche de x et y en paramètres
	 */
	public void obtenirPlusProcheIntersection(double x, double y) {
		etatCourant.obtenirPlusProcheIntersection(fenetre, plan, x, y);
	}
	
	/**
	 * Methode permettant de commencer à choisir une intersection sur vuePlan
	 */
	public void commencerChoixIntersection() {
		etatCourant.commencerChoixIntersection(fenetre);
	}
	
	/**
	 * Methode permettant de prendre en compte un appui sur la touche entrer
	 */
	public void appuiEntree() {
		etatCourant.appuiEntree(this, plan, fenetre, listeCommande);
	}
	
	/**
	 * Methode annulant la creation d'une nouvelle livraison
	 */
	public void annulerCreation() {
		etatCourant.annulerCreation(fenetre);
	}

	/**
	 * Recupere les noms des troncons adjacents a une intersection
	 * @param inter l'intersection
	 * @return les troncons dans un objet Set<String>
	 */
	public Set<String> nomsTronconsIntersection(Intersection inter){
		Set<String> liste;
		liste = plan.nomsTronconVoisin(inter.getId());
		return liste;
	}
	
	public void nettoyerNouvelleLivraison() {
		fenetre.nettoyerNouvelleLivraison();
	}
	
}
