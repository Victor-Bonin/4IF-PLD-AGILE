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

	public PlageHoraire(Calendar debut, Calendar fin) {
		debutPlage = debut;
		finPlage = fin;
	}
	
	public Calendar getDebut() {
		return debutPlage;
	}
	
	public Calendar getFin() {
		return finPlage;
	}
	
	public void setDebut(Calendar debutPlage) {
		this.debutPlage=debutPlage;
	}
	
	public void setFin(Calendar finPlage) {
		this.finPlage=finPlage;
	}
	
}
