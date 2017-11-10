package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import modele.Chemin;
import modele.Entrepot;
import modele.ExceptionPlanCo;
import modele.Intersection;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;
import xml.DeserialiseurXML;

/**
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
 *             \      .: /|\
 *             )\_   .: / |:"--___
 *         __-:|\ """ _-  |:::::::
 *       _-::::\ "-_.-   /::::::::
 *    _--:::::::| .|"-_  |::::::::
 *  -"::::::::::\  | { -_|::::::::
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr
 * 
 * 
 * @author 4104
 */
public class PlanTest {
	Plan plan;
	Intersection inter1;
	Intersection inter2;
	Troncon tronconExpected;
	Tournee tourneeExpected;

	@Before
	public void init() {		
		plan = new Plan();
		plan.ajouterIntersection(56454, 2484, 4242);
		plan.ajouterIntersection(3542, 5612, 4343);
		inter1 = plan.getIntersections().get(4242L);
		inter2 = plan.getIntersections().get(4343L);
		tronconExpected = new Troncon(inter1, inter2, "Rue des bolchéviques", (float)6666);
	}


	@Test
	public void calculerTourneeTest(){
		File planTest = new File("assets/Tests/planCalculTourneeTest.xml");
		File dlTest = new File("assets/Tests/DLCalculTourneeTest.xml");
		try{
			DeserialiseurXML.chargerFichier(plan, planTest);
			assert(plan.getIntersections() != null);
			assert(plan.getTroncons() != null);
			DeserialiseurXML.chargerDemandeLivraisonFichier(plan, dlTest);
			assert(plan.getDemandeLivraison() != null);
			plan.calculTournee();
		} catch (Exception e) {
			fail("Error " + e);
			e.printStackTrace();
		}
		Tournee tournee = plan.getTournee();
		
		Entrepot entrepotTest = plan.getDemandeLivraison().getEntrepot();
		Intersection interTest1 = plan.getIntersections().get(1L);
		Intersection interTest3 = plan.getIntersections().get(3L);
		Intersection interTest5 = plan.getIntersections().get(5L);
		Troncon troncon12 = null;
		Troncon troncon23 = null;
		Troncon troncon31 = null;
		Troncon troncon35 = null;
		Troncon troncon43 = null;
		Troncon troncon54 = null;
		for(Troncon t : plan.getTroncons()){
			if(t.getDebut().getId() == 1 && t.getFin().getId() == 2){
				troncon12 = t;
			}else if(t.getDebut().getId() == 2 && t.getFin().getId() == 3){
				troncon23 = t;
			}else if(t.getDebut().getId() == 3 && t.getFin().getId() == 1){
				troncon31 = t;
			}else if(t.getDebut().getId() == 3 && t.getFin().getId() == 5){
				troncon35 = t;
			}else if(t.getDebut().getId() == 4 && t.getFin().getId() == 3){
				troncon43 = t;
			}else if(t.getDebut().getId() == 5 && t.getFin().getId() == 4){
				troncon54 = t;
			}
		}
		
		List<Livraison> solutionExpected = new ArrayList<Livraison>();
		solutionExpected.add(new Livraison((Intersection)entrepotTest, 0));
		solutionExpected.add(new Livraison(interTest3, 900));
		solutionExpected.add(new Livraison(interTest5, 600));
		
		List<Chemin> cheminExpected = new ArrayList<Chemin>();
		Chemin chemin13 = new Chemin(interTest1,interTest3);
		chemin13.addTroncon(0, troncon12);
		chemin13.addTroncon(1, troncon23);
		cheminExpected.add(chemin13);
		Chemin chemin35 = new Chemin(interTest3,interTest5);
		chemin35.addTroncon(0, troncon35);
		cheminExpected.add(chemin35);
		Chemin chemin51 = new Chemin(interTest5,interTest1);
		chemin51.addTroncon(0, troncon54);
		chemin51.addTroncon(1, troncon43);
		chemin51.addTroncon(2, troncon31);
		cheminExpected.add(chemin51);
		Itineraire itineraireExpected = new Itineraire(cheminExpected);
		
		tourneeExpected = new Tournee(plan.getDemandeLivraison().getEntrepot() , solutionExpected, itineraireExpected);
		
		
		assertEquals(tournee, tourneeExpected);
	}
	
	@Test
	public void calculerTourneeTestEchec(){
		File planTest = new File("assets/Tests/planCalculTourneeTest.xml");
		File dlTest = new File("assets/Tests/DLCalculTourneeTestEchec.xml");
		try{
			DeserialiseurXML.chargerFichier(plan, planTest);
			assert(plan.getIntersections() != null);
			assert(plan.getTroncons() != null);
			DeserialiseurXML.chargerDemandeLivraisonFichier(plan, dlTest);
			assert(plan.getDemandeLivraison() != null);
			plan.calculTournee();
			fail();
		} catch (Exception e) {
			assertEquals( e.getMessage(), ExceptionPlanCo.AUCUNE_SOLUTION);
		}
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
		Set<Troncon> troncons = plan.listerTronconVoisin(1029591870L);
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
		Set<Troncon> troncons = plan.listerTronconVoisin(998859048L);
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
