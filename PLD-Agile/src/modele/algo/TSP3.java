package modele.algo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import modele.PlageHoraire;

public class TSP3 extends TSP2 {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Calendar heureDebut, float[][] cout, int[] duree, PlageHoraire[] horaires) {
		return new IteratorDistSimple(nonVus, sommetCrt, cout);
	}
}
