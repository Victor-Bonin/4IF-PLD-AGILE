package tests;

import java.util.Observable;
import java.util.Observer;

import modele.Cercle;
import modele.Plan;
import modele.Point;
import modele.PointFactory;
import modele.Rectangle;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest {

	
	Plan plan;
	Observer observer;
	boolean updateAppele;
	
	@Before
	public void setUp(){
		updateAppele = false;
		plan = new Plan(20,20);
		observer = new Observer(){public void update(Observable o, Object arg){updateAppele = true;}};
	}
	
	@Test
	public void contientTest1() {
		Rectangle r = new Rectangle(PointFactory.creePoint(3, 3), 2, 4);
		assert(r.contient(PointFactory.creePoint(3, 3)));
	}
	
	@Test
	public void contientTest2() {
		Rectangle r = new Rectangle(PointFactory.creePoint(3, 3), 2, 4);
		assert(r.contient(PointFactory.creePoint(5, 7)));
	}
	
	@Test
	public void contientTest3() {
		Rectangle r = new Rectangle(PointFactory.creePoint(3, 3), 2, 4);
		assert(r.contient(PointFactory.creePoint(4, 6)));
	}
	
	@Test
	public void contientTest4() {
		Rectangle r = new Rectangle(PointFactory.creePoint(3, 3), 2, 4);
		assert(!r.contient(PointFactory.creePoint(6, 8)));
	}
	
	@Test
	public void deplaceTest() {
		int largeur = 2;
		int hauteur = 3;
		Point coin = PointFactory.creePoint(4, 6);
		int deltaX = -1;
		int deltaY = 2;
		Rectangle r = new Rectangle(coin, largeur, hauteur);
		r.addObserver(observer);
		r.deplace(deltaX, deltaY);
		assert(r.getCoin() == coin.deplace(deltaX, deltaY));
		assert(largeur == r.getLargeur());
		assert(hauteur == r.getHauteur());
		assert(updateAppele);
	}
	
	@Test
	public void contenuDansTest1() {
		Rectangle r = new Rectangle(PointFactory.creePoint(3, 3), 2, 4);
		assert(r.contenuDans(new Rectangle(PointFactory.creePoint(3, 3), 5, 4)));
	}
	
	@Test
	public void contenuDansTest2() {
		Rectangle r = new Rectangle(PointFactory.creePoint(3, 3), 2, 4);
		assert(r.contenuDans(new Rectangle(PointFactory.creePoint(0, 1), 16, 17)));
	}
	
	@Test
	public void contenuDansTest3() {
		Rectangle r = new Rectangle(PointFactory.creePoint(3, 3), 2, 4);
		assert(!r.contenuDans(new Rectangle(PointFactory.creePoint(2, 1), 4, 4)));
	}
	
	public void miseAJourTailleTest() {
		Rectangle r = new Rectangle(PointFactory.creePoint(13, 10), 2, 4);
		r.addObserver(observer);
		r.miseAJourTaille(13, 10, PointFactory.creePoint(10, 8), plan);
		assert(r.getLargeur() == 3);
		assert(r.getHauteur() == 2);
		assert(r.getCoin() == PointFactory.creePoint(10, 8));
		assert(updateAppele);
	}

	@Test
	public void disjointTest1() {
		Rectangle r1 = new Rectangle(PointFactory.creePoint(4, 5), 2, 4);
		Rectangle r2 = new Rectangle(PointFactory.creePoint(10, 12), 1, 3);
		assert(r1.disjoint(r2));
		assert(r2.disjoint(r1));
	}

	@Test
	public void disjointTest2() {
		Rectangle r1 = new Rectangle(PointFactory.creePoint(4, 5), 12, 14);
		Rectangle r2 = new Rectangle(PointFactory.creePoint(6, 12), 1, 3);
		assert(!r1.disjoint(r2));
		assert(!r2.disjoint(r1));
	}

	@Test
	public void disjointTest3() {
		Cercle c = new Cercle(PointFactory.creePoint(4, 5), 2);
		Rectangle r = new Rectangle(PointFactory.creePoint(10, 12), 3, 4);
		assert(r.disjoint(c));
	}

	@Test
	public void disjointTest4() {
		Cercle c = new Cercle(PointFactory.creePoint(4, 5), 2);
		Rectangle r = new Rectangle(PointFactory.creePoint(6, 5), 3, 4);
		assert(!r.disjoint(c));
	}
}
