package controleur;

import modele.ExceptionPlanCo;

/**
 * <pre>
 * Interface Commande pour le design pattern Commande.  
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
 *  
 * @author 4104
 */
public interface Commande {

	/**
	 * Execute la commande this
	 * @throws ExceptionPlanCo 
	 */
	public void doCde() throws ExceptionPlanCo;
	
	/**
	 * Execute la commande inverse a this
	 * @throws ExceptionPlanCo 
	 */
	public void undoCde() throws ExceptionPlanCo;
}
