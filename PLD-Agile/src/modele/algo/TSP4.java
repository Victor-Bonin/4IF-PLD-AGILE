package modele.algo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import modele.PlageHoraire;

public class TSP4 extends TSP2 {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Calendar heureDebut, float[][] cout, int[] duree, PlageHoraire[] horaires) {
		return new IteratorDistHoraires(nonVus, sommetCrt, heureDebut, cout, horaires);
	}
	
	@Override
	protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, Calendar heureDebut, float[][] cout, int[] duree, PlageHoraire[] horaires) {
		float r = duree[sommetCourant];
		
		float min = Float.MAX_VALUE;
		/*
		if(sommetCourant<0||sommetCourant>=cout.length||sommetCourant>=duree.length||sommetCourant>=horaires.length)
			return Integer.MAX_VALUE;*/

		for(Integer courant : nonVus)
				if(cout[sommetCourant][courant]<min)
					min = cout[sommetCourant][courant];
				
		r+=min;
		
		
		for(Integer courant : nonVus) {
			min = cout[courant][0];
			for(Integer suivant : nonVus) {
				if(courant!=suivant && cout[courant][suivant]<min)
					min = cout[courant][suivant];
			}
			r+=min;
			r+=duree[courant];
		}
		return r;
	}
}
