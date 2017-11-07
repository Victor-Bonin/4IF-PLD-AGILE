package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

public class TSP2 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		int r = duree[sommetCourant];
		
		int minDebut = Integer.MAX_VALUE;
		int minAutre ;
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
