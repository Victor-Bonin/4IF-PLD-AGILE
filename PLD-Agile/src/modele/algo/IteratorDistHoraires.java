package modele.algo;

import java.util.Collection;
import java.util.Iterator;

public class IteratorDistHoraires implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * par distance croissante
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorDistHoraires(Collection<Integer> nonVus, int sommetCrt, int heureActuelle, int[][] cout, int[] duree, int[][] horaires){
		this.candidats = new Integer[nonVus.size()];
		nbCandidats = 0;
		for (Integer s : nonVus){
			candidats[nbCandidats++] = s;
		}
		
		for(int i=0;i<candidats.length;i++) {
			int max = cout[sommetCrt][candidats[i]];
			int maxIndex = i;
			for(int j=i+1;j<candidats.length;j++) {
				int distanceTemps = Math.max(
						cout[sommetCrt][candidats[j]],
						horaires[j][0] - heureActuelle // si horaire[][] = -1, on est negatif, donc le max le prend pas
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
