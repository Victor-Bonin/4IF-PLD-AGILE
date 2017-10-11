package vue;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import modele.Cercle;
import modele.Plan;
import modele.Forme;
import modele.Rectangle;
import modele.VisiteurPourAfficherFormes;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class VueGraphique extends JPanel implements Observer, VisiteurPourAfficherFormes{

	private int echelle;
	private int hauteurVue;
	private int largeurVue;
	private Plan plan;
	private Graphics g;

	/**
	 * Cree la vue graphique permettant de dessiner plan avec l'echelle e dans la fenetre f
	 * @param plan
	 * @param e l'echelle
	 * @param f la fenetre
	 */
	public VueGraphique(Plan plan, int e, Fenetre f) {
		super();
		plan.addObserver(this); // this observe plan
		this.echelle = e;
		hauteurVue = plan.getHauteur()*e;
		largeurVue = plan.getLargeur()*e;
		setLayout(null);
		setBackground(Color.white);
		setSize(largeurVue, hauteurVue);
		f.getContentPane().add(this);
		this.plan = plan;
	}
	
	/**
	 * Methode appelee a chaque fois que VueGraphique doit etre redessinee
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.lightGray);
		for (int y=0; y<largeurVue/echelle; y++)
			g.drawLine(y*echelle, 0, y*echelle, hauteurVue);
		for (int x=0; x<hauteurVue/echelle; x++)
			g.drawLine(0, x*echelle, largeurVue, x*echelle);
		g.setColor(Color.gray);
		g.drawRect(0, 0, largeurVue, hauteurVue);
		this.g = g;
		Iterator<Forme> it = plan.getIterateurFormes();
		while (it.hasNext())
			it.next().affiche(this);

	}

	public void setEchelle(int e) {
		largeurVue = (largeurVue/echelle)*e;
		hauteurVue = (hauteurVue/echelle)*e;
		setSize(largeurVue, hauteurVue);
		echelle = e;
	}

	public int getEchelle() {
		return echelle;
	}

	public int getHauteur() {
		return hauteurVue;
	}

	public int getLargeur() {
		return largeurVue;
	}

	/**
	 * Methode appelee par les objets observes par this a chaque fois qu'ils ont ete modifies
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null){ // arg est une forme qui vient d'etre ajoutee a plan
			Forme f = (Forme)arg;
			f.addObserver(this);  // this observe la forme f
		}
		repaint();
	}

	/**
	 * Methode appelee par l'objet visite (un cercle) a chaque fois qu'il recoit le message affiche
	 */
	@Override
	public void affiche(Cercle c) {
		int r = echelle*c.getRayon();
		if (c.getEstSelectionne())
			g.drawOval(echelle*c.getCentre().getX()-r, echelle*c.getCentre().getY()-r, 2*r, 2*r);
		else
			g.fillOval(echelle*c.getCentre().getX()-r, echelle*c.getCentre().getY()-r, 2*r, 2*r);
	}

	/**
	 * Methode appelee par l'objet visite (un rectangle) a chaque fois qu'il recoit le message affiche
	 */
	@Override
	public void affiche(Rectangle r) {
		if (r.getEstSelectionne())
			g.drawRect(echelle*r.getCoin().getX(),echelle*r.getCoin().getY(),echelle*(r.getLargeur()),echelle*(r.getHauteur()));
		else
			g.fillRect(echelle*r.getCoin().getX(),echelle*r.getCoin().getY(),echelle*(r.getLargeur()),echelle*(r.getHauteur()));
	}

}
