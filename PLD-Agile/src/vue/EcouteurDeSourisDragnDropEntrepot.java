package vue;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe contient les appels aux fonctions de drag and drop sur la vue tournee pour l'entrepot
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
public class EcouteurDeSourisDragnDropEntrepot extends MouseAdapter {
	
	VueTournee vueTournee;
	
	/**
	 * Constructeur d'une instance d'un écouteur de souris
	 * @param vue : vue tournee associee a l'ecouteur
	 */
	public EcouteurDeSourisDragnDropEntrepot(VueTournee vue){
		vueTournee = vue;
	}
	
	/**
	 * Commence le drag and drop au clic de la souris et affiche le menu contextuel de la vue tournee en cas de clic 
	 * droit (Mac)
	 * @see java.awt.event.MouseAdapter#mousePressed(MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			((ElementTourneeEntrepot)e.getSource()).showMenu(e.getComponent(), e.getX(), e.getY());
		}
	}
	
	/**
	 * Indique le survol d'un entrepot a la vue tournee et change le curseur de la souris en une main
	 * @see java.awt.event.MouseAdapter#mouseEntered(MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		vueTournee.dragIn((ElementTourneeEntrepot)e.getSource());
		vueTournee.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * Indique le de-survol d'un entrepot au la vue tournee
	 * @see java.awt.event.MouseAdapter#mouseExited(MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		vueTournee.dragOut((ElementTourneeEntrepot)e.getSource());
	}
	
	/**
	 * Termine le drag and drop au lacher du clic et affiche le menu contextuel de la vue tournee en cas de clic 
	 * droit (Windows)
	 * @see java.awt.event.MouseAdapter#mouseReleased(MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			((ElementTourneeEntrepot)e.getSource()).showMenu(e.getComponent(), e.getX(), e.getY());
		}
	}
		
}
