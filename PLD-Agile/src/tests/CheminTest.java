package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Chemin;
import modele.Intersection;
import modele.Troncon;

public class CheminTest {
	Chemin chemin;
	Chemin cheminRef;
	Intersection depart, arrivee, intermediaire, etape;
	final int INDEX = 0;
	Troncon troncon, troncon2;

	@Before
	public void init() {
		depart = new Intersection(10,50,123);
		arrivee = new Intersection(20,40,234);
		intermediaire = new Intersection(20,40,234);
		etape = new Intersection(10,20,245);
		chemin = new Chemin(depart, arrivee);
		cheminRef = new Chemin(depart, arrivee);
		troncon = new Troncon(intermediaire, etape);
		troncon2 = new Troncon(etape, arrivee);
	}


	@Test
	public void ajouterTronconTest() {
		try {
			chemin.ajouterTroncon(INDEX, troncon);
			assertEquals(troncon, chemin.getTroncons().get(INDEX));
		} catch (Exception e) {
			fail("Erreur ajouterTronconTest cas normal");
		}
	}
	
	@Test
	public void equalsTest() {
		try {
			boolean egalite = chemin.equals(chemin);
			assertTrue(egalite);
		} catch (Exception e) {
			fail("Erreur equalsTest cas égal");
		}
		chemin.ajouterTroncon(0, troncon);
		chemin.ajouterTroncon(1, troncon2);
		try {
			boolean egalite = chemin.equals(cheminRef);
			assertFalse(egalite);
		} catch (Exception e) {
			fail("Erreur equalsTest cas inégal");
		}
	}
}
