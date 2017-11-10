package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <pre>
 * Cette classe contient les appels aux fonctions de synchronisation depuis la souris
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
public class EcouteurDeSourisDeSynchronisation extends MouseAdapter {
	int place;
	VuePlan vuePlan;
	VueTournee vueTournee;
	
	public EcouteurDeSourisDeSynchronisation(int p, VuePlan vp, VueTournee vt){
		place = p;
		vuePlan = vp;
		vueTournee = vt;
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
		vuePlan.survol(place);
		vueTournee.survol(place);
	}
	
	@Override
	public void mouseExited(MouseEvent e){
		vuePlan.antiSurvol(place);
		vueTournee.antiSurvol(place);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1) {
			vueTournee.afficherDetails(place);
		}
	}


}
