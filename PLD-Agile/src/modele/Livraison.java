package modele;

import java.util.Date;

/**
 * Une etape d’une tournee composee d’une adresse (intersection) et d’une plage horaire
 * @author 4104
 */
public class Livraison extends Intersection{
	private int duree;
	private Date heurePassage;
	
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
}
