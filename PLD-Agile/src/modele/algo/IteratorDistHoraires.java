package modele.algo;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import modele.PlageHoraire;

public class IteratorDistHoraires implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * par distance croissante
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorDistHoraires(Collection<Integer> nonVus, int sommetCrt, Calendar heureDebut, float[][] cout, PlageHoraire[] horaires){
		this.candidats = new Integer[nonVus.size()];
		nbCandidats = 0;
		for (Integer s : nonVus){
			candidats[nbCandidats++] = s;
		}
		
		for(int i=0;i<candidats.length;i++) {
			float max = cout[sommetCrt][candidats[i]];
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
