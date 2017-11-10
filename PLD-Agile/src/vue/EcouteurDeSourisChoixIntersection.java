package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * <pre>
 * Cette classe contient les appels au controleur depuis la souris
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
public class EcouteurDeSourisChoixIntersection extends MouseAdapter implements MouseMotionListener{
	private controleur.Controleur ctrl;
	private VuePlan vue;
	
	public EcouteurDeSourisChoixIntersection(controleur.Controleur c, VuePlan v){
		ctrl = c;
		vue = v;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int posX = vue.positionXPlan(e.getX());
		int posY = vue.positionYPlan(e.getY());
		ctrl.obtenirPlusProcheIntersection(posX, posY);
		vue.terminerChoixIntersection();
	}
	
	public void mouseMoved(MouseEvent e) {
		vue.actualiserIconeSouris(e.getX(), e.getY());
	}

}

