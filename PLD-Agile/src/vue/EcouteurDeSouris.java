package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import controleur.Controleur;

import modele.Point;
import modele.PointFactory;

public class EcouteurDeSouris extends MouseAdapter {

	private Controleur controleur;
	private VueGraphique vueGraphique;
	private Fenetre fenetre;

	public EcouteurDeSouris(Controleur controleur, VueGraphique vueGraphique, Fenetre fenetre){
		this.controleur = controleur;
		this.vueGraphique = vueGraphique;
		this.fenetre = fenetre;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// Methode appelee par l'ecouteur de souris a chaque fois que la souris est cliquee
		// S'il s'agit d'un clic gauche dans la vue graphique, l'ecouteur envoie au controleur les coordonnees du point clique.
		// S'il s'agit d'un clic droit, l'ecouteur envoie le message d'echappement au controleur
		switch (evt.getButton()){
		case MouseEvent.BUTTON1: 
			Point p = coordonnees(evt);
			if (p != null)
				controleur.clicGauche(p); 
			break;
		case MouseEvent.BUTTON3: 
			controleur.clicDroit(); 
			break;
		default:
		}
	}

	public void mouseMoved(MouseEvent evt) {
		// Methode appelee a chaque fois que la souris est bougee
		// Envoie au controleur les coordonnees de la souris.
		Point p = coordonnees(evt);
		if (p != null)
			controleur.sourisBougee(p); 
	}
	
	private Point coordonnees(MouseEvent evt){
		MouseEvent e = SwingUtilities.convertMouseEvent(fenetre, evt, vueGraphique);
		int x = Math.round((float)e.getX()/(float)vueGraphique.getEchelle());
		int y = Math.round((float)e.getY()/(float)vueGraphique.getEchelle());
		return PointFactory.creePoint(x, y);
	}


}
