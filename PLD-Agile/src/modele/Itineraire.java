package modele;

import java.util.List;

/**
 * Ensemble ordonne des chemins empruntes par une tournee
 * @author Victor du Vercors, vos daronnes les roqueforts, qui rockent fort
 */
public class Itineraire {
	private List<Chemin> itineraire;
	
	public Itineraire(Chemin[][] pCourtsChemins, Integer[] meilleureSolution) {
		
		for (int i = 0; i < meilleureSolution.length-1; i++){
			itineraire.add(pCourtsChemins[meilleureSolution[i]][meilleureSolution[i+1]]);
		}
		itineraire.add(pCourtsChemins[meilleureSolution[meilleureSolution.length]][meilleureSolution[0]]);
	}
}
