package vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controleur.Controleur;

public class EcouteurDeClavier extends KeyAdapter {

	private Controleur controleur;

	public EcouteurDeClavier(Controleur controleur){
		this.controleur = controleur;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// Methode appelee par l'ecouteur de clavier a chaque fois qu'une touche est frappee
		controleur.caractereSaisi(e.getKeyCode());
	}

}
