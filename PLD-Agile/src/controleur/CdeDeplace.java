package controleur;
import modele.Forme;

public class CdeDeplace implements Commande {
	private Forme f;
	private int deltaX;
	private int deltaY;
	
	/**
	 * Cree la commande qui deplace la forme f de (deltaX,deltaY)
	 * @param f
	 * @param deltaX
	 * @param deltaY
	 */
	public CdeDeplace(Forme f, int deltaX, int deltaY) {
		this.f = f;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	@Override
	public void doCde() {
		f.deplace(deltaX,deltaY);
	}

	@Override
	public void undoCde() {
		f.deplace(-deltaX,-deltaY);
	}

}
