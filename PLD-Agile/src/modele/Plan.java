package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modele.algo.DjkSolution;
import modele.algo.TSP;
import modele.algo.TSP4;

/**
 * Objet contenant toutes les intersections et les troncons d'un plan, ainsi qu'une demande de livraison et les méthodes afin de traiter la demande.
 * @author 4104
 */
public class Plan {
	private final float VITESSE = 15 *(10000f/3600); // 15km/h en dm/s
	private final int LIMITE_TSP = 10000;
	private HashMap<Long, Intersection> intersections;
	private List<Troncon> troncons;
	private DemandeLivraison demandeLivraison;

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
		
	//A supprimer, permets de mesurer les temps pour du debug
	long debutDelay;
	long finDelay;
		
		
		List<Intersection> livraisons = new ArrayList<Intersection>(demandeLivraison.getLivraisons());
		Entrepot entrepot = demandeLivraison.getEntrepot();
		livraisons.add(0,entrepot);
		int nbLivraisons = livraisons.size();
		
		TSP tsp = new TSP4();
		
		// Remplissage de la liste des intersections avec tous les troncons

		//Data for Dijkstra
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

	debutDelay = System.currentTimeMillis();
		//Dijktra
		int[][] cout = new int[nbLivraisons][nbLivraisons];
		Chemin[][] pCourtsChemins = new Chemin[nbLivraisons][nbLivraisons];
		DjkSolution result;
		long srcId;
		long trgId;
		Iterator<Intersection> it = livraisons.iterator();
		int source = 0;
		while(it.hasNext()) {
			Intersection livrDepart = it.next();
			srcId = livrDepart.getId();
			result = dijkstraFull(adjMap, srcId);
			Iterator<Intersection> it2 = livraisons.iterator();
			int target = 0;
			while(it2.hasNext()) {
				Intersection livrArrivee = it2.next();
				trgId = livrArrivee.getId();
				if(srcId != trgId){
					cout[source][target]=Math.round(result.dist.get(trgId));
					// On ajoute le plus court chemin entre source et target dans le tableau de plus courts chemins
					pCourtsChemins[source][target] = new Chemin(livrDepart, livrArrivee);
					do{
						pCourtsChemins[source][target].addTroncon(0, troncons.get(troncons.indexOf(new Troncon( intersections.get(result.prev.get(trgId)) , intersections.get(trgId) ))));
						trgId = result.prev.get(trgId);
					}while(trgId != srcId); 
				}
				target++;
			}
			source++;
		}
		
	finDelay = System.currentTimeMillis();
	System.out.println("Temps de Dijkstra: " + (finDelay - debutDelay) + "ms");
	
	
		//Data for the TSP
		int[] duree = new int[nbLivraisons];
		for(int i=1; i<nbLivraisons; i++){
			duree[i] = ((Livraison)livraisons.get(i)).getDuree();
		}
		
		int[][] horairesInt = new int[nbLivraisons][2]; 
		horairesInt[0][0] = getSecondsInDay(entrepot.getHeureDepart());
		horairesInt[0][1] = getSecondsInDay(entrepot.getHeureArrivee());
		for(int i=1; i<nbLivraisons; i++){
			if(livraisons.get(i) instanceof LivraisonPlageHoraire){
				horairesInt[i][0] = getSecondsInDay(((LivraisonPlageHoraire)livraisons.get(i)).getDebut());
				horairesInt[i][1] = getSecondsInDay(((LivraisonPlageHoraire)livraisons.get(i)).getFin());
			}else{
				horairesInt[i][0] = -1;
				horairesInt[i][1] = -1;
			}
		}
	debutDelay = System.currentTimeMillis();
		//TSP
		Integer[] meilleureSolution = tsp.chercheSolution(LIMITE_TSP, nbLivraisons, cout, duree, horairesInt);
	finDelay = System.currentTimeMillis();
	System.out.println("Temps de TSP : " + (finDelay - debutDelay) + "ms");

		if(meilleureSolution==null)
			throw new ExceptionPlanCo("Solution ou tournee vide !");
		if(meilleureSolution[0]==null)
			throw new ExceptionPlanCo("Aucune solution respectant les contraintes n'a pas être trouvée");
	
		setTourneeFromTsp(pCourtsChemins, cout, livraisons, meilleureSolution);
	}
	
	/**
	 * 
	 * @param pCourtsChemins
	 * @param couts
	 * @param livraisons
	 * @param ordreTournee
	 */
	private void setTourneeFromTsp(Chemin[][] pCourtsChemins, int[][] couts, List<Intersection> livraisons, Integer[] ordreTournee) {
		int nbLivraisons = ordreTournee.length; 
		Itineraire itineraire = new Itineraire(pCourtsChemins, ordreTournee);
		Entrepot entrepot = (Entrepot)livraisons.get(0);

		List<Livraison> livs = new ArrayList<Livraison>(nbLivraisons-1);
		for (int i = 1; i < nbLivraisons; i++ ){
			livs.add((Livraison)livraisons.get(ordreTournee[i]));
		}
		
		Calendar heureDePassage = (Calendar)entrepot.getHeureDepart().clone();
		int heureDePassageInt = getSecondsInDay(heureDePassage);
		Livraison livPremiere = livs.get(0);
		livPremiere.setHeurePassage((Calendar)heureDePassage.clone());
		if(livPremiere instanceof LivraisonPlageHoraire){
			livPremiere.getHeurePassage().add(Calendar.SECOND,Math.max(couts[0][ordreTournee[1]],
					getSecondsInDay(((LivraisonPlageHoraire)livs.get(0)).getDebut()) - heureDePassageInt));
		}else {
			livPremiere.getHeurePassage().add(Calendar.SECOND,couts[0][ordreTournee[1]]);
		}
		
		for(int i = 1; i<nbLivraisons-1; i++){
			Livraison livI = livs.get(i);
			
			heureDePassage = (Calendar)livs.get(i-1).getHeurePassage().clone();
			heureDePassage.add(Calendar.SECOND, livs.get(i-1).getDuree());
			heureDePassageInt = getSecondsInDay(heureDePassage);
			
			livI.setHeurePassage((Calendar)heureDePassage.clone());
			if(livI instanceof LivraisonPlageHoraire){
				livI.getHeurePassage().add(Calendar.SECOND,Math.max(couts[ordreTournee[i]][ordreTournee[i+1]],
						getSecondsInDay(((LivraisonPlageHoraire)livs.get(i)).getDebut()) - heureDePassageInt));
			}else {
				livI.getHeurePassage().add(Calendar.SECOND,couts[ordreTournee[i]][ordreTournee[i+1]]);
			}
		}
		
		entrepot.setHeureArrivee((Calendar)livs.get(nbLivraisons-2).getHeurePassage().clone());
		entrepot.getHeureArrivee().add(Calendar.SECOND, (couts[ordreTournee[nbLivraisons-1]][0] + livs.get(nbLivraisons-2).getDuree()));

		demandeLivraison = new Tournee(entrepot, livs, itineraire);
		
	}
	
	/**
	 * Renvoie une solution {dist,previousNode} avec dist la hashmap des distances minimales de source a i et previousNode la hashmap des Nodes precedants i dans le chemin le plus court
	 */
	private DjkSolution dijkstraFull(HashMap<Long, List<Troncon>> adjMap, long source/*, long[] targets*/){

		Long current = source;
		
		//Distances
		HashMap<Long, Float> dist = new HashMap<Long, Float>();
		dist.put(current, (float)0);
		//Queue to near unexplored
		//Set<Long> queue = new HashSet<Long>();
		List<Long> queue = new ArrayList<Long>();
		queue.add(current);
		//Previous node for each explored node
		HashMap<Long, Long> previousNode = new HashMap<Long, Long>();
		//List of explored nodes (their distance in "dist" array are the final distances)
		Set<Long> explored = new HashSet<Long>();
		
		boolean continueLoop = true;
		while(!queue.isEmpty() && continueLoop) {
			Iterator<Long> it = queue.iterator();

			Long candidat = null;
			
			if(it.hasNext())
				candidat = it.next();
			current = candidat;
			
			while(it.hasNext()) {
				candidat = it.next();
				if(dist.get(candidat)<dist.get(current)){
			    	current = candidat;
			    }
			}
			
			explored.add(current);
			queue.remove(current);
			
			// Pour tous les adjacents du Node current
			if(adjMap.containsKey(current)){
				//for(int i=0;i<adjMap.get(current).size();i++){
				for(Troncon t : adjMap.get(current)){
					//Troncon t = adjMap.get(current).get(i);
					Long arrivee = t.getFin().getId();
					// Si le Node d'arrivee du troncon est inexplore et que sa dist est superieure a celle qui passe par current
					if(!explored.contains(arrivee)){
						if(queue.contains(arrivee)) {
							if(dist.get(arrivee)>dist.get(current)+(t.getLongueur()/VITESSE)) {
								dist.put(arrivee, dist.get(current)+(t.getLongueur()/VITESSE));
								previousNode.put(arrivee, current);
							}
						}else {
							queue.add(arrivee);
							dist.put(arrivee, dist.get(current)+(t.getLongueur()/VITESSE));
							previousNode.put(arrivee, current);
						}
					}
				}	
			}
			
			/*
			 * On ne va pas utiliser l'arret anticipé, dans notre cas 
			 * vérifier la condition demande plus de calcul que finir les itérations.
			 * En général on traite tout de même 10 000 noeuds au moins sur 12 000, 
			 * (Condition donc testée 10 000 fois au lieu de laisser tourner 2000 fois)
			 */
			/*
			//Can we end shortly ?
			continueLoop = false;
			for(int i=0;i<targets.length;i++) {
				if(!explored.contains(targets[i])) {
					continueLoop = true;
				}
			}
			*/
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
	public void ajouterPointLivraison(Long idIntersection, int dureeLivraison, Calendar debutPlage, Calendar finPlage) throws ExceptionPlanCo {
		Intersection intersection = intersections.getOrDefault(idIntersection, null);
		if(intersection != null) {
			Livraison livraison;
			if(debutPlage == null || finPlage == null) {
				livraison = new Livraison(intersection, dureeLivraison);
			} else {
				livraison = new LivraisonPlageHoraire(intersection, dureeLivraison, debutPlage, finPlage);
			}
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
	
	/**
	 * Retourne la liste des troncons voisins d'une intersection
	 * Il n'est pas conseillé d'utiliser cette méthode pour obtenir la liste des 
	 * troncons voisins de n intersections.
	 * @param idIntersection id de l'intersection dont il faut les voisins
	 * @return la liste des voisins de l'intersection désiré
	 */
	public List<Troncon> listerTronconVoisin(Long idIntersection){
		Intersection intersection = intersections.get(idIntersection);
		List<Troncon> tronconsVoisins = new ArrayList<Troncon>();
		if(intersection == null)
			return tronconsVoisins;
		for (Troncon t : troncons)
			if (t.getDebut().equals(intersection) || t.getFin().equals(intersection))
				tronconsVoisins.add(t);
		return tronconsVoisins;
	}
	
	/**
	 * Créer un dico contenant pour chaque intersection 
	 * la liste des troncons qui partent ou viennent vers elle
	 * @return un dictionnaire
	 */
	public HashMap<Long, List<Troncon>> obtenirVoisinParIntersection(){
		HashMap<Long, List<Troncon>> croisement = new HashMap<Long, List<Troncon>>();
		Long idDebut, idFin;
		for(Troncon t : troncons) {
			idDebut = t.getDebut().getId();
			idFin = t.getFin().getId();
			if (croisement.containsKey(idDebut))
				croisement.get(idDebut).add(t);
			else {
				croisement.put(idDebut, new ArrayList<Troncon>());
				croisement.get(idDebut).add(t);
			}
			
			if (croisement.containsKey(t.getFin().getId()))
				croisement.get(idFin).add(t);
			else {
				croisement.put(idFin, new ArrayList<Troncon>());
				croisement.get(idFin).add(t);
			}
		}
		return croisement;
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
	}

	public DemandeLivraison getDemandeLivraison(){
		return demandeLivraison;
	}
	
	public Tournee getTournee(){
		if (demandeLivraison instanceof Tournee)
			return (Tournee)demandeLivraison;
		return null;
	}
	
	/**
	 * With an offset for the moment
	 * @param cal
	 * @return
	 */
	private int getSecondsInDay(Calendar cal) {
		//TODO changer date
		//cal.set(Calendar.YEAR, 1900);
		if(cal==null) {
			return -1;
		}
		return (int)(cal.getTimeInMillis()/1000);
	}
	
	public Intersection obtenirPlusProcheIntersection(double x, double y) {
		Intersection plusProche = null;
		double distanceMin = Double.MAX_VALUE;
		for (Intersection intersec : intersections.values()) {
			double distance = Math.pow(intersec.getX()-x, 2) + Math.pow(intersec.getY()-y, 2);
		    if(distance < distanceMin) {
		    	distanceMin = distance;
		    	plusProche = intersec;
		    }
		}
		return plusProche;
	}
}