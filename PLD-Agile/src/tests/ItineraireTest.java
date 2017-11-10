package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modele.Chemin;
import modele.Itineraire;

public class ItineraireTest {
	Itineraire itineraire;
	Itineraire itineraireRef;
	List<Chemin> chemins;
	List<Chemin> cheminsRef;
	Chemin cheminRef;

	@Before
	public void init() {
		chemins = new ArrayList<Chemin>();
		cheminsRef = new ArrayList<Chemin>();
		cheminRef = new Chemin(null, null);
		itineraire = new Itineraire(chemins);
		cheminsRef.add(cheminRef);
		itineraireRef = new Itineraire(cheminsRef);
	}


	@Test
	public void equalsTest() {
		try {
			boolean egalite = itineraire.equals(itineraire);
			assertTrue(egalite);
		} catch (Exception e) {
			fail("Erreur equalsTest cas égal");
		}
		try {
			boolean egalite = itineraire.equals(itineraireRef);
			assertFalse(egalite);
		} catch (Exception e) {
			fail("Erreur equalsTest cas inégal");
		}
	}
}
