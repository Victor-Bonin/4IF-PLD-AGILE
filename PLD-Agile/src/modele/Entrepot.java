package modele;

import java.util.Calendar;

/**
 * Entrepot
 * @author 4104
 *
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
	public boolean egal(Object obj) {
		return (this.getId() == ((Intersection)obj).getId());
	}
	
	public void setHeureArrivee(Calendar heureArrivee) {
		this.horairesDepArr.setFin(heureArrivee);
	}

	public PlageHoraire getHoraires() {
		return horairesDepArr;
	}
}
