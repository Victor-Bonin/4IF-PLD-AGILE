package controleur;

import java.util.LinkedList;

import modele.ExceptionPlanCo;

/**
 * <pre>
 * Classe stockant l'historique des commandes effectuees par l'utilisateur, permettant le do / undo / delete d'une commande.
 * 
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o . . o.  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  '==="  /|
 *             \      .: /|\
 *             )\_   .: / |:"--___
 *         __-:|\ """ _-  |:::::::
 *       _-::::\ "-_.-   /::::::::
 *    _--:::::::| .|"-_  |::::::::
 *  -"::::::::::\  | { -_|::::::::
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr
 * </pre>
 * 
 *  
 * @author 4104
 */
public class ListeCommande{
	private LinkedList<Commande> liste;
	private int indiceCrt;

	/** Constructeur */
	public ListeCommande(){
		indiceCrt = -1;
		liste = new LinkedList<Commande>();
	}

	/**
	 * Ajout de la commande cmd a la liste this
	 * @param cmd commande a ajouter a la liste
	 * @throws ExceptionPlanCo Une execption PlanCo qui est levee si une erreur s'est produite
	 */
	public void ajoute(Commande cmd) throws ExceptionPlanCo{
		int i = indiceCrt+1;
		while(i<liste.size()){
			liste.remove(i);
		}
		indiceCrt++;
		liste.add(indiceCrt, cmd);
		cmd.doCde();
	}

	/**
	 * Annule temporairement la derniere commande ajoutee (cette commande pourra etre remise dans la liste avec redo)
	 * @throws ExceptionPlanCo Une execption PlanCo qui est levee si une erreur s'est produite
	 */
	public void undo() throws ExceptionPlanCo{
		if (indiceCrt >= 0){
			Commande cde = liste.get(indiceCrt);
			indiceCrt--;
			cde.undoCde();
		}
	}

	/**
	 * Supprime definitivement la derniere commande ajoutee (cette commande ne pourra pas etre remise dans la liste 
	 * avec redo)
	 * @throws ExceptionPlanCo Une execption PlanCo qui est levee si une erreur s'est produite 
	 */
	public void annule() throws ExceptionPlanCo{
		if (indiceCrt >= 0){
			Commande cde = liste.get(indiceCrt);
			liste.remove(indiceCrt);
			indiceCrt--;
			cde.undoCde();
		}
	}

	/**
	 * Remet dans la liste la derniere commande annulee avec undo
	 * @throws ExceptionPlanCo Une execption PlanCo qui est levee si une erreur s'est produite 
	 */
	public void redo() throws ExceptionPlanCo{
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
	
	/**
	 * Retourne la liste des commandes 
	 * @return une liste des commandes enregistree sous forme d'une LinkedList of Commandes
	 */
	public LinkedList<Commande> getListeCommandes() {
		return liste;
	}
}
