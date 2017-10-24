package modele;

import java.util.Calendar;

/**
 * Intervalle de temps dans lequel doit s’effectuer une livraison
 * @author 4104
 */
public class PlageHoraire {
	private Calendar debutPlage;
	private Calendar finPlage;
	
	public PlageHoraire( Calendar debutPlage, Calendar finPlage){
		this.debutPlage=debutPlage;
		this.finPlage=finPlage;
	}
	
	public Calendar getDebut() {
		return debutPlage;
	}
	
	public Calendar getFin() {
		return finPlage;
	}
	
	public void setDebut(Calendar debutPlage) {
		this.debutPlage=debutPlage;
	}
	
	public void setFin(Calendar finPlage) {
		this.finPlage=finPlage;
	}
	
}
