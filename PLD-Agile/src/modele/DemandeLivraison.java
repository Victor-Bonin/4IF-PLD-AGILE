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
		if(lvrsn == null)
			throw new ExceptionPlanCo("Dev ONLY : PdL null ajt 0xce");
		if(livraisons.contains(lvrsn))
			throw new ExceptionPlanCo("Point de livraison déjà dans la demande de livraison");
		if(!livraisons.add(lvrsn))
			throw new ExceptionPlanCo("Erreur lors de l'ajout de la livraison");
	}
	
	public void supprimerPointLivraison(Livraison lvrsn) throws ExceptionPlanCo {
		if(lvrsn == null)
			throw new ExceptionPlanCo("Dev ONLY : PdL null sppr 0xce");
		if(!livraisons.contains(lvrsn))
			throw new ExceptionPlanCo("Point de livraison non présent dans la demande de livraison");
		if (!livraisons.remove(lvrsn))
			throw new ExceptionPlanCo("Erreur lors de la suppression de la livraison");
	}

	public void setEntrepot(Entrepot entrpt){
		entrepot = entrpt;
	}
	
	public void setLivraisons(List<Livraison> livs){
		this.livraisons = livs;
	}
}
