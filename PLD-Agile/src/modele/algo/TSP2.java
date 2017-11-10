package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <pre>
 * Un TSP avec une heuristique d'approximation du cout minimal restant et un iterateur sequentiel
 * 
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o . . o.  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  '==="  /|
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
 * @deprecated un TSP avec une meilleure heuristique est disponible
 * @see TSP4
 */
public class TSP2 extends TemplateTSP {

	/** {@inheritDoc}  */
	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		return new IteratorSeq(nonVus, sommetCrt);
	}

	/** {@inheritDoc}  */
	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int heureActuelle, int[][] cout, int[] duree, int[][] horaires) {
		int r = duree[sommetCourant];
		
		int minDebut = Integer.MAX_VALUE;
		int minAutre ;
		/*
		if(sommetCourant<0||sommetCourant>=cout.length)
			return 0;
		 */
		for(Integer courant : nonVus) {
			if(cout[sommetCourant][courant]<minDebut)
				minDebut = cout[sommetCourant][courant];
			minAutre = cout[courant][0];
			for(Integer suivant : nonVus) {
				if(courant!=suivant && cout[courant][suivant]<minAutre)
					minAutre = cout[courant][suivant];
			}
			r+=minAutre;
			r+=duree[courant];
		}
		r+=minDebut;
		
		return r;
	}
}
