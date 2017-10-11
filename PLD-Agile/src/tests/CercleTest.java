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

public class CercleTest {
	
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
		Cercle c = new Cercle(PointFactory.creePoint(3, 3), 2);
		assert(c.contient(PointFactory.creePoint(3, 3)));
	}
	
	@Test
	public void contientTest2() {
		Cercle c = new Cercle(PointFactory.creePoint(3, 3), 2);
		assert(c.contient(PointFactory.creePoint(1, 1)));
	}
	
	@Test
	public void contientTest3() {
		Cercle c = new Cercle(PointFactory.creePoint(3, 3), 2);
		assert(c.contient(PointFactory.creePoint(2, 4)));
	}
	
	@Test
	public void contientTest4() {
		Cercle c = new Cercle(PointFactory.creePoint(3, 3), 2);
		assert(!c.contient(PointFactory.creePoint(6, 6)));
	}
	
	@Test
	public void deplaceTest() {
		int rayon = 2;
		Point centre = PointFactory.creePoint(3, 3);
		int deltaX = 4;
		int deltaY = 4;
		Cercle c = new Cercle(centre, rayon);
		c.addObserver(observer);
		c.deplace(deltaX, deltaY);
		assert(c.getCentre() == centre.deplace(deltaX, deltaY));
		assert(rayon == c.getRayon());
		assert(updateAppele);
	}
	
	@Test
	public void contenuDansTest1() {
		Cercle c = new Cercle(PointFactory.creePoint(3, 3), 2);
		assert(c.contenuDans(new Rectangle(PointFactory.creePoint(1, 1), 4, 4)));
	}
	
	@Test
	public void contenuDansTest2() {
		Cercle c = new Cercle(PointFactory.creePoint(3, 3), 2);
		assert(c.contenuDans(new Rectangle(PointFactory.creePoint(0, 1), 6, 7)));
	}
	
	@Test
	public void contenuDansTest3() {
		Cercle c = new Cercle(PointFactory.creePoint(3, 3), 2);
		assert(!c.contenuDans(new Rectangle(PointFactory.creePoint(2, 1), 4, 7)));
	}
	
	public void miseAJourTailleTest1() {
		Cercle c = new Cercle(PointFactory.creePoint(6, 8), 2);
		c.addObserver(observer);
		c.miseAJourTaille(PointFactory.creePoint(10, 8), plan);
		assert(c.getRayon() == 4);
		assert(updateAppele);
	}

	public void miseAJourTailleTest2() {
		Cercle c = new Cercle(PointFactory.creePoint(6, 8), 2);
		c.addObserver(observer);
		c.miseAJourTaille(PointFactory.creePoint(19, 18), plan);
		assert(c.getRayon() == 2);
		assert(!updateAppele);
	}

	@Test
	public void disjointTest1() {
		Cercle c1 = new Cercle(PointFactory.creePoint(4, 5), 2);
		Cercle c2 = new Cercle(PointFactory.creePoint(10, 12), 3);
		assert(c1.disjoint(c2));
		assert(c2.disjoint(c1));
	}

	@Test
	public void disjointTest2() {
		Cercle c1 = new Cercle(PointFactory.creePoint(4, 5), 2);
		Cercle c2 = new Cercle(PointFactory.creePoint(9, 5), 3);
		assert(!c1.disjoint(c2));
		assert(!c2.disjoint(c1));
	}

	@Test
	public void disjointTest3() {
		Cercle c = new Cercle(PointFactory.creePoint(4, 5), 2);
		Rectangle r = new Rectangle(PointFactory.creePoint(10, 12), 3, 4);
		assert(c.disjoint(r));
	}

	@Test
	public void disjointTest4() {
		Cercle c = new Cercle(PointFactory.creePoint(4, 5), 2);
		Rectangle r = new Rectangle(PointFactory.creePoint(6, 5), 3, 4);
		assert(!c.disjoint(r));
	}
}
