package modele;

import java.util.Calendar;

/**
 * Une etape d’une tournee composee d’une adresse (intersection) et d’une plage horaire
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
public class Livraison extends Intersection{
	protected int duree;
	protected Calendar heurePassage;
	
	public Livraison(Intersection inter, int dureeArret) {
		super(inter);
		duree = dureeArret;
	}
	
	public boolean equals(Object obj) {
		return (this.getId() == ((Intersection)obj).getId());
	}
	
	public int getDuree(){
		return this.duree;
	}
	
	public Calendar getHeurePassage(){
		return this.heurePassage;
	}
	
	public void setHeurePassage(Calendar heurePassage){
		this.heurePassage = heurePassage;
	}
	
	public void setDuree(int d) {
		duree = d;
	}

	/**
	 * With an offset for the moment
	 * @param cal
	 * @return
	 */
	public static int getSecondsInDay(Calendar cal) {
		//TODO changer date
		//cal.set(Calendar.YEAR, 1900);
		if(cal==null) {
			return -1;
		}
		return (int)(cal.getTimeInMillis()/1000);
	}
}
