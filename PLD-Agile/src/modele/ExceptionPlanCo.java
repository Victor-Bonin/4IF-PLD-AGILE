/**
 * @author 4104
 */
package modele;

public class ExceptionPlanCo extends Exception {
	private static final long serialVersionUID = 6735829319293468263L;

	public static String DEV_ONLY_1 = "Dev ONLY : PdL null ajt 0xce";
	public static String DEV_ONLY_2 = "Dev ONLY : PdL out of bounds ajt 0xce";
	public static String DEV_ONLY_3 = "Dev ONLY : PdL null sppr 0xce";
	public static String LIVRAISON_DEJA_PRESENTE = "Point de livraison déjà dans la demande de livraison";
	public static String ERREUR_AJOUT_LIVRAISON = "Erreur lors de l'ajout de la livraison";
	public static String LIVRAISON_ABSENTE = "Point de livraison non présent dans la demande de livraison";
	public static String ERREUR_SUPPRESSION_LIVRAISON = "Erreur lors de la suppression de la livraison";
	public static String INTERSECTION_ABSENTE = "Les intersections de depart et/ou de fin pour ce troncon ne sont pas presentes dans le plan.";
	public static String SOLUTION_VIDE = "Solution ou tournee vide !";
	public static String AUCUNE_SOLUTION = "Aucune solution respectant les contraintes n'a pas été trouvée";
	public static String ENTREPOT_INCONNU = "L'entrepôt ne correspond à aucune adresse connue";
	public static String LIVRAISON_DUREE_NEGATIVE = "La livraison possède une durée négative";
	public static String ANNULATION_OUVERTURE_FICHIER = "Annulation";
	public static String PROBLEME_OUVERTURE_FICHIER = "Probleme a l'ouverture du fichier"; 
	public static String DOCUMENT_NON_CONFORME = "Document non conforme";
	
	/**
	 * Exception personnalisee pour le modele de PlanCo
	 * @param message
	 */
	public ExceptionPlanCo(String message) {
		super(message);
	}
}
