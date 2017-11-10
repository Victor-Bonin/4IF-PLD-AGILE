package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Ensemble des tronçons reliant une intersection depart a une intersection arrivee
 * @author 4104
 *
 */
public class Chemin {

	private List<Troncon> troncons;
	private Intersection depart;
	private Intersection arrivee;
	
	public Chemin(Intersection depart, Intersection arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
		this.troncons = new ArrayList<Troncon>();
	}
	
	public void addTroncon (int index, Troncon troncon){
		this.troncons.add(index, troncon);;
	}
	
	public List<Troncon> getTroncons(){
		return troncons;
	}
	
	public Intersection getDepart() {
		return depart;
	}
	
	public Intersection getArrivee() {
		return arrivee;
	}
	
	public boolean equals(Chemin chemin){
		boolean isEqual = true;
		for(int i =0; i<this.troncons.size(); i++){
			isEqual = isEqual && this.troncons.get(i).equals(chemin.getTroncons().get(i));
		}
		return isEqual;
	}
}
