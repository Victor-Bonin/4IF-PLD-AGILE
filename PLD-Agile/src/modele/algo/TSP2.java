package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

import modele.PlageHoraire;

public class TSP2 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, float[][] cout, int[] duree, PlageHoraire[] horaires) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, float[][] cout, int[] duree, PlageHoraire[] horaires) {
		int r = duree[sommetCourant];
		
		float min = Float.MAX_VALUE;
		if(sommetCourant<0||sommetCourant>=cout.length)
			return 0;
		for(int i=0;i<cout[sommetCourant].length;i++)
			if(cout[sommetCourant][i]<min)
				min = cout[sommetCourant][i];
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
