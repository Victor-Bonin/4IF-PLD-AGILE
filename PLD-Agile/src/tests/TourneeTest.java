package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import modele.Entrepot;
import modele.ExceptionPlanCo;
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
	String feuilleDeRoute;
	// List<Chemin>
	
	@Before
	public void init () {
		entrepot = new Entrepot(inter, null);
		itineraire = new Itineraire(null);
		livraisons = new ArrayList<Livraison>();
		tournee = new Tournee(entrepot, livraisons, itineraire);
		feuilleDeRoute = "Je suis la feuille de route";
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
		assert(tournee.getFeuilleDeRoute().equals(truc));
	}
	
	@Test
	public void creerFeuilleDeRouteTest() {
		try {
			tournee.creerFeuilleDeRoute(feuilleDeRoute);
			assertThat();

		} catch (IOException | ExceptionPlanCo e) {
			e.printStackTrace();
		}
	}

}
