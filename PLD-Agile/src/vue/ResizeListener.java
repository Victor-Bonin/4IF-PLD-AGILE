package vue;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * <pre>
 * Cette classe contient les appels a la vue lors de la modification de la taille de la fenetre
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
public class ResizeListener extends ComponentAdapter {
	private VuePlan vuePlan;
	
	/**
	 * Constructeur d'une instance d'un ecouteur de redimensionnement
	 * @param vp : vuePlan associee a l'ecouteur
	 */
	public ResizeListener(VuePlan vp){
		vuePlan = vp;
	}
	
	/**
	 * Actualisation de le vue plan au redimensionnement de la fenetre
	 * @see java.awt.event.ComponentAdapter#componentResized(ComponentEvent)
	 */
	@Override
    public void componentResized(ComponentEvent e) {
    	vuePlan.actualiserIcones();
    }

}
