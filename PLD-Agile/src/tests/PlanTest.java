package tests;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import modele.Cercle;
import modele.Plan;
import modele.PointFactory;
import modele.Rectangle;

import org.junit.Before;
import org.junit.Test;

public class PlanTest {

	Observer observer;
	boolean updateAppele;
	
	@Before
	public void setUp(){
		updateAppele = false;
		observer = new Observer(){public void update(Observable o, Object arg){updateAppele = true;}};
	}
	
	@Test
	public void ajouteTest(){
		Plan plan = new Plan(20,20);
		plan.addObserver(observer);
		Cercle c = new Cercle(PointFactory.creePoint(4, 6),2);
		plan.ajoute(c);	
		assert(updateAppele);
	}
	
	@Test
	public void supprimeTest(){
		Plan plan = new Plan(20,20);
		plan.addObserver(observer);
		Cercle c = new Cercle(PointFactory.creePoint(4, 6),2);
		plan.ajoute(c);	
		updateAppele = false;
		plan.supprime(c);
		assert(updateAppele);
		assertNull(plan.cherche(PointFactory.creePoint(4,6)));
	}

	@Test
	public void chercheTest1(){
		Plan plan = new Plan(20,20);
		Cercle c = new Cercle(PointFactory.creePoint(4, 6),2);
		plan.ajoute(c);
		assert(plan.cherche(PointFactory.creePoint(5, 5)) == c);
	}
		
	@Test
	public void chercheTest2(){
		Plan plan = new Plan(20,20);
		Cercle c = new Cercle(PointFactory.creePoint(4, 6),2);
		plan.ajoute(c);
		assertNull(plan.cherche(PointFactory.creePoint(3, 15)));
	}

	@Test
	public void contientTest1(){
		Plan plan = new Plan(20,20);
		Cercle c = new Cercle(PointFactory.creePoint(4, 6),2);
		assert(plan.contient(c));
	}

	@Test
	public void contientTest2(){
		Plan plan = new Plan(20,20);
		Cercle c = new Cercle(PointFactory.creePoint(12, 16),15);
		assert(!plan.contient(c));
	}

	@Test
	public void tousDisjointsTest1(){
		Plan plan = new Plan(20,20);
		plan.ajoute(new Cercle(PointFactory.creePoint(12, 16),3));
		plan.ajoute(new Rectangle(PointFactory.creePoint(3,2), 1, 4));
		assert(plan.tousDisjoints(new Cercle(PointFactory.creePoint(8, 9),1)));
	}
	
	@Test
	public void tousDisjointsTest2(){
		Plan plan = new Plan(20,20);
		plan.ajoute(new Cercle(PointFactory.creePoint(12, 16),3));
		plan.ajoute(new Rectangle(PointFactory.creePoint(3,2), 1, 4));
		assert(!plan.tousDisjoints(new Cercle(PointFactory.creePoint(8, 9),11)));
	}
	
	@Test
	public void resetTest() {
		Plan plan = new Plan(20,20);
		Cercle c = new Cercle(PointFactory.creePoint(12, 16),3);
		plan.ajoute(c);
		Rectangle r = new Rectangle(PointFactory.creePoint(3,2), 1, 4);
		plan.ajoute(r);
		updateAppele = false;
		plan.reset(10, 10);
		assert(updateAppele);
		assertNull(plan.cherche(PointFactory.creePoint(3,2)));
		assert(!plan.contient(r));
		assertNull(PointFactory.creePoint(15, 15));
	}

}
