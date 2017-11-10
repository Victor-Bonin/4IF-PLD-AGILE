package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <pre>
 * Cette classe contient les appels au controleur depuis les boutons
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
public class EcouteurDeBouton implements ActionListener{
	private controleur.Controleur ctrl;
	
	/**
	 * Constructeur d'une instance d'un écouteur
	 * @param c le controleur sur lequel on appelera les actions
	 */
	public EcouteurDeBouton(controleur.Controleur c){
		ctrl = c;
	}

	/**
	 * Appelle les methodes du controleur a chaque action sur un bouton
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
			case "import-plan":
				ctrl.ouvrirPlan();
				break;
			case "import-demande-livraison":
				ctrl.nettoyerNouvelleLivraison();
				ctrl.ouvrirLivraison();
				break;
			case "export-feuille":
				ctrl.exporterFeuilleDeRoute();
				break;
			case "calcul-tournee":
				ctrl.calculerTournee();
				break;
			case "nouvelle-livraison":
				ctrl.creerLivraison();
				break;
			case "defaire_action":
				ctrl.undo();
				break;
			case "refaire_action":
				ctrl.redo();
				break;
		}
		
	}

}
