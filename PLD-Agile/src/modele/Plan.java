package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import modele.algo.DjkSolution;
import modele.algo.TSP1;

/**
 * Objet contenant toutes les intersections et les troncons d'un plan, ainsi qu'une demande de livraison et les méthodes afin de traiter la demande.
 * @author 4104
 */
public class Plan {
	private final int VITESSE = 15 *(10000/3600); // 15km/h en dm/s
	private HashMap<Long, Intersection> intersections;
	private List<Troncon> troncons;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;

	public Plan() {
		intersections = new HashMap<Long, Intersection>();
		troncons = new ArrayList<Troncon>();
		demandeLivraison = new DemandeLivraison();
	}

	/**
	 * Ajoute une intersection au plan
	 * @param x Position sur l'axe x de l'intersection
	 * @param y Position sur l'axe y de l'intersection
	 * @param id Adresse de l'intersection
	 */
	public void ajouterIntersection(int x, int y, long id) {
		Intersection intersection = new Intersection(x, y, id);
		intersections.put(id,  intersection);
	}

	/**
	 * Ajoute un troncon au plan, si le plan possede deja les deux intersections du troncon, rejete l'ajout sinon
	 * @param depart Adresse de l'intersection de depart
	 * @param arrivee Adresse de l'intersection d'arrivee
 	 * @param longueur Longueur du troncon
	 * @param nomRue Nom du troncon
	 * @throws Exception
	 */
	public void ajouterTroncon(long depart, long arrivee, float longueur, String nomRue) throws ExceptionPlanCo {
		Intersection debut = intersections.getOrDefault(depart, null);
		Intersection fin = intersections.getOrDefault(arrivee, null);
		if(debut != null && fin != null)
		{
			Troncon troncon = new Troncon(debut, fin, nomRue, longueur);
			troncons.add(troncon);
		}
		else {
			throw new ExceptionPlanCo("Les intersections de depart et/ou de fin pour ce troncon ne sont pas presentes dans le plan.");
		}
	}
	
	/**
	 * Calcule l'ordre optimal des livraisons ainsi que l'itinéraire pour effectuer ces livraisons
	 */
	public void calculTournee() throws Exception {
		List<Intersection> livraisons = new ArrayList<Intersection>(demandeLivraison.getLivraisons());
		Entrepot entrepot = demandeLivraison.getEntrepot();
		livraisons.add(0,entrepot);
		int nbLivraisons = livraisons.size();
		
		TSP1 tsp = new TSP1();
		
		// Remplissage de la liste des intersections avec tous les troncons
		
		HashMap<Long, List<Troncon>> adjMap = new HashMap<Long, List<Troncon>>();
		Troncon t;
		long idDebut;
		for(int i=0; i<troncons.size(); i++){
			t = troncons.get(i);
			idDebut = t.getDebut().getId();
			if(!adjMap.containsKey(idDebut)){
				adjMap.put(idDebut,new ArrayList<Troncon>());
			}
			adjMap.get(t.getDebut().getId()).add(t);
		}

		float[][] cout = new float[nbLivraisons][nbLivraisons];
		Chemin[][] pCourtsChemins = new Chemin[nbLivraisons][nbLivraisons];
		// On lance Dijkstra depuis tous les points de livraison pour remplir le tableau cout
		DjkSolution result;
		long srcId;
		long trgId;
		for(int source=0; source<nbLivraisons; source++){
			srcId = livraisons.get(source).getId();
			result = dijkstra(adjMap, srcId);

			for(int target=0; target<nbLivraisons; target++){
				trgId = livraisons.get(target).getId();
				if(srcId != trgId){
					cout[target][source]=result.dist.get(trgId);
					// On ajoute le plus court chemin entre source et target dans le tableau de plus courts chemins
					pCourtsChemins[source][target] = new Chemin(livraisons.get(source), livraisons.get(target));
					do{
						pCourtsChemins[source][target].addTroncon(0, troncons.get(troncons.indexOf(new Troncon( intersections.get(result.prev.get(trgId)) , intersections.get(trgId) ))));
						trgId = result.prev.get(trgId);
					}while(trgId != srcId); 
				}
			}
		}
		
		int[] duree = new int[nbLivraisons];
		for(int i=1; i<nbLivraisons; i++){
			duree[i] = ((Livraison)livraisons.get(i)).getDuree();
		}
		
		int tpsLimite = 10000;
		Integer[] meilleureSolution = tsp.chercheSolution(tpsLimite, nbLivraisons, cout, duree);

		Itineraire itineraire = new Itineraire(pCourtsChemins, meilleureSolution);

		List<Livraison> livs = new ArrayList<Livraison>(nbLivraisons);
		for (int i = 1; i < nbLivraisons; i++ ){
			livs.add((Livraison)livraisons.get(meilleureSolution[i]));
		}

		livs.get(0).setHeurePassage((Calendar)entrepot.getHeureDepart().clone());
		livs.get(0).getHeurePassage().add(Calendar.SECOND, 
				(int)cout[0][meilleureSolution[1]] + livs.get(0).getDuree());
//		System.out.println("Heure de passage au point de livraison 0 : "+livs.get(0).getHeurePassage().getTime());
		for(int i = 1; i<nbLivraisons-1; i++){
			livs.get(i).setHeurePassage((Calendar)livs.get(i-1).getHeurePassage().clone());
			livs.get(i).getHeurePassage().add(Calendar.SECOND, 
					(int)cout[meilleureSolution[i-1]][meilleureSolution[i]] + livs.get(i).getDuree());
//			System.out.println("Heure de passage au point de livraison "+i+" : "+livs.get(i).getHeurePassage().getTime());
		}
		entrepot.setHeureArrivee((Calendar)livs.get(nbLivraisons-2).getHeurePassage().clone());
		entrepot.getHeureArrivee().add(Calendar.SECOND, (int)cout[meilleureSolution[nbLivraisons-1]][0]);
//		System.out.println("Heure d'arrivee a l'entrepot : "+entrepot.getHeureArrivee().getTime());

		tournee = new Tournee(entrepot, livs, itineraire);
	}

	//Renvoie une solution {dist,previousNode} avec dist la hashmap des distances minimales de source a i et previousNode la hashmap des Nodes precedants i dans le chemin le plus court
	private DjkSolution dijkstra(HashMap<Long, List<Troncon>> adjMap, long source){
		HashMap<Long, Float> dist = new HashMap<Long, Float>();
		HashMap<Long, Long> previousNode = new HashMap<Long, Long>();
		List<Long> unexplored = new ArrayList<Long>();
		
		for(Entry<Long, Intersection> i : intersections.entrySet()) {
			dist.put(i.getKey(), Float.MAX_VALUE);
			unexplored.add(i.getKey());
		}
		dist.put(source, (float)0);
		
		Long current;
		while(!unexplored.isEmpty()){
			current = null;
			//On choisit le node inexplore ayant la plus petite dist
			for(int i = 0; i<unexplored.size(); i++){
				if(current==null || dist.get(unexplored.get(i))<dist.get(current)){
			    	current = unexplored.get(i);
			    }
			}
			unexplored.remove(current);
			
			// Pour tous les adjacents du Node current
			if(adjMap.containsKey(current)){
				for(int i=0;i<adjMap.get(current).size();i++){
					Troncon t = adjMap.get(current).get(i);
					Long arrivee = t.getFin().getId();
					// Si le Node d'arrivee du troncon est inexplore et que sa dist est superieure a celle qui passe par current
					if(unexplored.contains(arrivee) && dist.get(arrivee)>dist.get(current)+(t.getLongueur()/VITESSE) ){
						dist.put(arrivee, dist.get(current)+(t.getLongueur()/VITESSE));
						previousNode.put(arrivee, current);
					}
				}	
			}
		}
		DjkSolution result = new DjkSolution();
		result.dist = dist;
		result.prev = previousNode;
		return result;
	}
	
	/**
	 * Ajoute un entrepot a la demande de livraison du plan si l'entrepot correspond a une adresse du plan
	 * @param idIntersection adresse de l'entrepot
	 * @param heureDepart heure de depart de la tournee
	 * @throws Exception L'entrepot ne correspond a aucune intersection du plan
	 */
	public void setEntrepot(Long idIntersection, Calendar heureDepart) throws ExceptionPlanCo{
		Intersection intersection = intersections.getOrDefault(idIntersection, null);
		if(intersection != null) {
			Entrepot entrepot = new Entrepot(intersection, heureDepart);
			demandeLivraison.setEntrepot(entrepot);
		} else {
			throw new ExceptionPlanCo("L'entrepôt ne correspond à aucune adresse connue");
		}
	}

	/**
	 * Ajoute un point de livraison à la demande de livraison si elle correspond a une intersection du plan
	 * @param idIntersection Adresse de la livraison
	 * @param dureeLivraison Duree de la livraison
	 * @throws Exception La livraison ne correspond a aucune intersection du plan
	 */
	public void ajouterPointLivraison(Long idIntersection, int dureeLivraison) throws ExceptionPlanCo {
		Intersection intersection = intersections.getOrDefault(idIntersection, null);
		if(intersection != null) {
			Livraison livraison = new Livraison(intersection, dureeLivraison);
			demandeLivraison.ajoutePointLivraison(livraison);
		} else {
			throw new ExceptionPlanCo("Le point de livraison ("+ idIntersection.toString() +") ne correspond à aucune adresse connue.");
		}
	}

	public HashMap<Long, Intersection> getIntersections(){
		return intersections;
	}

	public List<Troncon> getTroncons(){
		return troncons;
	}

	/**
	 * Reinitialise le plan
	 */
	public void reset() {
		intersections.clear();
		troncons.clear();
		resetDemandeLivraison();
	}

	public void resetDemandeLivraison() {
		demandeLivraison = new DemandeLivraison();
		tournee = null;
	}

	public DemandeLivraison getDemandeLivraison(){
		return demandeLivraison;
	}
	
	public Tournee getTournee(){
		return tournee;
	}
}
