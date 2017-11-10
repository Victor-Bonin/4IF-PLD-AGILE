package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Ensemble ordonne des chemins empruntes par une tournee.
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

	/**
	 * Constructeur de l'itineraire.
	 * @param pCourtsChemins la matrice des plus courts chemins
	 * @param meilleureSolution le tableau contenant le cout des meilleurs solutions
	 */
	public Itineraire(Chemin[][] pCourtsChemins, Integer[] meilleureSolution) {
		itineraire = new ArrayList<Chemin>();
		for (int i = 0; i < meilleureSolution.length-1; i++) {
			itineraire.add(pCourtsChemins[meilleureSolution[i]][meilleureSolution[i+1]]);
		}
		itineraire.add(pCourtsChemins[meilleureSolution[meilleureSolution.length-1]][meilleureSolution[0]]);
	}

	/**
	 * Construit un itineraire a partir d'une liste de chemin
	 * @param itineraire la liste de chemin
	 */
	public Itineraire(List<Chemin> itineraire) {
		this.itineraire = new ArrayList<Chemin>(itineraire);
	}

	/**
	 * Retourne la liste de chemin de l'itineraire.
	 * @return la liste de chemin de l'itineraire
	 */
	public List<Chemin> getChemins() {
		return itineraire;
	}

	/**
	 * Compare tous les chemins de l'itineraire.
	 * @param obj l'itineraire a comparer
	 * @return true si tous les chemins sont égaux à ceux de l'autre itineraire
	 */
	public boolean equals(Object obj) {
		Itineraire itineraire = (Itineraire)obj;
		boolean estEgal = true;
		for(int i = 0; i < this.itineraire.size(); i++) {
			estEgal = estEgal && this.itineraire.get(i).equals(itineraire.getChemins().get(i));
		}

		return estEgal;
	}

}
