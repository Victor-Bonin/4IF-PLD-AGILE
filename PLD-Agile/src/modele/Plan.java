package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import modele.algo.Dijkstra;
import modele.algo.DjkSolution;
import modele.algo.TSP1;

public class Plan {
	private final int VITESSE = 15 *(10000/3600); // 15km/h en dm/s
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
			throw new Exception("Les intersections de depart et/ou de fin pour ce troncon ne sont pas presentes dans le plan.");
		}
	}
	
	public void calculTournee(){
		List<Intersection> interList = new ArrayList<Intersection>(intersections.values());
		List<Livraison> livraisons = demandeLivraison.getLivraisons();
		Entrepot entrepot = demandeLivraison.getEntrepot();
		int nbSommets = interList.size();
		int nbLivraisons = livraisons.size();
		TSP1 tsp = new TSP1();
		Dijkstra dijkstra = new Dijkstra();
		
		// Remplissage du graphe des intersections avec tous les troncons
		Float[][] graph = new Float[nbSommets][nbSommets];
		int interDebut;
		int interFin;
		Troncon t;
		for(int i=0; i<troncons.size(); i++){
			t = troncons.get(i);
			interDebut = interList.indexOf(t.getDebut());
			interFin = interList.indexOf(t.getFin());
			graph[interDebut][interFin] = t.getLongueur()/VITESSE;
		}
		
//					System.out.println("GRAPH :");
//					for(int i=0; i<graph.length; i++){
//						for(int j=0; j<graph[0].length; j++){
//							if(graph[i][j]!=null){
//								System.out.println("Troncon de l'intersection "+interList.get(i).getId()+" a l'intersection "+interList.get(j).getId()+" mesure "+graph[i][j]);
//							}
//						}
//					}
//					System.out.println();
		
		int[] duree = new int[nbLivraisons+1];
		for(int i=0; i<nbLivraisons; i++){
			duree[i+1]=livraisons.get(i).getDuree();
		}
			
		float[][] cout = new float[nbLivraisons+1][nbLivraisons+1];
		// On lance Dijkstra depuis tous les points de livraison pour remplir le tableau cout
		DjkSolution result;
		for(int i=0; i<nbLivraisons; i++){
			result = dijkstra.PCC(graph, interList.indexOf(livraisons.get(i)));
			// result est un tableau contenant pour chaque intersection de graph le plus court chemin du point de livraison i a l'intersection
			for(int j=0; j<nbLivraisons; j++){
				cout[i+1][j+1]=result.dist[interList.indexOf(livraisons.get(j))]; // L'index d'un point de livraison dans la liste interList correspond aussi a son index dans la matrice graph, donc result
				cout[i+1][0]=result.dist[interList.indexOf(entrepot)];
			}
		}
		// On lance aussi Dijkstra depuis l'entrepot
		result = dijkstra.PCC(graph, interList.indexOf(entrepot));
		for(int i=0; i<nbLivraisons; i++){
			cout[0][i+1]=result.dist[interList.indexOf(livraisons.get(i))];
		}
		
//					System.out.println("Cout :");
//					for(int i=0; i<cout.length; i++){
//						for(int j=0; j<cout[0].length; j++){
//							System.out.print(cout[i][j]+" ");
//						}
//						System.out.println();
//					}
//					System.out.println();
		
		for(int i=0;i<duree.length;i++){
			System.out.println(duree[i]);
		}
		int tpsLimite = 10000;
		tsp.chercheSolution(tpsLimite, nbLivraisons+1, cout, duree);
		
		System.out.println("temps de la solution : "+tsp.getCoutMeilleureSolution());
		System.out.print("Chemin de la solution : ");
		for(int i=0;i<nbLivraisons+1;i++){
			System.out.print(tsp.getMeilleureSolution(i)+" ");
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

	public void reset() {
		intersections.clear();
		troncons.clear();
		demandeLivraison = new DemandeLivraison();
	}

	public DemandeLivraison getDemandeLivraison(){
		return demandeLivraison;
	}
}
