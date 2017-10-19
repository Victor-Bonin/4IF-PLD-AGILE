package modele;

import java.util.Calendar;

/**
 * Entrepot
 * @author 4104
 *
 */
public class Entrepot extends Intersection{
	private Calendar heureDepart;
	private Calendar heureArrivee;
	
	public Entrepot(Intersection inter, Calendar hDepart) {
		super(inter);
		heureDepart = hDepart;
	}
	
	public Calendar getHeureDepart(){
		return this.heureDepart;
	}
	
	public Calendar getHeureArrivee(){
		return this.heureArrivee;
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
		this.heureArrivee = heureArrivee;
	}
}
