package vue.etat;

import javax.swing.JPanel;

import modele.DemandeLivraison;
import modele.Plan;
import vue.EcouteurDeSourisDeSynchronisation;
import vue.Fenetre;
import vue.PersoButton;
import vue.VuePlan;
import vue.VueTournee;

public class EtatDemandeOuverte extends EtatDefaut {

	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {
		footer.remove(fenetre.getImportDemandeLivraisonButton());
		footer.remove(fenetre.getExportButton());
		PersoButton calculTourneeButton = fenetre.getCalculTourneeButton();
		footer.add(calculTourneeButton);
		calculTourneeButton.setEnabled(true);
	}

	@Override
	public void afficherVue(Fenetre fenetre) {
		VueTournee vueTournee = fenetre.getVueTournee();
		VuePlan vuePlan = fenetre.getVuePlan();
		Plan plan = fenetre.getPlan();
		
		if(plan.getDemandeLivraison().getLivraisons().isEmpty() && plan.getDemandeLivraison().getEntrepot()==null) {
			vuePlan.nettoyerIcones();
		}else {
			vueTournee.initTournee(plan.getDemandeLivraison());
			vuePlan.afficherIcones(plan.getDemandeLivraison());
		}
		vuePlan.activerAnnulationBouton(false);

		fenetre.ajouterEcouteursSynchro();
		vuePlan.activerBouton(true);
		
	}

}
