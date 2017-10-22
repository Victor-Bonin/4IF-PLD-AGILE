package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


/**
 * Cette classe contient les appels au controleur depuis la souris
 * 
 * @author 4104
 *
 */
public class EcouteurDeSouris extends MouseAdapter implements MouseWheelListener{
	private controleur.Controleur ctrl;
	private VuePlan vue;
	
	public EcouteurDeSouris(controleur.Controleur c, VuePlan f){
		ctrl = c;
		vue = f;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e){
		int cran = e.getWheelRotation();
        if (cran < 0) {
        	// Scroll haut
        	this.vue.zoom();
        } else if (cran > 0) {
        	// Scroll bas
        	this.vue.dezoom();
        } 
	}

}
