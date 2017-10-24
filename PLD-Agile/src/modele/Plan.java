package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import modele.algo.Dijkstra;
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
		List<Intersection> interList = new ArrayList<Intersection>(intersections.values());
		List<Intersection> livraisons = new ArrayList<Intersection>(demandeLivraison.getLivraisons());
		Entrepot entrepot = demandeLivraison.getEntrepot();
		livraisons.add(0,entrepot);
		int nbSommets = interList.size();
		int nbLivraisons = livraisons.size();

		long[] idLivraisons = new long[nbLivraisons];

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

		int[] duree = new int[nbLivraisons];
		for(int i=1; i<nbLivraisons; i++){
			duree[i]=((Livraison)livraisons.get(i)).getDuree();
		}

		float[][] cout = new float[nbLivraisons][nbLivraisons];
		Chemin[][] pCourtsChemins = new Chemin[nbLivraisons][nbLivraisons];
		// On lance Dijkstra depuis tous les points de livraison pour remplir le tableau cout
		DjkSolution result;
		int iIndex;
		int jIndex;
		for(int i=0; i<nbLivraisons; i++){
			idLivraisons[0]=livraisons.get(i).getId();
			iIndex = interList.indexOf(livraisons.get(i));
			result = dijkstra.PCC(graph, iIndex);
			// result est un tableau contenant pour chaque intersection de graph le plus court chemin du point de livraison i a l'intersection
			for(int j=0; j<nbLivraisons; j++){
				if(i != j){
					jIndex = interList.indexOf(livraisons.get(j));
					cout[j][i]=result.dist[jIndex]; // L'index d'un point de livraison dans la liste interList correspond aussi a son index dans la matrice graph, donc result
					// On ajoute le plus court chemin entre i et j dans le tableau de Chemins
					pCourtsChemins[i][j] = new Chemin(livraisons.get(i), livraisons.get(j));
					do{
						pCourtsChemins[i][j].addTroncon(0, troncons.get(troncons.indexOf(new Troncon( interList.get(result.prev[jIndex]) , interList.get(jIndex) ))));
						jIndex = result.prev[jIndex];
					}while(jIndex != iIndex); 
				}
			}
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
				(int)cout[0][meilleureSolution[1]]);
		System.out.println("Heure de passage au point de livraison 0 : "+livs.get(0).getHeurePassage().getTime());
		for(int i = 1; i<nbLivraisons-1; i++){
			livs.get(i).setHeurePassage((Calendar)livs.get(i-1).getHeurePassage().clone());
			livs.get(i).getHeurePassage().add(Calendar.SECOND, 
					(int)cout[meilleureSolution[i-1]][meilleureSolution[i]] + livs.get(i-1).getDuree());
			System.out.println("Heure de passage au point de livraison "+i+" : "+livs.get(i).getHeurePassage().getTime());
		}
		entrepot.setHeureArrivee((Calendar)livs.get(nbLivraisons-2).getHeurePassage().clone());
		entrepot.getHeureArrivee().add(Calendar.SECOND, 
				(int)cout[meilleureSolution[nbLivraisons-1]][0] + livs.get(nbLivraisons-2).getDuree());
		System.out.println("Heure d'arrivee a l'entrepot : "+entrepot.getHeureArrivee().getTime());

		tournee = new Tournee(entrepot, livs, itineraire);
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
	
	public void ajouterPointLivraison(Livraison livraison) throws ExceptionPlanCo {
		if (livraison.getDuree() < 0) 
			throw new ExceptionPlanCo("La livraison possède une durée négative");
		demandeLivraison.ajoutePointLivraison(livraison);
	}
	
	public void ajouterPointLivraison(Livraison livraison, int index) throws ExceptionPlanCo {
		if (livraison.getDuree() < 0) 
			throw new ExceptionPlanCo("La livraison possède une durée négative");
		demandeLivraison.ajoutePointLivraison(livraison, index);
		
	}
	
	public void supprimerPointLivraison(Livraison livraison) throws ExceptionPlanCo {
		demandeLivraison.supprimerPointLivraison(livraison);	
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
