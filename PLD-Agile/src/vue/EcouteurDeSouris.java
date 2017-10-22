package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;


/**
 * Cette classe contient les appels au controleur depuis la souris
 * 
 * @author 4104
 *
 */
public class EcouteurDeSouris extends MouseAdapter implements MouseWheelListener{
	private controleur.Controleur ctrl;
	private VuePlan vue;
	private int posX;
	private int posY;
	
	public EcouteurDeSouris(controleur.Controleur c, VuePlan v){
		ctrl = c;
		vue = v;
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
	
	@Override
	public void mousePressed(MouseEvent e) {
		posX = e.getX();
		posY = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){
			vue.move(e.getX()-posX, e.getY()-posY);
		}
		posX = e.getX();
		posY = e.getY();
	}

}
