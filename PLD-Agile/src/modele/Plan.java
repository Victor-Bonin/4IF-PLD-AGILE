package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Plan 
{
	private HashMap<Long, Intersection> intersections;
	private List<Troncon> troncons;
	private DemandeLivraison demandeLivraison;

	public Plan() {
		intersections = new HashMap<Long, Intersection>();
		troncons = new ArrayList<Troncon>();
		demandeLivraison = new DemandeLivraison();
	}

	public void ajoute(int x, int y, long id) {
		Intersection intersection = new Intersection(x, y, id);
		intersections.put(id,  intersection);
	}

	public void ajoute(long depart, long arrivee, float longueur, String nomRue) throws Exception {
		Intersection debut = intersections.getOrDefault(depart, null);
		Intersection fin = intersections.getOrDefault(arrivee, null);
		if(debut != null && fin != null)
		{
			Troncon troncon = new Troncon(debut, fin, nomRue, longueur);
			troncons.add(troncon);
		}
		else {
			throw new Exception("Les intersections de départ et/ou de fin pour ce troncon ne sont pas présentes dans le plan.");
		}

	}

	public void setEntrepot(Long idIntersection, Date heureDepart) throws Exception{
		Intersection intersection = intersections.getOrDefault(idIntersection, null);
		if(intersection != null) {
			Entrepot entrepot = new Entrepot(intersection, heureDepart);
			demandeLivraison.setEntrepot(entrepot);
		} else {
			throw new Exception("L'entrepôt ne correspond à aucune adresse connue");
		}
	}
	
	public void ajouterPointLivraison(Long idIntersection, int dureeLivraison) throws Exception {
		Intersection intersection = intersections.getOrDefault(idIntersection, null);
		if(intersection != null) {
			Livraison livraison = new Livraison(intersection, dureeLivraison);
			demandeLivraison.ajoutePointLivraison(livraison);
		} else {
			throw new Exception("Le point de livraison ("+ idIntersection.toString() +") ne correspond à aucune adresse connue.");
		}
	}
	
	public HashMap<Long, Intersection> getIntersections(){
		return intersections;
	}
	
	public List<Troncon> getTroncons(){
		return troncons;
	}
	
	public DemandeLivraison getDemandeLivraison(){
		return demandeLivraison;
	}
}
