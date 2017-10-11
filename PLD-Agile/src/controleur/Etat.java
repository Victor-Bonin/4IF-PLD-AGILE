package controleur;

import modele.Plan;
import modele.Point;
import vue.Fenetre;

public interface Etat {
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Ajouter un cercle"
	 * @param controleur 
	 * @param fenetre
	 */
	public void ajouterCercle(Controleur controleur, Fenetre fenetre);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Ajouter un rectangle"
	 * @param controleur 
	 * @param fenetre
	 */
	public void ajouterRectangle(Controleur controleur, Fenetre fenetre);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Supprimer des formes"
	 * @param controleur 
	 * @param fenetre
	 */
	public void supprimer(Controleur controleur, Fenetre fenetre);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Deplacer une forme"
	 * @param controleur 
	 * @param fenetre
	 */
	public void deplacer(Controleur controleur, Fenetre fenetre);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Diminuer echelle"
	 * @param fenetre
	 */
	public void diminuerEchelle(Fenetre fenetre);
	
	/**
	 * Methode appelee par le controleur quand l'utilisateur clique sur le bouton "Augmenter echelle"
	 * @param fenetre
	 */
	public void augmenterEchelle(Fenetre fenetre);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Undo"
	 * @param listeDeCdes
	 */
	public void undo(ListeDeCdes listeDeCdes);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Redo"
	 * @param listeDeCdes
	 */
	public void redo(ListeDeCdes listeDeCdes);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Sauver le plan"
	 * @param plan
	 * @param fenetre
	 */
	public void sauver(Plan plan, Fenetre fenetre);
	
	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Ouvrir un plan"
	 * @param plan
	 * @param listeDeCdes
	 * @param fenetre
	 */
	public void ouvrir(Plan plan, ListeDeCdes listeDeCdes, Fenetre fenetre);

	/**
	 * Methode appelee par controleur apres un deplacement de la souris sur la vue graphique du plan
	 * Precondition : p != null
	 * @param plan
	 * @param p = coordonnees du plan correspondant a la position de la souris
	 */
	public void sourisBougee(Plan plan, Point p);
	
	/**
	 * Methode appelee par controleur apres la saisie d'un caractere au clavier
	 * @param plan
	 * @param listeDeCdes
	 * @param codeCar le code ASCII du caractere saisi
	 */
	public void carSaisi(Plan plan, ListeDeCdes listeDeCdes, int codeCar);
	
	/**
	 * Methode appelee par controleur apres un clic droit
	 * @param controleur 
	 * @param fenetre
	 * @param listeDeCdes
	 */
	public void clicDroit(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes);
	
	/**
	 * Methode appelee par controleur apres un clic gauche sur un point de la vue graphique
	 * Precondition : p != null
	 * @param controleur 
	 * @param fenetre
	 * @param plan
	 * @param listeDeCdes
	 * @param p = coordonnees du plan correspondant au point clique
	 */
	public void clicGauche(Controleur controleur, Fenetre fenetre, Plan plan, ListeDeCdes listeDeCdes, Point p);
}
