package modele.algo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import modele.PlageHoraire;

public class TSP4 extends TSP2 {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureDebut, float[][] cout, int[] duree, int[][] horaires) {
		return new IteratorDistHoraires(nonVus, sommetCrt, heureDebut, cout, horaires);
	}
	
	@Override
	protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureDebut, float[][] cout, int[] duree, int[][] horaires) {
		float r = duree[sommetCourant];

		float minDebut = Float.MAX_VALUE;
		float minAutre;
		/*
		if(sommetCourant<0||sommetCourant>=cout.length||sommetCourant>=duree.length||sommetCourant>=horaires.length)
			return Integer.MAX_VALUE;*/

		for(Integer courant : nonVus) {
			if(horaires[courant]!=null) {			
			}
			
			if(cout[sommetCourant][courant]<minDebut)
				minDebut = cout[sommetCourant][courant];
			minAutre = cout[courant][0];
			
			for(Integer suivant : nonVus) {
				if(courant!=suivant && cout[courant][suivant]<minAutre)
					minAutre = cout[courant][suivant];
			}
			r+=minAutre;
			r+=duree[courant];
		}
		r+=minDebut;
		
		return r;
	}
}
