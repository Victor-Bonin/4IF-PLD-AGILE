package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modele.Entrepot;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Tournee;

public class TourneeTest {
	Tournee tournee;
	Entrepot entrepot;
	Intersection inter;
	Itineraire itineraire;
	List<Livraison> livraisons;
	// List<Chemin>
	
	@Before
	public void init () {
		entrepot = new Entrepot(inter, null);
		itineraire = new Itineraire(null);
		livraisons = new ArrayList<Livraison>();
		tournee = new Tournee(entrepot, livraisons, itineraire);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
