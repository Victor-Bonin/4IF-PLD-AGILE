package modele;

import java.util.Date;

/**
 * Entrepot
 * @author 4104
 *
 */
public class Entrepot extends Intersection{
	private Date heureDepart;
	
	public Entrepot(Intersection inter, Date hDepart) {
		super(inter);
		heureDepart = hDepart;
	}
	
	/**
	 * Verifie si l'objet Entrepot possède la meme adresse que l'intersection
	 * @param obj Une intersection
	 * @return true si les adresses sont les memes.
	 */
	public boolean equals(Intersection obj) {
		return (this.getId() == obj.getId());
	}
}
