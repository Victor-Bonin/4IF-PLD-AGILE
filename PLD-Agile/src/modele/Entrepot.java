package modele;

import java.util.Date;

public class Entrepot extends Intersection{
	private Date heureDepart;
	
	public Entrepot(Intersection inter, Date hDepart) {
		super(inter);
		heureDepart = hDepart;
	}
}
