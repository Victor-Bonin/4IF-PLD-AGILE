package controleur;

public interface Commande {
	
	/**
	 * Execute la commande this
	 */
	void doCde();
	
	/**
	 * Execute la commande inverse a this
	 */
	void undoCde();
}
