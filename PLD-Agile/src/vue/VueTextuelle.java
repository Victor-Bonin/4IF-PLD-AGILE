package vue;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import modele.Cercle;
import modele.Forme;
import modele.Plan;
import modele.Rectangle;
import modele.VisiteurPourAfficherFormes;

public class VueTextuelle extends JLabel implements Observer, VisiteurPourAfficherFormes{

	private String texte;
	private Plan plan;

	/**
	 * Cree une vue textuelle de plan dans fenetre
	 * @param plan
	 * @param fenetre
	 */
	public VueTextuelle(Plan plan, Fenetre fenetre){
		super();
		setBorder(BorderFactory.createTitledBorder("Liste des formes :"));
		this.setVerticalTextPosition(TOP);
		this.setVerticalAlignment(TOP);
		fenetre.getContentPane().add(this);
		plan.addObserver(this); // this observe plan
		this.plan = plan;
	}
	
	/**
	 * Methode appelee par les objets observes par this a chaque fois qu'ils ont ete modifies
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null){ // arg est une forme qui vient d'etre ajoutee a plan
			Forme f = (Forme)arg;
			f.addObserver(this);
		}
		Iterator<Forme> it = plan.getIterateurFormes();
		texte = "<html><ul>";
		while (it.hasNext())
			it.next().affiche(this);
		texte = texte+"</ul></html>";
		setText(texte);
	}


	/**
	 * Methode appelee par l'objet visite (un cercle) a chaque fois qu'il recoit le message affiche
	 */
	@Override
	public void affiche(Cercle c) {
		texte = texte+"<li>";
		if (c.getEstSelectionne())
			texte = texte+"<b><i>";
		texte = texte+"Cercle : centre=(" + c.getCentre().getX() 
				+ "," + c.getCentre().getY()
				+ ") rayon=" + c.getRayon();
		if (c.getEstSelectionne())
				texte = texte+"</i></b>";
		texte = texte+"</li>";
	}
	
	/**
	 * Methode appelee par l'objet visite (un rectangle) a chaque fois qu'il recoit le message affiche
	 */
	@Override
	public void affiche(Rectangle r) {
		texte = texte+"<li>";
		if (r.getEstSelectionne())
			texte = texte+"<b><i>";
		texte = texte+"Rectangle : coin=(" + r.getCoin().getX() 
				+ "," + r.getCoin().getY() + ") largeur="
				+ r.getLargeur() + " hauteur=" + r.getHauteur();
		if (r.getEstSelectionne())
			texte = texte+"</i></b>";
		texte = texte+"</li>";
	}


}
