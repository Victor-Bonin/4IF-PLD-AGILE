package modele;

import java.util.Date;

public class Livraison extends Intersection{
	private int duree;
	private Date heurePassage;
	
	public Livraison(int coordX, int coordY, int id) {
		super(coordX, coordY, id);
	}
}
