package modele;

import java.util.Calendar;

/**
 * <pre>
 * Une etape d’une tournee composee d’une adresse (intersection) et d’une plage horaire
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
public class Livraison extends Intersection {

	protected int duree;
	protected Calendar heurePassage;

	/**
	 * Constructeur d'une livraison a partir de l'intersection correspondant a son adresse et de la duree de la livraison.
	 * @param inter l'intersection
	 * @param dureeArret la durée de la livraison
	 */
	public Livraison(Intersection inter, int dureeArret) {
		super(inter);
		duree = dureeArret;
	}

	/**
	 * Retourne la durée de la livraison.
	 * @return la duree de la livraison
	 */
	public int getDuree() {
		return this.duree;
	}

	/**
	 * Retourne l'heure de passage de la livraison.
	 * @return l'heure de passage de la livraison
	 */
	public Calendar getHeurePassage() {
		return this.heurePassage;
	}

	/**
	 * Change l'heure de passage de la livraison.
	 * @param heurePassage la nouvelle heure de passage pour cette livraison
	 */
	public void setHeurePassage(Calendar heurePassage) {
		this.heurePassage = heurePassage;
	}

	/**
	 * Change la durée de la livraison.
	 * @param d la nouvelle durée de la livraison
	 */
	public void setDuree(int d) {
		duree = d;
	}

	/**
	 * Convertit le calendrier passe en parametre en seconde par rapport a une date 
	 * @param cal la date a convertir en secondes
	 * @return int le nombre de secondes qui s'est ecoule
	 */
	public static int getSecondsInDay(Calendar cal) {
		//TODO changer date mettre date du jour
		//cal.set(Calendar.YEAR, 1900);
		if(cal==null) {
			return -1;
		}
		return (int)(cal.getTimeInMillis()/1000);
	}
}
