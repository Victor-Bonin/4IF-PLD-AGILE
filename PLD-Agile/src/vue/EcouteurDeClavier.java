package vue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Cette classe contient les appels au controleur depuis le clavier
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
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
 * 
 * 
 * @author 4104
 */
public class EcouteurDeClavier implements KeyListener{
	private controleur.Controleur ctrl;
	
	/**
	 * Constructeur d'une instance d'un écouteur de clavier
	 * @param c le controleur sur lequel on appelera les actions
	 */
	public EcouteurDeClavier(controleur.Controleur c){
		ctrl = c;
	}
	
	/**
	 * Appelle les methodes du controleur a chaque action sur un bouton
	 * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
	 */
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
			case KeyEvent.VK_E:
				ctrl.exporterFeuilleDeRoute();
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
