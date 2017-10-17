package vue;

import java.awt.event.MouseAdapter;

public class EcouteurDeSouris extends MouseAdapter {
	private controleur.Controleur ctrl;
	
	public EcouteurDeSouris(controleur.Controleur c){
		ctrl = c;
	}

}
