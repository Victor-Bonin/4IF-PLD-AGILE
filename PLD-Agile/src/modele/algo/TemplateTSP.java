package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <pre>
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
 *             \      .: /|\
 *             )\_   .: / |:"--___
 *         __-:|\ """ _-  |:::::::
 *       _-::::\ "-_.-   /::::::::
 *    _--:::::::| .|"-_  |::::::::
 *  -"::::::::::\  | { -_|::::::::
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr
 * </pre>
 * 
 * @author 4104
 */
public abstract class TemplateTSP implements TSP {

	private Integer[] meilleureSolution;
	private int coutMeilleureSolution = 0;
	private Boolean tempsLimiteAtteint;
//	private int compteur = 0;
	
	public Boolean getTempsLimiteAtteint(){
		return tempsLimiteAtteint;
	}
	
	public Integer[] chercheSolution(int tpsLimite, int nbSommets, int[][] couts, int[] durees, int[][] horaires){
		tempsLimiteAtteint = false;
		coutMeilleureSolution = Integer.MAX_VALUE;
		meilleureSolution = new Integer[nbSommets];
		ArrayList<Integer> nonVus = new ArrayList<Integer>();
		for (int i=1; i<nbSommets; i++) nonVus.add(i);
		ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
		vus.add(0); // le premier sommet visite est 0
		branchAndBound(0, nonVus, vus, horaires[0][0] , couts, durees, horaires, System.currentTimeMillis(), tpsLimite);
//		System.out.println("Tentatives : " + compteur);
//		System.out.println("Total d'apres algo : " + (coutMeilleureSolution/60));
		return meilleureSolution;
	}
	
	public Integer getMeilleureSolution(int i){
		if ((meilleureSolution == null) || (i<0) || (i>=meilleureSolution.length))
			return null;
		return meilleureSolution[i];
	}
	
	public int getCoutMeilleureSolution(){
		return coutMeilleureSolution;
	}
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCourant
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param heureActuelle : heure a laquelle on quitte le sommetCourant
	 * @param couts : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param durees : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @param horaires : horaires[i][k] = horaire du créneau pour le point i, k=0 pour horaireDébut, k=1 pour horaire fin
	 * @return une borne inferieure du cout des permutations commencant par sommetCourant, 
	 * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
	 */
	protected abstract int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureActuelle, int[][] couts, int[] durees, int[][] horaires);
	
	/** 
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCrt
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param heureActuelle : heure a laquelle on quitte le sommetCourant
	 * @param couts : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param durees : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @param horaires : horaires[i][k] = horaire du créneau pour le point i, k=0 pour horaireDébut, k=1 pour horaire fin
	 * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
	 */
	protected abstract Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureActuelle, int[][] couts, int[] durees, int[][] horaires);
	
	/**
	 * Methode definissant le patron (template) d'une resolution par separation et evaluation (branch and bound) du TSP
	 * @param sommetCrt le dernier sommet visite
	 * @param nonVus la liste des sommets qui n'ont pas encore ete visites
	 * @param vus la liste des sommets visites (y compris sommetCrt)
	 * @param heureFinActuelle la somme des couts des arcs du chemin passant par tous les sommets de vus + la somme des duree des sommets de vus
	 * @param couts : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param durees : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @param horaires : horaires[i][k] = horaire du créneau pour le point i, k=0 pour horaireDébut, k=1 pour horaire fin
	 * @param tpsDebut : moment ou la resolution a commence
	 * @param tpsLimite : limite de temps pour la resolution
	 */	
	 void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, int heureFinActuelle, int[][] couts, int[] durees, int[][] horaires, long tpsDebut, int tpsLimite){
//		 compteur++;
		 if (System.currentTimeMillis() - tpsDebut > tpsLimite){
			 tempsLimiteAtteint = true;
			 return;
		 }
		 
		 int bound = bound(sommetCrt, nonVus, heureFinActuelle, couts, durees, horaires);
		 
		 if (nonVus.isEmpty()){ // tous les sommets ont ete visites
	    	heureFinActuelle += couts[sommetCrt][0];
	    	if (heureFinActuelle < coutMeilleureSolution){ // on a trouve une solution meilleure que meilleureSolution
	    		vus.toArray(meilleureSolution);
	    		coutMeilleureSolution = heureFinActuelle;
    		}
		 
		 } else if (heureFinActuelle + bound < coutMeilleureSolution && bound != Integer.MAX_VALUE){
	        Iterator<Integer> it = iterator(sommetCrt, nonVus, heureFinActuelle, couts, durees, horaires);
	        while (it.hasNext()){
	        	Integer prochainSommet = it.next();
	        	vus.add(prochainSommet);
	        	nonVus.remove(prochainSommet);
	        	
	        	int wait = 0;
	        	if(horaires[prochainSommet][0]!=-1)
	        		wait  = Math.max(0, horaires[prochainSommet][0] - (heureFinActuelle + couts[sommetCrt][prochainSommet]));
	        	
	        	branchAndBound(prochainSommet, nonVus, vus, heureFinActuelle + couts[sommetCrt][prochainSommet] + wait + durees[prochainSommet], couts, durees, horaires, tpsDebut, tpsLimite); 
	        	vus.remove(prochainSommet);
	        	nonVus.add(prochainSommet);
	        }	    
	    }
	}
}

