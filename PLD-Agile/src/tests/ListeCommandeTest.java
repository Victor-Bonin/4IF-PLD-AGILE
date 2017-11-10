package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import controleur.CommandeAjouter;
import controleur.ListeCommande;
import modele.ExceptionPlanCo;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

public class ListeCommandeTest {
	
	ListeCommande listeCmd;
	Plan plan;
	Livraison livraison;
	
	@Before
	public void init() {
		listeCmd = new ListeCommande();
		plan = new Plan();
		plan.ajouterIntersection(100, 100, 100);
		plan.ajouterIntersection(100,  150,  101);
		try {
			plan.ajouterTroncon(100L,  101L,  10,  "Rue");
		} catch (ExceptionPlanCo e) {
			fail();
		}
		livraison = new Livraison(new Intersection(100, 101, 101), 0);
	}
	
	/*@Test
	public void undoTest() {
		try {
			listeCmd.ajoute(new CommandeAjouter(plan, livraison, 0));
			assertTrue(listeCmd.getListeCommandes().size() == 1);
		} catch (ExceptionPlanCo e) {
			fail("Erreur undoTest : ajout d'une commande");
		}
	}*/
}
