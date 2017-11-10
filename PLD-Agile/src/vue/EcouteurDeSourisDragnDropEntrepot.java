package vue;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
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
 * 
 * 
 * @author 4104
 */
public class EcouteurDeSourisDragnDropEntrepot extends MouseAdapter {
	
	VueTournee vueTournee;
	
	public EcouteurDeSourisDragnDropEntrepot(VueTournee vue){
		vueTournee = vue;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("entered");
		vueTournee.dragIn((ElementTourneeEntrepot)e.getSource());
		vueTournee.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		vueTournee.dragOut((ElementTourneeEntrepot)e.getSource());
	}
		
}
