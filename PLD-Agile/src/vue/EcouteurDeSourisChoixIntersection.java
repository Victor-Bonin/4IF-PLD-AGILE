package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


/**
 * Cette classe contient les appels au controleur depuis la souris
 * 
 * @author 4104
 *
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

