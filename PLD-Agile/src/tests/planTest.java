package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Plan;

public class planTest {
	Plan plan = new Plan();

	@Before
	public void init() {
		plan.ajouterIntersection(56454, 2484, 4242);
		plan.ajouterIntersection(3542, 5612, 4343);
	}
	
	
	@Test
	public void ajouterTronconTest() {
		try {
			plan.ajouterTroncon(4242, 4343, 6666, "Rue des bolchévique");
		} catch (Exception e) {
			fail("Error " + e);
			e.printStackTrace();
		}
		assertEquals(2,2);
	}



}
