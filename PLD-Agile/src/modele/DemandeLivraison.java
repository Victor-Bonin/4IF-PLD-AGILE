package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Demande de livraison entre un entrepot et des livraisons (non ordonnées)
 * @author 4104
 *
 */
public class DemandeLivraison {
	private Entrepot entrepot;
	private List<Livraison> livraisons;
	
	public DemandeLivraison() {
		livraisons = new ArrayList<Livraison>();
	}
	
	public Entrepot getEntrepot(){
		return this.entrepot;
	}
	
	public List<Livraison> getLivraisons(){
		return this.livraisons;
	}

	public void ajoutePointLivraison(Livraison lvrsn) {
		livraisons.add(lvrsn);
	}

	public void setEntrepot(Entrepot entrpt){
		entrepot = entrpt;
	}
	
	public void setLivraisons(List<Livraison> livs){
		this.livraisons = livs;
	}
}
