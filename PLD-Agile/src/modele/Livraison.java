package modele;

import java.util.Date;

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
