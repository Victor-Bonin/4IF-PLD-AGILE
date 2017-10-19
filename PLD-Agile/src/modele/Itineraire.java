package modele;

import java.util.List;

/**
 * Ensemble ordonne des chemins empruntes par une tournee
 * @author Victor du Vercors, vos daronnes les roqueforts, qui rockent fort
 */
public class Itineraire {
	private List<Chemin> itineraire;
	
	public Itineraire(List<Intersection> meilleureSolution, Chemin[][] pCourtsChemins) {
		Chemin[][] plusCourtsChemins = pCourtsChemins;
		List<Intersection> ordreDePassage = meilleureSolution;
		// TODO : test les extremites
		for (int i = 0; i < ordreDePassage.size(); i++){
			Intersection depart = ordreDePassage.get(i);
			Intersection arrivee = ordreDePassage.get(i+1);
			itineraire.add(plusCourtsChemins[depart.getId()][arrivee.getId()]);
		}
	}
}
