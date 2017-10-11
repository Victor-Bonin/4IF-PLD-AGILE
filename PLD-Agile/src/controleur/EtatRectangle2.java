package controleur;
import vue.Fenetre;
import modele.Plan;
import modele.Point;
import modele.Rectangle;

public class EtatRectangle2 extends EtatDefaut {
	// Etat atteint a partir de EtatRectangle1 a la reception de l'evenement clicGauche(p)
	// -> actionEntree cree le rectangle de coin superieur gauche p, de largeur 1 et de hauteur 1
	// -> Mise a jour de rectangle a chaque fois que la souris est bougee, de sorte que la position de la souris corresponde a l'angle oppose a p
	// -> Mise a jour de rectangle quand un deuxieme point est saisi, puis retour dans EtatInit

	private Rectangle rectangle;
	private int x;
	private int y;
	
	@Override
	public void clicGauche(Controleur controleur, Fenetre fenetre, Plan plan, ListeDeCdes listeDeCdes, Point p) {
		rectangle.setEstSelectionne(false);
		rectangle.miseAJourTaille(x, y, p, plan);
		if ((rectangle.getLargeur() == 0) || (rectangle.getHauteur() == 0)) 
			listeDeCdes.annule();
		fenetre.afficheMessage("");
		fenetre.autoriseBoutons(true);
		controleur.setEtatCourant(controleur.etatInit);
	}
	
	@Override
	public void sourisBougee(Plan plan, Point p){
		rectangle.miseAJourTaille(x, y, p, plan);
	}
	
	@Override
	public void clicDroit(Controleur controleur, Fenetre fenetre, ListeDeCdes listeDeCdes) {
		listeDeCdes.annule(); // Le rectangle en cours de saisie est definitivement enleve de listeDeCdes
		super.clicDroit(controleur, fenetre, listeDeCdes);
	}

	protected void actionEntree(Point p, Plan plan, ListeDeCdes listeDeCdes) {
		rectangle = new Rectangle(p, 1, 1);
		rectangle.setEstSelectionne(true);
		x = rectangle.getCoin().getX();
		y = rectangle.getCoin().getY();
		listeDeCdes.ajoute(new CdeAjout(plan, rectangle));
	}

}
