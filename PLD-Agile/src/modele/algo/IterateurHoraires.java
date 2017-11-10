package modele.algo;

import java.util.Collection;
import java.util.Iterator;

/**
 * <pre>
 * 
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
public class IterateurHoraires implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * par distance croissante en considérant les horaires de début
	 * l'attribut les stock dans l'ordre décroissant
	 * @param nonVus la liste des sommets qui n'ont pas encore ete visites
	 * @param sommetCrt le dernier sommet visite
	 * @param heureActuelle heure a laquelle on part du sommetCrt
	 * @param couts : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param durees : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @param horaires : horaires[i][k] = horaire du créneau pour le point i, k=0 pour horaireDébut, k=1 pour horaire fin
	 */	
	public IterateurHoraires(Collection<Integer> nonVus, int sommetCrt, int heureActuelle, int[][] couts, int[] durees, int[][] horaires){
		this.candidats = new Integer[nonVus.size()];
		nbCandidats = 0;
		for (Integer s : nonVus){
			candidats[nbCandidats++] = s;
		}
		
		for(int i=0;i<candidats.length;i++) {
			int max = Math.max(
					couts[sommetCrt][candidats[i]],
					horaires[candidats[i]][0] - heureActuelle );
			int maxIndex = i;
			for(int j=i+1;j<candidats.length;j++) {
				int distanceTemps = Math.max(
						couts[sommetCrt][candidats[j]],
						horaires[candidats[j]][0] - heureActuelle // si horaire[][] = -1, on est negatif, donc le max le prend pas
						);
				if(distanceTemps>max) {
					maxIndex = j;
					max = distanceTemps;
				}
			}
			Integer temp = candidats[i];

			candidats[i] = candidats[maxIndex];
			candidats[maxIndex] = temp;
		}
	}
	
	@Override
	public boolean hasNext() {
		return nbCandidats > 0;
	}

	@Override
	public Integer next() {
		return candidats[--nbCandidats];
	}

	@Override
	public void remove() {}

}
