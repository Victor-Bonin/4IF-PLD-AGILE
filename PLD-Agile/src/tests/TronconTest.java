package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Intersection;
import modele.Troncon;

public class TronconTest {
	Troncon troncon;
	Troncon tronconRef;
	Intersection depart;
	Intersection arrivee;
	Intersection arriveeRef;

	@Before
	public void init() {
		depart = new Intersection(1, 1, 1);
		arrivee = new Intersection(2, 2, 2);
		arriveeRef = new Intersection(3, 3, 3);
		troncon = new Troncon(depart, arrivee);
		tronconRef = new Troncon(depart, arriveeRef);
	}

	@Test
	public void equalsTronconTest() {
		try {
			boolean egalite = troncon.equals(troncon);
			assertTrue(egalite);
		} catch (Exception e) {
			fail("Erreur equalsTest cas égal");
		}
		try {
			boolean egalite = troncon.equals(tronconRef);
			assertFalse(egalite);
		} catch (Exception e) {
			fail("Erreur equalsTest cas inégal");
		}
	}

}
