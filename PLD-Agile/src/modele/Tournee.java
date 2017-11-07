package modele;

import java.util.List;

/**
 * Ensemble ordonne de livraisons avec un point de depart et d’arrivee ainsi qu’un itineraire
 * @author 4104
 */
public class Tournee extends DemandeLivraison{
	private FeuilleDeRoute feuilleDeRoute;
	private Itineraire itineraire;

	public Tournee(Entrepot entrepot, List<Livraison> meilleureSolution, Itineraire iti){
		this.itineraire = iti;
		super.setEntrepot(entrepot);
		super.setLivraisons(meilleureSolution);
	}
	
	public Itineraire getItineraire(){
		return itineraire;
	}
	
	public boolean equals(Object obj) {
		return this.getItineraire().equals(((Tournee)obj).getItineraire());
	}
	
	public FeuilleDeRoute GetFeuilleDeRoute() {
		return feuilleDeRoute;
	}
}
