package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Demande de livraison entre un entrepot et des livraisons (non ordonnées)
 * @author 4104
 *
 */
public class DemandeLivraison {
	private Entrepot entrepot;
	private List<Livraison> livraisons;
	
	public DemandeLivraison() {
		livraisons = new ArrayList<Livraison>();
	}
	
	public Entrepot getEntrepot(){
		return this.entrepot;
	}
	
	public List<Livraison> getLivraisons(){
		return this.livraisons;
	}

	public void ajoutePointLivraison(Livraison lvrsn) throws ExceptionPlanCo {
		ajoutePointLivraison(lvrsn, livraisons.size());
	}
	
	public void ajoutePointLivraison(Livraison lvrsn, int index) throws ExceptionPlanCo {
		if(lvrsn == null)
			throw new ExceptionPlanCo(ExceptionPlanCo.DEV_ONLY_1);
		if(livraisons.contains(lvrsn))
			throw new ExceptionPlanCo(ExceptionPlanCo.LIVRAISON_DEJA_PRESENTE);

		try {
			livraisons.add(index, lvrsn);
		}
		catch (IndexOutOfBoundsException e) {
			throw new ExceptionPlanCo(ExceptionPlanCo.DEV_ONLY_2);
		}
		catch (Exception e) {
			throw new ExceptionPlanCo(ExceptionPlanCo.ERREUR_AJOUT_LIVRAISON);		
		}
		
	}
	
	public void supprimerPointLivraison(Livraison lvrsn) throws ExceptionPlanCo {
		if(lvrsn == null)
			throw new ExceptionPlanCo(ExceptionPlanCo.DEV_ONLY_3);
		if(!livraisons.contains(lvrsn))
			throw new ExceptionPlanCo(ExceptionPlanCo.LIVRAISON_ABSENTE);
		if (!livraisons.remove(lvrsn))
			throw new ExceptionPlanCo(ExceptionPlanCo.ERREUR_SUPPRESSION_LIVRAISON);
	}

	public void setEntrepot(Entrepot entrpt){
		entrepot = entrpt;
	}
	
	public void setLivraisons(List<Livraison> livs){
		this.livraisons = livs;
	}
}
