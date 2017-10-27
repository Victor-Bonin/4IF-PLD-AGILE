package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import modele.Chemin;
import modele.Entrepot;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

public class planTest {
	Plan plan = new Plan();
	Intersection inter1;
	Intersection inter2;
	Troncon tronconExpected;
	Tournee tourneeExpected;

	@Before
	public void init() {		
		plan.ajouterIntersection(56454, 2484, 4242);
		plan.ajouterIntersection(3542, 5612, 4343);
		inter1 = plan.getIntersections().get(4242L);
		inter2 = plan.getIntersections().get(4343L);
		tronconExpected = new Troncon(inter1, inter2, "Rue des bolchéviques", (float)6666);
	}


	@Test
	public void calculerTourneeTest(){
		File planTest = new File("assets/planTest.xml");
		File dlTest = new File("assets/DLTest.xml");
		try{
			DeserialiseurXML.chargerFichier(plan, planTest);
			DeserialiseurXML.chargerDemandeLivraisonFichier(plan, dlTest);
			plan.calculTournee();
		} catch (Exception e) {
			fail("Error " + e);
			e.printStackTrace();
		}
		Tournee tournee = plan.getTournee();
		
		Entrepot entrepotTest = plan.getDemandeLivraison().getEntrepot();
		Intersection interTest1 = plan.getIntersections().get(1L);
		Intersection interTest2 = plan.getIntersections().get(2L);
		Intersection interTest3 = plan.getIntersections().get(3L);
		Intersection interTest4 = plan.getIntersections().get(4L);
		Intersection interTest5 = plan.getIntersections().get(5L);
		
		List<Livraison> solutionExpected = new ArrayList<Livraison>();
		solutionExpected.add((Livraison)(Intersection)entrepotTest);
		solutionExpected.add((Livraison)interTest3);
		solutionExpected.add((Livraison)interTest5);
		
		List<Chemin> cheminExpected = new ArrayList<Chemin>();
		cheminExpected.add(new Chemin(interTest1,interTest2));
		cheminExpected.add(new Chemin(interTest2,interTest3));
		cheminExpected.add(new Chemin(interTest3,interTest5));
		cheminExpected.add(new Chemin(interTest5,interTest4));
		cheminExpected.add(new Chemin(interTest4,interTest3));
		cheminExpected.add(new Chemin(interTest3,interTest1));
		Itineraire itineraireExpected = new Itineraire(cheminExpected);
		
		tourneeExpected = new Tournee(plan.getDemandeLivraison().getEntrepot() , solutionExpected, itineraireExpected);
		
		
		assertEquals(tournee, tourneeExpected);
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

	@Test
	public void listerTronconVoisinPetit() {
		File xml = new File("assets/planLyonPetit.xml");
		try {
			DeserialiseurXML.chargerFichier(plan, xml);
			assertEquals(217, plan.getIntersections().values().size());
		}
		catch (Exception e) {
			fail("Erreur listerTronconVoisinPetit parse XML");
		}
		List<Troncon> troncons = plan.listerTronconVoisin(1029591870L);
		assertEquals(3, troncons.size());
	}
	
	//@Test
	public void listerTronconVoisinGrand() {
		File xml = new File("assets/planLyonGrand.xml");
		try {
			DeserialiseurXML.chargerFichier(plan, xml);
			assertEquals(12165, plan.getIntersections().values().size());
		}
		catch (Exception e) {
			fail("Erreur listerTronconVoisinGrand parse XML");
		}
		// 3.6 s
		List<Troncon> troncons = plan.listerTronconVoisin(998859048L);
		assertEquals(5, troncons.size());
		// 3.7s
		troncons = plan.listerTronconVoisin(100218027L);
		assertEquals(6, troncons.size());
		// 3.8s
		for(Intersection i : plan.getIntersections().values())
			plan.listerTronconVoisin(i.getId());
		// 8s
	}
	
	@Test
	public void obtenirVoisinParIntersectionTest() {
		File xml = new File("assets/planLyonGrand.xml");
		try {
			DeserialiseurXML.chargerFichier(plan, xml);
			assertEquals(12165, plan.getIntersections().values().size());
		}
		catch (Exception e) {
			fail("Erreur obtenirVoisinParIntersectionTest parse XML");
		}
		HashMap<Long, List<Troncon>> croisement = 
				plan.obtenirVoisinParIntersection();
		assertEquals(5, croisement.get(998859048L).size());
		assertEquals(6, croisement.get(100218027L).size());
	}

}
