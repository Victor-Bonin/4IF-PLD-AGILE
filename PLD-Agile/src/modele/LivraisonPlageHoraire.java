/**
 * This file has been created by vbonin, on 11 oct. 2017
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr

_____   _   _____   __   _   _     _   _____   __   _   _   _   _____  
|  _  \ | | | ____| |  \ | | | |   / / | ____| |  \ | | | | | | | ____| 
| |_| | | | | |__   |   \| | | |  / /  | |__   |   \| | | | | | | |__   
|  _  { | | |  __|  | |\   | | | / /   |  __|  | |\   | | | | | |  __|  
| |_| | | | | |___  | | \  | | |/ /    | |___  | | \  | | |_| | | |___  
|_____/ |_| |_____| |_|  \_| |___/     |_____| |_|  \_| \_____/ |_____| 



 */
package modele;

import java.util.Calendar;

/**
 * Une etape d’une tournee composee d’une adresse (intersection) et d'une plage horaire
 * @author 4104
 */
public class LivraisonPlageHoraire extends Livraison {

	private PlageHoraire plage;

	public LivraisonPlageHoraire(Intersection inter, int dureeArret, Calendar debut, Calendar fin) {
		super(inter, dureeArret);
		plage = new PlageHoraire(debut, fin);
	}
	
	public PlageHoraire getPlage(){
		return this.plage;
	}
	
	public Calendar getDebut() {
		return plage.getDebut();
	}
	
	public Calendar getFin() {
		return plage.getFin();
	}
}
