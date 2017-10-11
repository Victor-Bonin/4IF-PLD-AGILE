package tests;

import modele.Plan;
import modele.Point;
import modele.PointFactory;

import org.junit.Before;
import org.junit.Test;

public class PointTest {

	Plan plan;
		
	@Before
	public void setUp(){
		plan = new Plan(20,20);
	}

	@Test
	public void deplaceTest() {
		Point p1 = PointFactory.creePoint(4, 8);
		Point p2 = p1.deplace(2, 3);
		assert(p2.getX() == p1.getX()+2);
		assert(p2.getY() == p1.getY()+3);
	}
	
	@Test
	public void distanceTest1(){
		Point p1 = PointFactory.creePoint(4, 8);
		Point p2 = p1.deplace(2, 0);
		assert(p1.distance(p2) == 2);
	}

	@Test
	public void distanceTest2(){
		Point p1 = PointFactory.creePoint(1, 2);
		Point p2 = p1.deplace(4, 3);
		assert(p1.distance(p2) == 5);
	}

	@Test
	public void distanceTest3(){
		Point p1 = PointFactory.creePoint(4, 8);
		Point p2 = p1.deplace(0, 4);
		assert(p1.distance(p2) == 4);
	}

}
