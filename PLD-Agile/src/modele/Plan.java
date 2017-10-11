package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Plan extends Observable {
	private Rectangle r; // Rectangle definissant les contours du plan
	private ArrayList<Forme> formes; // liste des formes positionnees sur le plan
	
	public Plan(int largeur, int hauteur){
		PointFactory.initPointFactory(largeur, hauteur);
		r = new Rectangle(PointFactory.creePoint(0,0), hauteur, hauteur);
		formes = new ArrayList<Forme>();
	}
	
	/**
	 * Ajout d'une forme f au plan
	 * precondition : f est entierement contenue dans this
	 * @param f
	 */
	public void ajoute(Forme f){
		formes.add(f);
		setChanged();
		notifyObservers(f);
	}
	
	/**
	 * Suppression de la forme f du plan
	 * precondition : f est une forme contenue dans this
	 * @param f
	 */
	public void supprime(Forme f) {
		formes.remove(f);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Recherche d'une forme f contenant le point p
	 * @param p
	 * @return null si aucune forme ne contient p ; une forme f contenant p sinon
	 */
	public Forme cherche(Point p){
		for (Forme f : formes){
			if (f.contient(p)) return f;
		}
		return null;
	}

	
	/**
	 * @param f
	 * @return true si la forme f est entierement contenue dans this
	 */
	public boolean contient(Forme f){
		return f.contenuDans(r);
	}

	public int getLargeur() {
		return r.getLargeur();
	}
	
	public int getHauteur() {
		return r.getHauteur();
	}
	
	public Iterator<Forme> getIterateurFormes(){
		return formes.iterator();
	}

	/**
	 * Determine si toutes les formes du plan sont disjointes de f1
	 * @param f1
	 * @return false si this contient une forme f2 differente de f1 telle que f1 et f2 ne sont pas disjointes
	 */
	public boolean tousDisjoints(Forme f1) {
		for (Forme f2 : formes){
			if ((f2 != f1) && (!f2.disjoint(f1)))
				return false;
		}
		return true;
	}

	/**
	 * Re-initialise le plan : supprime les formes du plan courant et met a jour la largeur et la hauteur
	 * @param largeur
	 * @param hauteur
	 */
	public void reset(int largeur, int hauteur) {
		Iterator<Forme> it = formes.iterator();
		while (it.hasNext()){
			it.next();
			it.remove();
		}
		setChanged();
		notifyObservers();	
		PointFactory.initPointFactory(largeur, hauteur);
		r = new Rectangle(PointFactory.creePoint(0,0), largeur, hauteur);
	}

}
