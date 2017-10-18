package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class EcouteurDeSouris extends MouseAdapter implements MouseWheelListener{
	private controleur.Controleur ctrl;
	private Fenetre fenetre;
	
	public EcouteurDeSouris(controleur.Controleur c, Fenetre f){
		ctrl = c;
		fenetre = f;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e){
		int cran = e.getWheelRotation();
        if (cran < 0) {
        	// Scroll haut
        	this.fenetre.zoom();
        } else if (cran > 0) {
        	// Scroll bas
        	this.fenetre.dezoom();
        } 
	}

}
