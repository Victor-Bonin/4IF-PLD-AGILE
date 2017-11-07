package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @deprecated
 */
public class TSP1 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		return 0;
	}
}
