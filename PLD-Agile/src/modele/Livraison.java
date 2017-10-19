package modele;

import java.util.Date;

public class Livraison extends Intersection{
	private int duree;
	private Date heurePassage;
	
	public Livraison(Intersection inter, int dureeArret) {
		super(inter);
		duree = dureeArret;
	}
	
	public Date getHeurePassage() {
		return this.heurePassage;
	}
	
	public int getDuree() {
		return duree;
	}
}
