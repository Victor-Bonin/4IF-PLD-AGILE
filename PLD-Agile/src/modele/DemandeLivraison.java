package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Demande de livraison entre un entrepot et des livraisons, non ordonnees.
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
public class DemandeLivraison {

	private Entrepot entrepot;
	private List<Livraison> livraisons;

	/**
	 * Constructeur de demande de livraison.
	 * Initialise la liste de livraison sans la remplir.
	 */
	public DemandeLivraison() {
		livraisons = new ArrayList<Livraison>();
	}

	/**
	 * Retourne l'entrepot de la demande de livraison.
	 * @return l'entrepot
	 */
	public Entrepot getEntrepot() {
		return this.entrepot;
	}

	/**
	 * Retourne la liste de livraison de la demande de livraison.
	 * @return liste de Livraison
	 */
	public List<Livraison> getLivraisons() {
		return this.livraisons;
	}

	/**
	 * Ajoute un point de livraison a la demande de livraison.
	 * Si l'ajout n'est pas possible lance une ExceptionPlanCo.
	 * @param lvrsn le point de livraison a ajouter
	 * @throws ExceptionPlanCo
	 */
	public void ajoutePointLivraison(Livraison lvrsn) throws ExceptionPlanCo {
		ajoutePointLivraison(lvrsn, livraisons.size());
	}

	/**
	 * Ajoute un point de livraison a la demande de livraison a la position indiquee.
	 * Si l'ajout n'est pas possible lance une ExceptionPlanCo.
	 * @param lvrsn le point de livraison a ajouter
	 * @param index la position dans la liste ou ajouter le point de livraison
	 * @throws ExceptionPlanCo
	 */
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

	/**
	 * Supprime la livraison passee en parametre de la demande de livraison.
	 * Si la suppression n'est pas possible lance une ExceptionPlanCo.
	 * @param lvrsn le point de livraison a supprimer
	 * @throws ExceptionPlanCo
	 */
	public void supprimerPointLivraison(Livraison lvrsn) throws ExceptionPlanCo {
		if(lvrsn == null)
			throw new ExceptionPlanCo(ExceptionPlanCo.DEV_ONLY_3);
		if(!livraisons.contains(lvrsn))
			throw new ExceptionPlanCo(ExceptionPlanCo.LIVRAISON_ABSENTE);
		if (!livraisons.remove(lvrsn))
			throw new ExceptionPlanCo(ExceptionPlanCo.ERREUR_SUPPRESSION_LIVRAISON);
	}

	/**
	 * Change l'entrepot de la demande de livraison.
	 * @param entrpt le nouvel entrepot
	 */
	public void setEntrepot(Entrepot entrpt) {
		entrepot = entrpt;
	}

	/**
	 * Change la liste de point de livraison de la demande de livraison.
	 * @param livs la nouvelle liste de point de livraison
	 */
	public void setLivraisons(List<Livraison> livs) {
		this.livraisons = livs;
	}
}
