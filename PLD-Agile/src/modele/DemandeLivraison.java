package modele;

import java.util.ArrayList;
import java.util.List;

public class DemandeLivraison {
	private Entrepot entrepot;
	private List<Livraison> livraisons;
	
	public public DemandeLivraison() {
		entrepot = new Entrepot(coordX, coordY, id);
		livraisons = new ArrayList<Livraison>();
	}
	
	public void ajoute(int x, int y, long id) {
		Intersection intersection = new Intersection(x, y, id);
		intersections.add(intersection);
	}
	
	public void ajoute(long depart, long arrivee, float longueur, String nomRue) throws Exception {
		Intersection debut = intersections.stream().filter(x->x.getid() == depart).findFirst().orElse(null);
		Intersection fin = intersections.stream().filter(x->x.getid() == arrivee).findFirst().orElse(null);
		if(debut != null && fin != null)
		{
			Troncon troncon = new Troncon(debut, fin, nomRue, longueur);
			troncons.add(troncon);
		}
		else {
			throw new Exception("Les intersections de départ et/ou de fin pour ce troncon ne sont pas présentes dans le plan.");
		}
		
	}
}
