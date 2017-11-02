package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

public class TSP4 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		//return new IteratorDistHoraires(nonVus, sommetCrt, heureActuelle, cout, duree, horaires);
		return new IteratorDistSimple(nonVus, sommetCrt, cout);
	}
	
	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		int borneMinDuree = duree[sommetCourant];

		int minDebut = Integer.MAX_VALUE;
		int minAutre;
		/*
		if(sommetCourant<0||sommetCourant>=cout.length||sommetCourant>=duree.length||sommetCourant>=horaires.length)
			return Integer.MAX_VALUE;*/

		for(Integer courant : nonVus) {
			/*
			//Si l'horaire de fin est depassé, la solution n'est pas viable.
			if(horaires[courant][1]!=-1 && horaires[courant][1]<heureActuelle+cout[sommetCourant][courant]+duree[courant])
				return Integer.MAX_VALUE;
			
			//Sinon il faut prendre en compte l'attente avant le premier sommet
			int distanceTemps = Math.max(
					cout[sommetCourant][courant],
					horaires[courant][0] - heureActuelle // si horaire[][] = -1, on est negatif, donc le max le prend pas
					);
			if(distanceTemps<minDebut)
				minDebut = distanceTemps;
			*/
			//Et on calcule le min des distances entre les sommets restants
			minAutre = cout[courant][0];
			for(Integer suivant : nonVus) {
				if(courant!=suivant && cout[courant][suivant]<minAutre)
					minAutre = cout[courant][suivant];
			}
			borneMinDuree+=minAutre;
			borneMinDuree+=duree[courant];
		}
		//borneMinDuree+=minDebut;
		
		return borneMinDuree;
	}
}
