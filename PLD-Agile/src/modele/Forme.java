package modele;

import java.util.Observable;

public abstract class Forme extends Observable {
	
	private boolean estSelectionne;
	
	public boolean getEstSelectionne(){
		return estSelectionne;
	}
	
	public void setEstSelectionne(boolean b) {
		estSelectionne = b;
		setChanged();
		notifyObservers(this);
	}
	
	public Forme(){
		estSelectionne = false;
	}
	
	/**
	 * Determine si le point p est contenu dans this
	 * @param p 
	 * @return true si p appartient a this, false sinon
	 */
	public abstract boolean contient(Point p);

	/**
	 * Determine s'il est possible de deplacer this de (deltaX,deltaY) de telle sorte qu'il soit toujours
	 * entierement inclus dans plan, et qu'il soit disjoint de toutes les formes de plan
	 * @param deltaX
	 * @param deltaY
	 * @param plan
	 */
	public abstract boolean deplacementPossible(int deltaX, int deltaY, Plan plan);

	/**
	 * Deplace this de (deltaX,deltaY)
	 * Precondition : deplacementPossible(deltaX, deltaY, plan) == true
	 * @param deltaX
	 * @param deltaY
	 */
	public void deplace(int deltaX, int deltaY){
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Determine si this est entierement contenue dans le rectangle r
	 * @param r
	 * @return true si this est entierement contenue dans le rectangle r
	 */
	public abstract boolean contenuDans(Rectangle r);
	
	/**
	 * Determine si l'intersection entre this et f est vide
	 * @param f
	 * @return true si l'intersection entre this et f est vide
	 */
	public abstract boolean disjoint(Forme f);
	
	/**
	 * Appelle la methode affiche de v sur this (design pattern visiteur)
	 * @param v
	 */
	public abstract void affiche(VisiteurPourAfficherFormes v);
}
