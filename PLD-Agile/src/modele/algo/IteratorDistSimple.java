package modele.algo;

import java.util.Collection;
import java.util.Iterator;

/**
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
 * @deprecated
 */
public class IteratorDistSimple implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * par distance croissante (next lit de droite à gauche).
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorDistSimple(Collection<Integer> nonVus, int sommetCrt, int[][] cout){
		this.candidats = new Integer[nonVus.size()];
		nbCandidats = 0;
		for (Integer s : nonVus){
			candidats[nbCandidats++] = s;
		}
		
		for(int i=0;i<candidats.length;i++) {
			int max = cout[sommetCrt][candidats[i]];
			int maxIndex = i;
			for(int j=i+1;j<candidats.length;j++) {
				if(cout[sommetCrt][candidats[j]]>max) {
					maxIndex = j;
					max = cout[sommetCrt][candidats[j]];
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
