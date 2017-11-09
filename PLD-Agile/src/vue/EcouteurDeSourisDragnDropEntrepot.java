package vue;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
