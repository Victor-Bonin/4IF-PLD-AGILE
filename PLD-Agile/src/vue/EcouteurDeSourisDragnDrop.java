package vue;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <pre>
 * Cette classe contient les appels aux fonctions de drag and drop sur la vue tournee pour les livraisons
 * 
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o . . o.  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  '==="  /|
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
 * </pre>
 * 
 * 
 * @author 4104
 */
public class EcouteurDeSourisDragnDrop extends MouseAdapter {
	
	VueTournee vueTournee;
	
	/**
	 * Constructeur d'une instance d'un écouteur de souris
	 * @param vue : vue tournee associee a l'ecouteur
	 */
	public EcouteurDeSourisDragnDrop(VueTournee vue){
		vueTournee = vue;
	}

	/**
	 * Commence le drag and drop au clic de la souris et affiche le menu contextuel de la vue tournee en cas de clic 
	 * droit (Mac)
	 * @see java.awt.event.MouseAdapter#mousePressed(MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		ElementTourneeLivraison elemt = ((ElementTourneeLivraison)e.getSource());
		vueTournee.dragCommencer(elemt);
		if (e.isPopupTrigger()) {
			elemt.showMenu(e.getComponent(), e.getX(), e.getY());
		}
	}
	
	/**
	 * Indique le survol d'une livraison a la vue tournee et change le curseur de la souris en une main
	 * @see java.awt.event.MouseAdapter#mouseEntered(MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		vueTournee.dragIn((ElementTourneeLivraison)e.getSource());
		vueTournee.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Indique le de-survol d'une livraison au la vue tournee
	 * @see java.awt.event.MouseAdapter#mouseExited(MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		vueTournee.dragOut((ElementTourneeLivraison)e.getSource());
	}
	
	/**
	 * Termine le drag and drop au lacher du clic et affiche le menu contextuel de la vue tournee en cas de clic 
	 * droit (Windows)
	 * @see java.awt.event.MouseAdapter#mouseReleased(MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		ElementTourneeLivraison elemt = ((ElementTourneeLivraison)e.getSource());
		vueTournee.stopDrag(elemt);
		if (e.isPopupTrigger()) {
			elemt.showMenu(e.getComponent(), e.getX(), e.getY());
		}
	}
		
}
