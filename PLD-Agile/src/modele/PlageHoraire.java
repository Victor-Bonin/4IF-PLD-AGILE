package modele;

import java.util.Calendar;

/**
 * Intervalle de temps dans lequel doit s’effectuer une livraison
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
public class PlageHoraire {

	private Calendar debutPlage;
	private Calendar finPlage;

	/**
	 * Constructeur d'une plage horaire, avec une heure de début et une heure de fin.
	 * @param debut l'heure de debut de la plage horaire
	 * @param fin l'heure de fin de la plage horaire
	 */
	public PlageHoraire(Calendar debut, Calendar fin) {
		debutPlage = debut;
		finPlage = fin;
	}

	/**
	 * Retourne l'heure de debut de la plage horaire.
	 * @return l'heure de debut de la plage horaire
	 */
	public Calendar getDebut() {
		return debutPlage;
	}

	/**
	 * Retourne l'heure de fin de la plage horaire.
	 * @return l'heure de fin de la plage horaire
	 */
	public Calendar getFin() {
		return finPlage;
	}

	/**
	 * Change l'heure de debut de la plage horaire.
	 * @param debutPlage la nouvelle heure de debut de la plage horaire
	 */
	public void setDebut(Calendar debutPlage) {
		this.debutPlage=debutPlage;
	}

	/**
	 * Change l'heure de fin de la plage horaire.
	 * @param finPlage la nouvelle heure de fin de la plage horaire
	 */
	public void setFin(Calendar finPlage) {
		this.finPlage=finPlage;
	}

}
