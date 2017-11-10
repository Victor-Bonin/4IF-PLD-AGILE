package modele;

import java.util.Calendar;

/**
 * <pre>
 * Entrepot
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
public class Entrepot extends Intersection{
	private PlageHoraire horairesDepArr;
	
	public Entrepot(Intersection inter, Calendar hDepart) {
		super(inter);
		horairesDepArr = new PlageHoraire(hDepart, null);
	}
	
	public Calendar getHeureDepart(){
		return this.horairesDepArr.getDebut();
	}
	
	public Calendar getHeureArrivee(){
		return this.horairesDepArr.getFin();
	}
	
	/**
	* Verifie si l'objet Entrepot possède la meme adresse que l'intersection
	* @param obj Une intersection
	* @return true si les adresses sont les memes.
	*/
	public boolean equals(Object obj) {
		return (this.getId() == ((Intersection)obj).getId());
	}
	
	public void setHeureArrivee(Calendar heureArrivee) {
		this.horairesDepArr.setFin(heureArrivee);
	}

	public PlageHoraire getHoraires() {
		return horairesDepArr;
	}
}
