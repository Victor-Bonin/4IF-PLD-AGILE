package controleur;

import modele.Plan;
import modele.Forme;

public class CdeAjout implements Commande {
	
	private Plan plan;
	private Forme forme;
	
	/**
	 * Cree la commande qui ajoute la forme f au plan p
	 * @param p
	 * @param f
	 */
	public CdeAjout(Plan p, Forme f){
		this.plan = p;
		this.forme = f;
	}

	@Override
	public void doCde() {
		plan.ajoute(forme);
	}

	@Override
	public void undoCde() {
		plan.supprime(forme);
	}

}
