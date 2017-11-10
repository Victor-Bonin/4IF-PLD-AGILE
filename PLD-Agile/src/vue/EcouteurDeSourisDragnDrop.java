package vue;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <pre>
 * 
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
public class EcouteurDeSourisDragnDrop extends MouseAdapter {
	
	VueTournee vueTournee;
	
	public EcouteurDeSourisDragnDrop(VueTournee vue){
		vueTournee = vue;
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		ElementTourneeLivraison elemt = ((ElementTourneeLivraison)e.getSource());
		vueTournee.dragCommencer(elemt);
		if (e.isPopupTrigger()) {
			elemt.showMenu(e.getComponent(), e.getX(), e.getY());
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		vueTournee.dragIn((ElementTourneeLivraison)e.getSource());
		vueTournee.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		vueTournee.dragOut((ElementTourneeLivraison)e.getSource());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		ElementTourneeLivraison elemt = ((ElementTourneeLivraison)e.getSource());
		vueTournee.stopDrag(elemt);
		if (e.isPopupTrigger()) {
			elemt.showMenu(e.getComponent(), e.getX(), e.getY());
		}
	}
		
}
