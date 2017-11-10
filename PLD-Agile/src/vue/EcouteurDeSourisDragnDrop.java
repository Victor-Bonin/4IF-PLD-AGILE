package vue;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
