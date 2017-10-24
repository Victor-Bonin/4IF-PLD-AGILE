package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.DemandeLivraison;
import modele.ExceptionPlanCo;
import modele.Intersection;
import modele.Livraison;

public class demandeLivraisonTest {
	DemandeLivraison demandeLivraison = new DemandeLivraison(); 

	@Before
	public void init() {}

	@Test
	public void ajoutePointLivraisonTest() {
		Livraison l = new Livraison(new Intersection(1, 1, 100L), 0);

		try {
			assertEquals(0, demandeLivraison.getLivraisons().size());	
			demandeLivraison.ajoutePointLivraison(l);
			assertEquals(1, demandeLivraison.getLivraisons().size());
		}
		catch (ExceptionPlanCo e) {
			fail("Erreur ajoutePointLivraisonTest ExceptionPlanCo cas normal");
		}

		try {
			demandeLivraison.ajoutePointLivraison(null);
			fail ("Ajout de point de livraison null interdit");
		} catch (ExceptionPlanCo e) {
			assertEquals("Dev ONLY : PdL null ajt 0xce", e.getMessage());
			assertEquals(1, demandeLivraison.getLivraisons().size());	
		}

		try {
			demandeLivraison.ajoutePointLivraison(l);
			fail ("Ajout de point de livraison identique interdit");
		} catch (ExceptionPlanCo e) {
			assertEquals("Point de livraison déjà dans la demande de livraison",
					e.getMessage());
			assertEquals(1, demandeLivraison.getLivraisons().size());	
		}
	}

	@Test
	public void supprimerPointLivraisonTest() {
		Livraison l = new Livraison(new Intersection(1, 1, 100L), 0);
		try {
			demandeLivraison.ajoutePointLivraison(l);

			demandeLivraison.supprimerPointLivraison(l);
			assertEquals(0, demandeLivraison.getLivraisons().size());

			Livraison l2 = new Livraison(new Intersection(2, 2, 200L), 0);
			demandeLivraison.ajoutePointLivraison(l);
			demandeLivraison.ajoutePointLivraison(l2);

			demandeLivraison.supprimerPointLivraison(l);
			assertEquals(1, demandeLivraison.getLivraisons().size());
		}
		catch(ExceptionPlanCo e) {
			fail("Erreur supprimerPointLivraisonTest ExceptionPlanCo cas normal");
		}

		try {
			demandeLivraison.supprimerPointLivraison(null);
			fail("Suppression point de livraison null");
		}
		catch(ExceptionPlanCo e) {
			assertEquals("Dev ONLY : PdL null sppr 0xce", 
					e.getMessage());
			assertEquals(1, demandeLivraison.getLivraisons().size());
		}

		try {
			demandeLivraison.supprimerPointLivraison(l);
			fail("Suppression point de livraison inexistant");
		}
		catch(ExceptionPlanCo e) {
			assertEquals("Point de livraison non présent dans la demande de livraison", 
					e.getMessage());
			assertEquals(1, demandeLivraison.getLivraisons().size());
		}


	}

}
