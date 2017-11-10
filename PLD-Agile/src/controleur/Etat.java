package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

/**
 * <pre>
 * Interface listant les etats possibles pour le controleur
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
public interface Etat {
	/** 
	 * Methode appelee apres un clic sur le bouton "Importer un plan" ou "Changer de plan"
	 * @param controleur le controleur liee a notre instance de PlanCo
	 * @param plan le plan a completer
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param listeCommande liste des commandes executee, utile pour le pattern commande permettant les annulations
	 */
	void ouvrirPlan(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande);
	/**
	 * Methode appelee apres un clic sur le bouton "Importer une demande de
	 * livraison" ou "Importer une nouvelle demande de livraison"
	 * @param controleur le controleur liee a notre instance de PlanCo
	 * @param plan le plan a completer
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param listeCommande liste des commandes executee, utile pour le pattern commande permettant les annulations
	 */
	void ouvrirLivraison(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande);
	/**
	 * 
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param plan le plan dans lequel ajouter la livraison
	 * @param livraison livraison a ajouter
	 * @param listeCmd liste des commandes executee, utile pour le pattern commande permettant les annulaions
	 * @param position position a laquelle ajouter la livraison
	 */
	void ajouterLivraison(Fenetre fenetre, Plan plan, Livraison livraison, ListeCommande listeCmd, int position);
	/**
	 * Deplace une livraison parmi la liste des livraison d'une tournee d'un plan
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param plan plan contenant la tournee que l'on souhaite modifier
	 * @param livraison livraison a deplacer
	 * @param listeCmd liste des commandes executee, utile pour le pattern commande permettant les annulations
	 * @param anciennePos position actuelle de la livraison a deplacer
	 * @param predecesseurCible index de l'element survole, donc le predecesseur : -1 pour l'entrepot
	 */
	void deplacerLivraison(Fenetre fenetre, Plan plan, Livraison livraison, ListeCommande listeCmd, int anciennePos, 
			int predecesseurCible);
	/**
	 * Supprime une livraison d'une tournee
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param plan plan contenant la tournee que l'on souhaite modifier
	 * @param livraison livraison a supprimer
	 * @param listeCmd liste des commandes executee, utile pour le pattern commande permettant les annulations
	 * @param position position actuelle de la livraison a supprimer
	 */
	void supprimerLivraison(Fenetre fenetre, Plan plan, Livraison livraison, ListeCommande listeCmd, int position);
	/**
	 * Lance le calcul d'une tournee depuis une demande de livraison.
	 * Cela modifie l'objet Tournee contenu dans le plan, peut réordonner !
	 * @param controleur le controleur liee a notre instance de PlanCo
	 * @param plan le plan a completer
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void calculerTournee(Controleur controleur, Plan plan, Fenetre fenetre);
	/**
	 * Annule la derniere action executee
	 * @param listeCmd liste des commandes executee, utile pour le pattern commande permettant les annulations
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void undo(ListeCommande listeCommande, Fenetre fenetre);
	/**
	 * Re-execute la derniere action annulee
	 * @param listeCmd liste des commandes executee, utile pour le pattern commande permettant les annulations
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void redo(ListeCommande listeCommande, Fenetre fenetre);
	/**
	 * Methode appelee pour que la vue corresponde a l'etat courant
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void afficherFenetre(Fenetre fenetre);
	/**
	 * Methode appelee pour que la notification corresponde a l'etat courant
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void afficherNotif(Fenetre fenetre);
	/**
	 * Lance le calcul d'un itineraire depuis une tournée. Ne reordonne pas la tournee, mais complete les informations
	 * telles que les chemins entre chaque livraison
	 * @param controleur le controleur liee a notre instance de PlanCo
	 * @param plan le plan a completer
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void calculerItineraire(Controleur controleur, Plan plan, Fenetre fenetre);
	/**
	 * Debute l'entree de donnees pour creer une nouvelle livraison
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void creerLivraison(Fenetre fenetre);
	/**
	 * Debute l'entree de donnees pour creer une nouvelle livraison a une position precise
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param position position a laquelle on ajoutera la livraison apres validation
	 */
	void creerLivraisonApres(Fenetre fenetre, int position);
	/**
	 * Methode permettant de trouver l'inersection la plus proche de x et y en
	 * parametres
	 * @param x position x du clic
	 * @param y position y du clic
	 * @param plan le plan a completer
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void obtenirPlusProcheIntersection(Fenetre fenetre, Plan plan, double x, double y);
	/**
	 * Debute l'entree de donnees pour determiner l'adresse d'une livraison
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void commencerChoixIntersection(Fenetre fenetre);
	/**
	 * Methode appellee lors de l'appuie sur la touche entree, doit etre override par chacun des etats.
	 * @param controleur le controleur liee a notre instance de PlanCo
	 * @param plan le plan a completer
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param listeCommande liste des commandes executee, utile pour le pattern commande permettant les annulations
	 */
	void appuiEntree(Controleur controleur, Plan plan, Fenetre fenetre, ListeCommande listeCommande);
	/**
	 * Exporte une feuille de route decrivant une tournee
	 * @param fenetre la fenetre de notre instance de PlanCo
	 * @param tournee la tournee qui sera decrite dans la feuille de route
	 */
	void exporterFeuilleDeRoute(Fenetre fenetre, Tournee tournee);
	/**
	 * Methode appelee lors d'une annulation
	 * @param fenetre la fenetre de notre instance de PlanCo
	 */
	void annulerCreation(Fenetre fenetre);
}
