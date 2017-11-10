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
 *         \| 'o . . o.  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  '==="  /|
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
 *  
 * @author 4104
 */
public class LivraisonPlageHoraire extends Livraison {

	private PlageHoraire plage;
	private int attente;

	public LivraisonPlageHoraire(Intersection inter, int dureeArret, Calendar debut, Calendar fin) {
		super(inter, dureeArret);
		plage = new PlageHoraire(debut, fin);
	}
	
	public PlageHoraire getPlage(){
		return this.plage;
	}
	
	public void setAttente(int attente) {
		this.attente = attente;
	}
	
	public int getAttente() {
		return attente;
	}
	
	public int getRetardPossible() {
		return getSecondsInDay(plage.getFin()) - (getSecondsInDay(heurePassage) + duree);
	}
	
	public Calendar getArriveeEstimee() {
		Calendar heureEstimee = (Calendar) this.getHeurePassage().clone();
		heureEstimee.add(Calendar.SECOND, - this.getAttente());
		return (heureEstimee);
		}
	
	public Calendar getDebut() {
		return plage.getDebut();
	}
	
	public Calendar getFin() {
		return plage.getFin();
	}
}
