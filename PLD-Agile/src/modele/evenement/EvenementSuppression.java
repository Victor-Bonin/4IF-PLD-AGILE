package modele.evenement;

import modele.Livraison;

/**
 * <pre>
 * Represente un evenement de suppression, lorsqu'un point de livraison est supprime
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
public class EvenementSuppression {

	private Livraison livraison;
	private int index;
	
	/**
	 * Constructeur d'EvenementSuppression
	 * @param livraison la livraison supprimee
	 * @param index la position de la livraison supprimee
	 */
	public EvenementSuppression(Livraison livraison, int index) {
		this.livraison = livraison;
		this.index = index;
	}
	
	/**
	 * Retourne la livraison supprimee
	 * @return la livrasion supprimee
	 */
	public Livraison getLivraison() {
		return livraison;
	}
	
	/**
	 * Retourne la position de la livraison supprimee
	 * @return la livraison supprimee
	 */
	public int getIndex() {
		return index;
	}
}
