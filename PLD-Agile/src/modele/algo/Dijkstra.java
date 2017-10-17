package modele.algo;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
	
	//Renvoie un tableau {dist,previousNode} avec dist les tableaux des distances minimales de source à i et previousNode le tableau des Nodes precedants i dans le chemin le plus court
	public Integer[][] PCC(Integer[][] graph,int source){
		int nbNode = graph.length;
		Integer[] dist = new Integer[nbNode];
		Integer[] previousNode = new Integer[nbNode];
		List<Integer> unexplored = new ArrayList<Integer>();
		
		for(int i=0; i<nbNode; i++){
			dist[i]=Integer.MAX_VALUE;
			unexplored.add(i);
		}
		dist[source]=0;
		
		Integer current;
		while(!unexplored.isEmpty()){
			current = null;
			//On choisit le node inexplore ayant la plus petite dist
			for(int i = 0; i<unexplored.size(); i++){
				if(current==null || dist[unexplored.get(i)]<dist[current]){
			    	current = unexplored.get(i);
			    }
			}
			unexplored.remove(current);
			
			for(int i=0;i<nbNode;i++){
				// Si le Node est voisin de current, qu'il est inexplore et que sa dist est supérieure a celle qui passe par current
				if(graph[current][i]!=null && unexplored.contains(i) && dist[i]>dist[current]+graph[current][i]){
					dist[i]=dist[current]+graph[current][i];
					previousNode[i]=current;
				}
			}			
		}
		Integer[][] result = {dist,previousNode};
		return result;
	}
}
