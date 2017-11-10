package modele;

import java.util.Calendar;

/**
 * <pre>
 * Une etape d’une tournee composee d’une adresse (intersection) et d'une plage horaire
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
public class LivraisonPlageHoraire extends Livraison {

	private PlageHoraire plage;
	private int attente;

	/**
	 * Constructeur d'une livraison avec plage horaire.
	 * @param inter l'intersection de la livraison
	 * @param dureeArret la durée de la livraison
	 * @param debut l'heure correspondant au debut de la plage horaire
	 * @param fin l'heure correspondant a la fin de la plage horaire
	 */
	public LivraisonPlageHoraire(Intersection inter, int dureeArret, Calendar debut, Calendar fin) {
		super(inter, dureeArret);
		plage = new PlageHoraire(debut, fin);
	}

	/**
	 * Retourne la plage horaire de la livraison.
	 * @return la plage horaire de la livraison
	 */
	public PlageHoraire getPlage() {
		return this.plage;
	}

	/**
	 * Change le temps d'attente de la livraison.
	 * @param attente le nouveau temps d'attente
	 */
	public void setAttente(int attente) {
		this.attente = attente;
	}

	/**
	 * Retourne le temps d'attente de la livraison.
	 * @return le temps d'attente de la livraison
	 */
	public int getAttente() {
		return attente;
	}

	/**
	 * Retourne le retard possible pour la livraison.
	 * @return le retard possible pour la livraison
	 */
	public int getRetardPossible() {
		return getSecondsInDay(plage.getFin()) - (getSecondsInDay(heurePassage) + duree);
	}

	/**
	 * Retourne l'heure d'arrivee estime pour la livraison.
	 * @return l'heure d'arrivee estime pour la livraison
	 */
	public Calendar getArriveeEstimee() {
		Calendar heureEstimee = (Calendar) this.getHeurePassage().clone();
		heureEstimee.add(Calendar.SECOND, - this.getAttente());
		return heureEstimee;
	}

	/**
	 * Retourne l'heure de debut de la plage horaire de la livraison.
	 * @return l'heure de debut de la plage horaire
	 */
	public Calendar getDebut() {
		return plage.getDebut();
	}

	/**
	 * Retourne l'heure de fin de la plage horaire de la livraison.
	 * @return l'heure de fin de la plage horaire
	 */
	public Calendar getFin() {
		return plage.getFin();
	}
}
