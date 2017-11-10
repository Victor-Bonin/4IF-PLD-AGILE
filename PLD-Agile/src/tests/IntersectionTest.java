package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Intersection;

public class IntersectionTest {
	Intersection intersection;
	Intersection intersectionRef;
	
	@Before
	public void init() {
		intersection = new Intersection(100, 100, 001);
		intersectionRef = new Intersection(200, 200, 002);
	}

	@Test
	public void egalTest() {
		try {
			boolean egalite = intersection.equals(intersection);
			assertTrue(egalite);
		} catch (Exception e) {
			fail("Erreur egalTest cas égal");
		}
		try {
			boolean egalite = intersection.equals(intersectionRef);
			assertFalse(egalite);
		} catch (Exception e) {
			fail("Erreur egalTest cas inégal");
		}
	}

}
