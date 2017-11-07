package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

public class TSP3 extends TSP2 {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		return new IteratorDistSimple(nonVus, sommetCrt, cout);
	}
}
