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
	
	public List<Chemin> getItineraire(){
		return itineraire.getChemins();
	}
	
	public FeuilleDeRoute GetFeuilleDeRoute() {
		return feuilleDeRoute;
	}
}
