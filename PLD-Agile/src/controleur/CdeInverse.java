package controleur;


public class CdeInverse implements Commande{
	private Commande cde;
	
	/**
	 * Cree la commande inverse a la commande cde (de sorte que cde.doCde corresponde a this.undoCde, et vice-versa) 
	 * @param cde
	 */
	public CdeInverse(Commande cde){
		this.cde = cde;
	}

	@Override
	public void doCde() {
		cde.undoCde();
	}

	@Override
	public void undoCde() {
		cde.doCde();
	}

}
