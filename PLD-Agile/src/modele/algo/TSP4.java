package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

public class TSP4 extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureActuelle, int[][] couts,
			int[] durees, int[][] horaires) {
		return new IterateurHoraires(nonVus, sommetCrt, heureActuelle, couts, durees, horaires);
	}

	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureActuelle, int[][] couts,
			int[] durees, int[][] horaires) {
		int borneMinDuree = 0;

		int minActuelPremierNonVu = Integer.MAX_VALUE;

		// if(sommetCourant<0||sommetCourant>=cout.length||sommetCourant>=duree.length||sommetCourant>=horaires.length)
		// return Integer.MAX_VALUE;

		for (Integer premierNonVu : nonVus) {
			// Si l'horaire de fin est depassé, la solution n'est pas viable.
			if (horaires[premierNonVu][1] != -1 && horaires[premierNonVu][1] < heureActuelle
					+ couts[sommetCourant][premierNonVu] + durees[premierNonVu])
				return Integer.MAX_VALUE;

			// Sinon il faut prendre en compte l'attente avant le premier sommet
			int distanceActuelPremierNonVu = couts[sommetCourant][premierNonVu];
			if (horaires[premierNonVu][0] != -1)
				distanceActuelPremierNonVu = Math.max(couts[sommetCourant][premierNonVu],
						horaires[premierNonVu][0] - heureActuelle // si horaire[][] = -1, on est negatif, donc le max le
																	// prend pas
				);

			if (distanceActuelPremierNonVu < minActuelPremierNonVu)
				minActuelPremierNonVu = distanceActuelPremierNonVu;
		}
		borneMinDuree += minActuelPremierNonVu;

		// Et on calcule le min des distances entre les sommets restants
		for (Integer premiecandidatSuite : nonVus) {
			int minAutres = couts[premiecandidatSuite][0];
			for (Integer suivant : nonVus) {
				if (!premiecandidatSuite.equals(suivant) && couts[premiecandidatSuite][suivant] < minAutres)
					minAutres = couts[premiecandidatSuite][suivant];
			}
			borneMinDuree += minAutres;
			borneMinDuree += durees[premiecandidatSuite];
		}

		return borneMinDuree;
	}
}
