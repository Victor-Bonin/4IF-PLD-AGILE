package modele.algo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import modele.PlageHoraire;

public class TSP1 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Calendar heureDebut, float[][] cout, int[] duree, PlageHoraire[] horaires) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, Calendar heureDebut, float[][] cout, int[] duree, PlageHoraire[] horaires) {
		return 0;
	}
}
