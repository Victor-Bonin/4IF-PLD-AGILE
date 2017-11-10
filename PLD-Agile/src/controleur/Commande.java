package controleur;

import modele.ExceptionPlanCo;

/**
 * Interface Commande pour le design pattern Commande.  
 * @author 4104
 */
public interface Commande {

	/**
	 * Execute la commande this
	 * @throws ExceptionPlanCo 
	 */
	void doCde() throws ExceptionPlanCo;
	
	/**
	 * Execute la commande inverse a this
	 * @throws ExceptionPlanCo 
	 */
	void undoCde() throws ExceptionPlanCo;
}
