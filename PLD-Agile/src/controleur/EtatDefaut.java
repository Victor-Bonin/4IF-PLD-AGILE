package controleur;


import vue.Fenetre;
import modele.Plan;
import modele.Point;

public abstract class EtatDefaut implements Etat{	
	// Definition des comportements par defaut des methodes
	public void ajouterCercle(Controleur controleur, Fenetre fenetre){}
	public void ajouterRectangle(Controleur controleur, Fenetre fenetre){}
	public void supprimer(Controleur controleur, Fenetre fenetre){}
	public void deplacer(Controleur controleur, Fenetre fenetre){}
	public void diminuerEchelle(Fenetre fenetre) {}
	public void augmenterEchelle(Fenetre fenetre) {}
	public void undo(ListeDeCdes listeDeCdes){}
	public void redo(ListeDeCdes listeDeCdes) {}
	public void clicGauche(Controleur controleur, Fenetre fenetre, Plan plan, ListeDeCdes listeDeCdes, Point p) {}
	public void sourisBougee(Plan plan, Point p){}
	public void carSaisi(Plan plan, ListeDeCdes listeDeCdes, int codeCar) {}
	public void clicDroit(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes) {
		fenetre.autoriseBoutons(true);
		controleur.setEtatCourant(controleur.etatInit);
		fenetre.afficheMessage("");
	}
	public void sauver(Plan plan, Fenetre fenetre){};
	public void ouvrir(Plan plan, ListeDeCdes listeDeCdes, Fenetre fenetre){}
}
