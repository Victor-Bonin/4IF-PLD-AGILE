package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurDeBouton implements ActionListener{
	private controleur.Controleur ctrl;
	
	public EcouteurDeBouton(controleur.Controleur c){
		ctrl = c;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
			case "import-plan":
				ctrl.ouvrirPlan();
				break;
			case "export-feuille":
				ctrl.exporterFeuilleDeRoute();
				break;
			case "import-demande-livraison":
				ctrl.ouvrirLivraison();
				break;
		}
		
	}

}
