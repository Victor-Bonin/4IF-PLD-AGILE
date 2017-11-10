package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import modele.Entrepot;
import modele.ExceptionPlanCo;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import xml.DeserialiseurXML;

/**
 * <pre>
 * 
 * 
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o . . o.  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  '==="  /|
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
 * </pre>
 * 
 * 
 * @author 4104
 */
public class TestDeserialiseur {

	Plan plan;
	Plan planCharge;
	File planFile;
	File DLFile;
	
	Entrepot entrepot;
	List<Livraison> livraisons;
	
	@Before
	public void init() {		
		// init Test chargement plan
		plan = new Plan();
		planFile = new File("assets/Tests/PlanTest.xml");
		
		// init Test chargement DL
		DLFile = new File("assets/Tests/DLTest.xml");
		Calendar cal = Calendar.getInstance();
		cal.set(0,  0,  0,  8,  0,  0);
		entrepot = new Entrepot(new Intersection(15427, 27866, 1029591870L), cal);
		planCharge = new Plan();
		try {
			DeserialiseurXML.chargerFichier(planCharge, planFile);
		} catch (ParserConfigurationException | SAXException | IOException | ExceptionPlanCo e) {
			e.printStackTrace();
		}
		livraisons = new ArrayList<Livraison>();
		livraisons.add(new Livraison(new Intersection(15602, 27911, 103755060L), 900));
		livraisons.add(new Livraison(new Intersection(16034, 27050, 1039285693), 600));
	}
	
	@Test
	public void chargerPlanTest() {
		try {
			DeserialiseurXML.chargerFichier(plan, planFile);
		} catch (ParserConfigurationException | SAXException | IOException | ExceptionPlanCo e) {
			e.printStackTrace();
		}
		assertTrue(plan.getIntersections().containsKey(1029591870L));
		assertTrue(plan.getIntersections().containsKey(103755060L));
		assertTrue(plan.getIntersections().containsKey(1039285693L));
		assertNotNull(plan.getTroncons());
		assertTrue(!plan.getTroncons().isEmpty());
	}
	
	@Test
	public void chargerPlanEchecTest() {
		try {
			DeserialiseurXML.chargerFichier(plan, new File("assets/Tests/DLTest.xml"));
			fail();
		} catch (ParserConfigurationException | SAXException | IOException | ExceptionPlanCo e) {
			assertEquals(e.getMessage(), ExceptionPlanCo.DOCUMENT_NON_CONFORME);
		}
	}
	
	@Test
	public void chargerPlanStructureIncorrecteTest() {
		try {
			DeserialiseurXML.chargerFichier(plan, new File("assets/Tests/PlanTestEchec.xml"));
		} catch (ParserConfigurationException | SAXException | IOException | ExceptionPlanCo e) {
			return;
		}
		fail();
	}
	
	@Test
	public void chargerDemandeLivraisonTest() {
		try {
			DeserialiseurXML.chargerDemandeLivraisonFichier(planCharge, DLFile);
		} catch (ParserConfigurationException | SAXException | IOException | ExceptionPlanCo | ParseException e) {
			e.printStackTrace();
		}
		assertTrue(planCharge.getDemandeLivraison().getEntrepot().equals(entrepot));
		assertTrue(planCharge.getDemandeLivraison().getLivraisons().size() == 2);
		assertTrue(planCharge.getDemandeLivraison().getLivraisons().get(0).equals(livraisons.get(0)));
		assertTrue(planCharge.getDemandeLivraison().getLivraisons().get(1).equals(livraisons.get(1)));
	}
	
	@Test
	public void chargerDemandeLivraisonEchecTest() {
		try {
			DeserialiseurXML.chargerDemandeLivraisonFichier(planCharge, new File("assets/Tests/PlanTest.xml"));
			fail();
		} catch (ParserConfigurationException | SAXException | IOException | ExceptionPlanCo | ParseException e) {
			assertEquals(e.getMessage(), ExceptionPlanCo.DOCUMENT_NON_CONFORME);
		}
	}
	
	@Test
	public void chargerDemandeLivraisonStructureIncorrecteTest() {
		try {
			DeserialiseurXML.chargerDemandeLivraisonFichier(planCharge, new File("assets/Tests/DLTestEchec.xml"));
		} catch (ParserConfigurationException | SAXException | IOException | ExceptionPlanCo | ParseException e) {
			return;
		}
		fail();
	}
}
