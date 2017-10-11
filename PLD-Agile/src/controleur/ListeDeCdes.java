package controleur;

import java.util.LinkedList;

public class ListeDeCdes {
	private LinkedList<Commande> liste;
	private int indiceCrt;
	
	public ListeDeCdes(){
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
