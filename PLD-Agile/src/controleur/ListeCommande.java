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

Classe représentant la liste des commandes que l'on peut annuler / refaire.
@author 4104
 */
package controleur;

import java.util.LinkedList;

/**
 * Classe stockant l'historique des commandes effectuees par l'utilisateur, permettant le do / undo / delete d'une commande.
 * @author 4104
 */
public class ListeCommande{
	private LinkedList<Commande> liste;
	private int indiceCrt;
	
	public ListeCommande(){
		indiceCrt = -1;
		liste = new LinkedList<Commande>();
	}
	
	/**
	 * Ajout de la commande c a la liste this
	 * @param c
	 */
	public void ajoute(Commande c){
        int i = indiceCrt+1;
        while(i<liste.size()){
            liste.remove(i);
        }
        indiceCrt++;
        liste.add(indiceCrt, c);
        c.doCde();
    }
	
	/**
	 * Annule temporairement la derniere commande ajoutee (cette commande pourra etre remise dans la liste avec redo)
	 */
	public void undo(){
		if (indiceCrt >= 0){
			Commande cde = liste.get(indiceCrt);
			indiceCrt--;
			cde.undoCde();
		}
	}
	
	/**
	 * Supprime definitivement la derniere commande ajoutee (cette commande ne pourra pas etre remise dans la liste avec redo)
	 */
	public void annule(){
		if (indiceCrt >= 0){
			Commande cde = liste.get(indiceCrt);
			liste.remove(indiceCrt);
			indiceCrt--;
			cde.undoCde();
		}
	}

	/**
	 * Remet dans la liste la derniere commande annulee avec undo
	 */
	public void redo(){
		if (indiceCrt < liste.size()-1){
			indiceCrt++;
			Commande cde = liste.get(indiceCrt);
			cde.doCde();
		}
	}
	
	/**
	 * Supprime definitivement toutes les commandes de liste
	 */
	   public void reset(){
	        indiceCrt = -1;
	        liste.clear();  
	    }
}
