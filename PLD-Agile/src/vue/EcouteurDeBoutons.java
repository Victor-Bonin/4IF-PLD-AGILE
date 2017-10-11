package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.Controleur;

public class EcouteurDeBoutons implements ActionListener {
	
	private Controleur controleur;

	public EcouteurDeBoutons(Controleur controleur){
		this.controleur = controleur;
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		// Methode appelee par l'ecouteur de boutons a chaque fois qu'un bouton est clique
		// Envoi au controleur du message correspondant au bouton clique
		switch (e.getActionCommand()){
		case Fenetre.AJOUTER_CERCLE: controleur.ajouterCercle(); break;
		case Fenetre.AJOUTER_RECTANGLE: controleur.ajouterRectangle(); break;
		case Fenetre.SUPPRIMER: controleur.supprimer(); break;
		case Fenetre.SAUVER: controleur.sauver(); break;
		case Fenetre.OUVRIR: controleur.ouvrir(); break;
		case Fenetre.UNDO: controleur.undo(); break;
		case Fenetre.REDO: controleur.redo(); break;
		case Fenetre.DEPLACER: controleur.deplacer();break;
		case Fenetre.DIMINUER_ECHELLE: controleur.diminuerEchelle(); break;
		case Fenetre.AUGMENTER_ECHELLE: controleur.augmenterEchelle(); break;
		}
	}
}
