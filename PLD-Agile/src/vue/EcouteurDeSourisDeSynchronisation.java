package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;


/**
 * Cette classe contient les appels aux fonctions de synchronisation depuis la souris
 * 
 * @author 4104
 *
 */

public class EcouteurDeSourisDeSynchronisation extends MouseAdapter {
	int place;
	VuePlan vuePlan;
	//VueTournee vueTournee;
	
	public EcouteurDeSourisDeSynchronisation(int p, VuePlan vp, VueTournee vt){
		place = p;
		vuePlan = vp;
		//vueTournee = vt;
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
		vuePlan.survol(place);
		
	}
	
	@Override
	public void mouseExited(MouseEvent e){
		vuePlan.antiSurvol(place);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		
	}


}
