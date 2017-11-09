package modele.evenement;

import modele.Livraison;

public class EvenementSuppression {
	private Livraison livraison;
	private int index;
	
	public EvenementSuppression(Livraison livraison, int index) {
		this.livraison = livraison;
		this.index = index;
	}
	
	public Livraison getLivraison() {
		return livraison;
	}
	
	public int getIndex() {
		return index;
	}
}
