package modele.evenement;

import modele.Livraison;

/**
 * <pre>
 * Represente un evenement d'insertion, lorsqu'un point de livraison est ajoute
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
 */
public class EvenementInsertion {
	private Livraison livraison;
	
	/**
	 * Constructeur d'EvenementInsertion
	 * @param livraison la livraison insere
	 */
	public EvenementInsertion(Livraison livraison) {
		this.livraison = livraison;
	}
	
	/**
	 * Retourne la livraison inseree
	 * @return la livraison insere
	 */
	public Livraison getLivraison() {
		return livraison;
	}
}
