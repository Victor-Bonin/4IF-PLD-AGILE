package modele.evenement;

import modele.Livraison;

public class EvenementInsertion {
	private Livraison livraison;
	
	public EvenementInsertion(Livraison livraison) {
		this.livraison = livraison;
	}
	
	public Livraison getLivraison() {
		return livraison;
	}
}
