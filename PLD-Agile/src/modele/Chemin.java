package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Ensemble des tronçons reliant une intersection depart a une intersection arrivee
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
 * @author 4104
 */
public class Chemin {

	private List<Troncon> troncons;
	private Intersection depart;
	private Intersection arrivee;

	/**
	 * Constructeur de chemin a partir de deux intersections, une de depart une d'arrivee.
	 * @param depart l'intersection de depart
	 * @param arrivee l'intersection d'arrivee
	 */
	public Chemin(Intersection depart, Intersection arrivee) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.troncons = new ArrayList<Troncon>();
	}

	/**
	 * Ajoute un troncon au chemin a la position indiquee.
	 * @param index la position voulue
	 * @param troncon le troncon ajoute
	 */
	public void addTroncon (int index, Troncon troncon) {
		this.troncons.add(index, troncon);
	}

	/**
	 * Retourne la liste des troncons du chemin.
	 * @return la liste des troncons du chemin
	 */
	public List<Troncon> getTroncons() {
		return troncons;
	}

	/**
	 * Retourne le depart du chemin.
	 * @return le depart du chemin
	 */
	public Intersection getDepart() {
		return depart;
	}

	/**
	 * Retourne l'arrivee du chemin.
	 * @return l'arrivee du chemin
	 */
	public Intersection getArrivee() {
		return arrivee;
	}

	/**
	 * Retourne true si les chemins sont égaux, false sinon.
	 * @param chemin Chemin
	 * @return true si les chemins sont égaux, false sinon
	 */
	public boolean equals(Chemin chemin) {
		boolean estEgal = true;
		for(int i = 0; i < this.troncons.size(); i++) {
			estEgal = estEgal && this.troncons.get(i).equals(chemin.getTroncons().get(i));
		}
		return estEgal;
	}
}
