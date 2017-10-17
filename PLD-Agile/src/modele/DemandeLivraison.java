package modele;

import java.util.ArrayList;
import java.util.List;

public class DemandeLivraison {
	private Entrepot entrepot;
	private List<Livraison> livraisons;

	public DemandeLivraison() {
		livraisons = new ArrayList<Livraison>();
	}

	public void ajoutePointLivraison(Livraison lvrsn) {
		livraisons.add(lvrsn);
	}

	public void setEntrepot(Entrepot entrpt){
		entrepot = entrpt;
	}
}
