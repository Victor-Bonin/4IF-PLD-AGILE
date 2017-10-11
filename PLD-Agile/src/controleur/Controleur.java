 package controleur;

import modele.Plan;
import modele.Point;
import vue.Fenetre;

public class Controleur{
	
	private Plan plan;
	private Fenetre fenetre;
	private ListeDeCdes listeDeCdes;
	private Etat etatCourant;
	// Instances associees a chaque etat possible du controleur
	protected final EtatInit etatInit = new EtatInit();
	protected final EtatDeplacer1 etatDeplacer1 = new EtatDeplacer1();
	protected final EtatDeplacer2 etatDeplacer2 = new EtatDeplacer2();
	protected final EtatRectangle1 etatRectangle1 = new EtatRectangle1();
	protected final EtatRectangle2 etatRectangle2 = new EtatRectangle2();
	protected final EtatCercle1 etatCercle1 = new EtatCercle1();
	protected final EtatCercle2 etatCercle2 = new EtatCercle2();
	protected final EtatSupprimer etatSupprimer = new EtatSupprimer();

	/**
	 * Cree le controleur de l'application
	 * @param plan le plan
	 * @param echelle l'echelle de la vue graphique de p
	 */
	public Controleur(Plan plan, int echelle) {
		this.plan = plan;
		listeDeCdes = new ListeDeCdes();
		etatCourant = etatInit;
		fenetre = new Fenetre(plan, echelle, this);
	}
	
	/**
	 * Change l'etat courant du controleur
	 * @param etat le nouvel etat courant
	 */
	protected void setEtatCourant(Etat etat){
		etatCourant = etat;
	}

	// Methodes correspondant aux evenements utilisateur
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Ajouter un cercle"
	 */
	public void ajouterCercle() {
		etatCourant.ajouterCercle(this, fenetre);
	}

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Ajouter un rectangle"
	 */
	public void ajouterRectangle() {
		etatCourant.ajouterRectangle(this, fenetre);
	}

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Supprimer des formes"
	 */
	public void supprimer() {
		etatCourant.supprimer(this, fenetre);
	}

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Deplacer une forme"
	 */
	public void deplacer() {
		etatCourant.deplacer(this, fenetre);
	}
	
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Diminuer echelle"
	 */
	public void diminuerEchelle(){
		etatCourant.diminuerEchelle( fenetre);
	}

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Augmenter echelle"
	 */
	public void augmenterEchelle(){
		etatCourant.augmenterEchelle(fenetre);
	}
	
	/**
	 * Methode appelee par la fenetre quand l'utilisateur clique sur le bouton "Undo"
	 */
	public void undo(){
		etatCourant.undo(listeDeCdes);
	}

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Redo"
	 */
	public void redo(){
		etatCourant.redo(listeDeCdes);
	}
	
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Sauver le plan"
	 */
	public void sauver() {
		etatCourant.sauver(plan, fenetre);
	}

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Ouvrir un plan"
	 */
	public void ouvrir() {
		etatCourant.ouvrir(plan, listeDeCdes, fenetre);
	}

	/**
	 * Methode appelee par fenetre apres un clic gauche sur un point de la vue graphique
	 * Precondition : p != null
	 * @param p = coordonnees du plan correspondant au point clique
	 */
	public void clicGauche(Point p) {
		etatCourant.clicGauche(this, fenetre,plan,listeDeCdes,p);
	}
	/**
	 * Methode appelee par fenetre apres un clic droit
	 */
	public void clicDroit(){
		etatCourant.clicDroit(this, fenetre, listeDeCdes);
	}

	/**
	 * Methode appelee par fenetre apres un deplacement de la souris sur la vue graphique du plan
	 * Precondition : p != null
	 * @param p = coordonnees du plan correspondant a la position de la souris
	 */
	public void sourisBougee(Point p) {
		etatCourant.sourisBougee(plan, p);
	}

	/**
	 * Methode appelee par fenetre apres la saisie d'un caractere au clavier
	 * @param codeCar le code ASCII du caractere saisi
	 */
	public void caractereSaisi(int codeCar) {
		etatCourant.carSaisi(plan, listeDeCdes, codeCar);
	}
}
