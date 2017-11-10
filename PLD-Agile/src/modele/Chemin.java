package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Ensemble des tronçons reliant une intersection depart a une intersection arrivee
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
 * 
 *  
 * @author 4104
 */
public class Chemin {

	private List<Troncon> troncons;
	private Intersection depart;
	private Intersection arrivee;

	public Chemin(Intersection depart, Intersection arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
		this.troncons = new ArrayList<Troncon>();
	}

	public void addTroncon (int index, Troncon troncon){
		this.troncons.add(index, troncon);;
	}

	public List<Troncon> getTroncons(){
		return troncons;
	}

	public Intersection getDepart() {
		return depart;
	}

	public Intersection getArrivee() {
		return arrivee;
	}

	public boolean equals(Chemin chemin) {
		if (!((this.getTroncons().size()) == (chemin.getTroncons().size()))) {
			return false;
		}
		for(int i =0; i<this.troncons.size(); i++) {
			if (!this.troncons.get(i).equals(chemin.getTroncons().get(i))) {
				return false;
			}
		}
		return true;
	}
}

