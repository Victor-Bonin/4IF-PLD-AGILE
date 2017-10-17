package modele;

import java.util.List;

public class DemandeLivraison {
	private Entrepot entrepot;
	private List<Livraison> livraisons;
	
	public Entrepot getEntrepot(){
		return this.entrepot;
	}
	
	public List<Livraison> getLivraisons(){
		return this.livraisons;
	}
}
