package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

/**
 * <pre>
 * Cette classe contient les appels au controleur depuis la souris sur VuePlan
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
public class EcouteurDeSouris extends MouseAdapter implements MouseWheelListener{
	private controleur.Controleur ctrl;
	private VuePlan vue;
	private int posX;
	private int posY;
	
	/**
	 * Constructeur d'une instance d'un ecouteur de souris
	 * @param v : la vue du plan associee
	 */
	public EcouteurDeSouris(VuePlan v){
		vue = v;
	}
	
	/**
	 * Cree un zoom sur la vue plan a l'action sur la molette
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(MouseWheelEvent)
	 */
	public void mouseWheelMoved(MouseWheelEvent e){
		int cran = e.getWheelRotation();
        if (cran < 0) {
        	// Scroll vers le haut
        	this.vue.zoom();
        } else if (cran > 0) {
        	// Scroll vers le bas
        	this.vue.dezoom();
        } 
	}
	
	/**
	 * Ecoute le clic sur la souris pour connaitre l'origine du cliquer-glisser
	 * @see java.awt.event.MouseAdapter#mousePressed(MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		posX = e.getX();
		posY = e.getY();
	}
	
	/**
	 * Actualise la vue plan au cliquer-glisser de la souris
	 * @see java.awt.event.MouseAdapter#mouseDragged(MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){
			vue.move(e.getX()-posX, e.getY()-posY);
		}
		posX = e.getX();
		posY = e.getY();
	}

}
