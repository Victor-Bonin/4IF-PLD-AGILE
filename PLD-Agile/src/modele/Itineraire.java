package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Ensemble ordonne des chemins empruntes par une tournee
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
public class Itineraire {
	private List<Chemin> itineraire;

	public Itineraire(Chemin[][] pCourtsChemins, Integer[] meilleureSolution) {
		itineraire = new ArrayList<Chemin>();
		for (int i = 0; i < meilleureSolution.length-1; i++){
			itineraire.add(pCourtsChemins[meilleureSolution[i]][meilleureSolution[i+1]]);
		}
		itineraire.add(pCourtsChemins[meilleureSolution[meilleureSolution.length-1]][meilleureSolution[0]]);
	}

	public Itineraire(List<Chemin> itineraire) {
		this.itineraire = new ArrayList<Chemin>(itineraire);
	}

	public List<Chemin> getChemins() {
		return itineraire;
	}

	public boolean equals(Itineraire itineraire) {
		if (!((this.itineraire.size()) == (itineraire.getChemins().size()))) {
			return false;
		}
		for(int i = 0; i<this.itineraire.size(); i++) {
			if (!this.itineraire.get(i).equals(itineraire.getChemins().get(i))) {
				return false;
			}
		}
		return true;
	}
}
