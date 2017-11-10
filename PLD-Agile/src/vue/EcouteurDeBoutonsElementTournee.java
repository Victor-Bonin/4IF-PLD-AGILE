package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe contient les appels au controleur depuis les actions sur un ElementTournee
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
public class EcouteurDeBoutonsElementTournee implements ActionListener{
	private controleur.Controleur ctrl;
	private ElementTourneeLivraison elemTournee;
	
	/**
	 * Constructeur d'une instance d'un écouteur
	 * @param c : le controleur sur lequel on appelera les actions
	 * @param el : l'element de la tournee associe a l'ecouteur
	 */
	public EcouteurDeBoutonsElementTournee(controleur.Controleur c, ElementTourneeLivraison el){
		ctrl = c;
		elemTournee = el;
	}
	
	/**
	 * Appelle les methodes du controleur a chaque action sur un bouton
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
			case "choisir-intersection":
				ctrl.commencerChoixIntersection();
				break;
			case "annuler-creation":
				ctrl.annulerCreation();
				break;
			case "valider-creation":
				elemTournee.setDuree();
				ctrl.ajouterLivraison(elemTournee.getLivraison(), elemTournee.getPosition());
				break;
			case "supprimer-livraison":
				ctrl.supprimerLivraison(elemTournee.getLivraison(), elemTournee.getPosition());
				break;
		}
		
	}

}
