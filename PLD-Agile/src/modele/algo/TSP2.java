package modele.algo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import modele.PlageHoraire;

public class TSP2 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Long heureDebut, float[][] cout, int[] duree, Long[][] horaires) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, Long heureDebut, float[][] cout, int[] duree, Long[][] horaires) {
		float r = duree[sommetCourant];
		
		float minDebut = Float.MAX_VALUE;
		float minAutre ;
		/*
		if(sommetCourant<0||sommetCourant>=cout.length)
			return 0;
		 */
		for(Integer courant : nonVus) {
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
