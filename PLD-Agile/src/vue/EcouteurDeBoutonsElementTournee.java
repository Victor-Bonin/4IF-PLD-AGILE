package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe contient les appels au controleur depuis les boutons
 * 
 * @author 4104
 *
 */
public class EcouteurDeBoutonsElementTournee implements ActionListener{
	private controleur.Controleur ctrl;
	private ElementTourneeLivraison elemTournee;
	
	/**
	 * Constructeur d'une instance d'un écouteur
	 * @param c le controleur sur lequel on appelera les actions
	 */
	public EcouteurDeBoutonsElementTournee(controleur.Controleur c, ElementTourneeLivraison el){
		ctrl = c;
		elemTournee = el;
	}

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
