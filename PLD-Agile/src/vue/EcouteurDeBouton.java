package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Cette classe contient les appels au controleur depuis les boutons
 * 
 * @author 4104
 *
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

	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
			case "import-plan":
				ctrl.ouvrirPlan();
				break;
			case "import-demande-livraison":
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
			case "choisir-intersection":
				ctrl.commencerChoixIntersection();
				break;
			case "annuler-creation":
				break;
			case "valider-creation":
				break;
		}
		
	}

}
