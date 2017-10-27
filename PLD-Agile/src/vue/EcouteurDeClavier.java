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
		int keycode = e.getKeyCode();
		if(e.isControlDown())
		{
			switch(keycode)
			{
			case KeyEvent.VK_Y:
				ctrl.redo();
				break;
			case KeyEvent.VK_Z:
				ctrl.undo();
				break;
			case KeyEvent.VK_P:
				ctrl.ouvrirPlan();
				break;
			case KeyEvent.VK_L:
				ctrl.ouvrirLivraison();
				break;
			case KeyEvent.VK_T:
				ctrl.calculerTournee();
				break;
			case KeyEvent.VK_N:
				ctrl.creerLivraison();
				break;
			}
		}
		if(keycode == KeyEvent.VK_ENTER)
		{
			ctrl.appuiEntree();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
