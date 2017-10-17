package modele;

import java.util.ArrayList;
import java.util.List;

public class Plan 
{
	private List<Intersection> intersections;
	private List<Troncon> troncons;
	
	public Plan() {
		intersections = new ArrayList<Intersection>();
		troncons = new ArrayList<Troncon>();
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
	
	public List<Intersection> getIntersections(){
		return intersections;
	}
	
	public List<Troncon> getTroncons(){
		return troncons;
	}
}
