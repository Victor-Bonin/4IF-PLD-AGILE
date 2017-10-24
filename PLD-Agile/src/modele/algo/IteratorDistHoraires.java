package modele.algo;

import java.util.Arrays;
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
	public IteratorDistHoraires(Collection<Integer> nonVus, int sommetCrt){
		this.candidats = new Integer[nonVus.size()];
		nbCandidats = 0;
		for (Integer s : nonVus){
			candidats[nbCandidats++] = s;
		}
		Arrays.sort(candidats);
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
