package modele.algo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import modele.PlageHoraire;

public class TSP1 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureDebut, float[][] cout, int[] duree, int[][] horaires) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureDebut, float[][] cout, int[] duree, int[][] horaires) {
		return 0;
	}
}
