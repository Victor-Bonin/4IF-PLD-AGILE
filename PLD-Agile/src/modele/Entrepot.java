package modele;

import java.util.Calendar;

/**
 * <pre>
 * Entrepot, point de depart d'une demande de livraison
 * 
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
 * </pre>
 *  
 * @author 4104
 */
public class Entrepot extends Intersection {

	private PlageHoraire horairesDepArr;

	/**
	 * Constructeur de l'entrepot a partir de l'intersection ou se trouve l'entrepot et l'heure de depart de l'entrepot.
	 * @param inter l'intersection
	 * @param hDepart l'heure de depart
	 */
	public Entrepot(Intersection inter, Calendar hDepart) {
		super(inter);
		horairesDepArr = new PlageHoraire(hDepart, null);
	}

	/**
	 * Retourne l'heure de depart de l'entrepot.
	 * @return l'heure de depart de l'entrepot
	 */
	public Calendar getHeureDepart() {
		return this.horairesDepArr.getDebut();
	}

	/**
	 * Retourne l'heure d'arrivee a l'entrepot.
	 * @return l'heure d'arrive a l'entrepot
	 */
	public Calendar getHeureArrivee() {
		return this.horairesDepArr.getFin();
	}


	/**
	 * Change l'heure d'arrivee a l'entrepot, l'heure de retour.
	 * @param heureArrivee la nouvelle heure d'arrivee a l'entrepot
	 */
	public void setHeureArrivee(Calendar heureArrivee) {
		this.horairesDepArr.setFin(heureArrivee);
	}

	/**
	 * Retourne la plage horaire de l'entrepot.
	 * @return la plage horaire de l'entrepot
	 */
	public PlageHoraire getHoraires() {
		return horairesDepArr;
	}
}
