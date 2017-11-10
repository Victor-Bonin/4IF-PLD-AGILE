package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe contient les appels aux fonctions de synchronisation depuis la souris pour synchroniser les icones
 * de la vue plan et celles de la vue tournee
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
public class EcouteurDeSourisDeSynchronisation extends MouseAdapter {
	int place;
	VuePlan vuePlan;
	VueTournee vueTournee;
	
	/**
	 * Constructeur d'une instance d'un écouteur de souris
	 * @param p : position de l'element survole dans la liste d'elements de la tournee
	 * @param vp : vue plan associee a l'ecouteur
	 * @param vt : vue tournee associee a l'ecouteur
	 */
	public EcouteurDeSourisDeSynchronisation(int p, VuePlan vp, VueTournee vt){
		place = p;
		vuePlan = vp;
		vueTournee = vt;
	}
	
	/**
	 * Indique le survol d'une livraison aux vues plan et tournee
	 * @see java.awt.event.MouseAdapter#mouseEntered(MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e){
		vuePlan.survol(place);
		vueTournee.survol(place);
	}
	
	/**
	 * Indique le de-survol d'un livraison aux vues plan et tournee
	 * @see java.awt.event.MouseAdapter#mouseExited(MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e){
		vuePlan.antiSurvol(place);
		vueTournee.antiSurvol(place);
		
	}
	
	/**
	 * Au clic sur une livraison, affiche ses details dans la vue tournee
	 * @see java.awt.event.MouseAdapter#mousePressed(MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1) {
			vueTournee.afficherDetails(place);
		}
	}


}
