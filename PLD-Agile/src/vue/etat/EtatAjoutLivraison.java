package vue.etat;

import vue.Fenetre;

public class EtatAjoutLivraison extends EtatDefaut {

	@Override
	public void afficherVue(Fenetre fenetre) {
		fenetre.getVueTournee().creerLivraisonApres(2);
	}

}
