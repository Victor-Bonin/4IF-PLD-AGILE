package vue.etat;

import javax.swing.JPanel;

import vue.Fenetre;

/**
 * <pre>
 * Interface des etats de la fenetre
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
public interface Etat {
	/**
	 * Affiche le pied de page correspondant a l'etat de la vue
	 * @param footer : le pied de page a actualiser
	 * @param fenetre : la fenetre contenant le pied de page
	 */
	void setFooter(JPanel footer, Fenetre fenetre);
	
	/**
	 * Affiche le la fenetre globale correspondant a l'etat de la vue
	 * @param fenetre : la fenetre a actualiser
	 */
	void afficherVue(Fenetre fenetre);
}
