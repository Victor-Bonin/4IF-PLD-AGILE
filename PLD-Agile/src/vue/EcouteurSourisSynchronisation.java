package vue;

/**
 * Cette classe contient les appels aux fonctions de synchronisation depuis la souris
 * 
 * @author 4104
 *
 */
public class EcouteurSourisSynchronisation {
	private VueTournee vueTournee;
	private VuePlan vuePlan;
	private int posX;
	private int posY;
	
	public EcouteurSourisSynchronisation(VueTournee vt, VuePlan vp){
		vueTournee = vt;
		vuePlan = vp;
	}

}
