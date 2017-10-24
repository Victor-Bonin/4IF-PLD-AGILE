package vue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Cette classe contient les appels au controleur depuis le clavier
 * 
 * @author 4104
 *
 */
public class EcouteurDeClavier implements KeyListener{
	private controleur.Controleur ctrl;
	
	/**
	 * Constructeur d'une instance d'un écouteur
	 * @param c le controleur sur lequel on appelera les actions
	 */
	public EcouteurDeClavier(controleur.Controleur c){
		ctrl = c;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
