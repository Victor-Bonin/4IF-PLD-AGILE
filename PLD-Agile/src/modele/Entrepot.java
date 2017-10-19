package modele;

import java.util.Date;

public class Entrepot extends Intersection{
	private Date heureDepart;
	
	public Entrepot(Intersection inter, Date hDepart) {
		super(inter);
		heureDepart = hDepart;
	}
	
	public boolean equals(Intersection obj) {
		return (this.getId() == obj.getId());
	}
	
	public Date getHeureDepart() {
		return heureDepart;
	}
}
