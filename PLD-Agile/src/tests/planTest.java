package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Intersection;
import modele.Plan;
import modele.Troncon;

public class planTest {
	Plan plan = new Plan();
	Intersection inter1;
	Intersection inter2;
	Troncon tronconExpected;

	@Before
	public void init() {		
	plan.ajouterIntersection(56454, 2484, 4242);
	plan.ajouterIntersection(3542, 5612, 4343);
	inter1 = plan.getIntersections().get(4242L);
	inter2 = plan.getIntersections().get(4343L);
	tronconExpected = new Troncon(inter1, inter2, "Rue des bolchéviques", (float)6666);
	}
	

	@Test
	public void ajouterTronconTest() {
		try {
			plan.ajouterTroncon(4242, 4343, (float)6666, "Rue des bolchéviques");
		} catch (Exception e) {
			fail("Error " + e);
			e.printStackTrace();
		}
		Troncon tronconAjoute = plan.getTroncons().get(plan.getTroncons().size()-1);
		assertEquals(tronconExpected.getDebut(),tronconAjoute.getDebut());
		assertEquals(tronconExpected.getFin(),tronconAjoute.getFin());
		assertTrue(tronconExpected.getLongueur() == tronconAjoute.getLongueur());
  	}



}
