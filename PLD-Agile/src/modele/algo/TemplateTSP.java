package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TemplateTSP implements TSP {
	
	private Integer[] meilleureSolution;
	private float coutMeilleureSolution = 0;
	private Boolean tempsLimiteAtteint;
	private int compteur = 0;
	
	public Boolean getTempsLimiteAtteint(){
		return tempsLimiteAtteint;
	}
	
	public Integer[] chercheSolution(int tpsLimite, int nbSommets, float[][] cout, int[] duree, int[][] horaires){
		tempsLimiteAtteint = false;
		coutMeilleureSolution = Float.MAX_VALUE;
		meilleureSolution = new Integer[nbSommets];
		ArrayList<Integer> nonVus = new ArrayList<Integer>();
		for (int i=1; i<nbSommets; i++) nonVus.add(i);
		ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
		vus.add(0); // le premier sommet visite est 0
		branchAndBound(0, nonVus, vus, 0, horaires[0][0] , cout, duree, horaires, System.currentTimeMillis(), tpsLimite);
		System.out.println("Tentatives : " + compteur);
		return meilleureSolution;
	}
	
	public Integer getMeilleureSolution(int i){
		if ((meilleureSolution == null) || (i<0) || (i>=meilleureSolution.length))
			return null;
		return meilleureSolution[i];
	}
	
	public float getCoutMeilleureSolution(){
		return coutMeilleureSolution;
	}
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCourant
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @return une borne inferieure du cout des permutations commencant par sommetCourant, 
	 * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
	 */
	protected abstract float bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureDebut, float[][] cout, int[] duree, int[][] horaires);
	
	/** 
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCrt
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
	 */
	protected abstract Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureDebut, float[][] cout, int[] duree, int[][] horaires);
	
	/**
	 * Methode definissant le patron (template) d'une resolution par separation et evaluation (branch and bound) du TSP
	 * @param sommetCrt le dernier sommet visite
	 * @param nonVus la liste des sommets qui n'ont pas encore ete visites
	 * @param vus la liste des sommets visites (y compris sommetCrt)
	 * @param f la somme des couts des arcs du chemin passant par tous les sommets de vus + la somme des duree des sommets de vus
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @param tpsDebut : moment ou la resolution a commence
	 * @param tpsLimite : limite de temps pour la resolution
	 */	
	 void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, float f, int heureDebut, float[][] cout, int[] duree, int[][] horaires, long tpsDebut, int tpsLimite){
		 compteur++;
		 if (System.currentTimeMillis() - tpsDebut > tpsLimite){
			 tempsLimiteAtteint = true;
			 return;
		 }
		 float bound = bound(sommetCrt, nonVus, heureDebut, cout, duree, horaires);
	    if (nonVus.isEmpty()){ // tous les sommets ont ete visites
	    	f += cout[sommetCrt][0];
	    	if (f < coutMeilleureSolution){ // on a trouve une solution meilleure que meilleureSolution
	    		vus.toArray(meilleureSolution);
	    		coutMeilleureSolution = f;
	    	}
	    } else if (f + bound < coutMeilleureSolution && bound != Integer.MAX_VALUE){
	        Iterator<Integer> it = iterator(sommetCrt, nonVus, heureDebut, cout, duree, horaires);
	        while (it.hasNext()){
	        	Integer prochainSommet = it.next();
	        	vus.add(prochainSommet);
	        	nonVus.remove(prochainSommet);
	        	float wait = 0;
	        	if(horaires[sommetCrt][0]!=-1) {
		        	wait = horaires[sommetCrt][0] - heureDebut - f;
		        	wait=(wait<0)?0:wait;
	        	}
	        	branchAndBound(prochainSommet, nonVus, vus, f + cout[sommetCrt][prochainSommet] + duree[sommetCrt] + wait, heureDebut , cout, duree, horaires, tpsDebut, tpsLimite); 
	        	vus.remove(prochainSommet);
	        	nonVus.add(prochainSommet);
	        }	    
	    }
	}
}

