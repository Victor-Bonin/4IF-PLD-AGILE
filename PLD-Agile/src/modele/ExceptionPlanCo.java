package modele;

/**
 * <pre>
 * Represente les exceptions du modele.
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
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
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
 * @author 4104
 */
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
	public static String AUCUNE_SOLUTION = "Aucune solution respectant les contraintes a été trouvée";
	public static String ENTREPOT_INCONNU = "L'entrepôt ne correspond à aucune adresse connue";
	public static String LIVRAISON_DUREE_NEGATIVE = "La livraison possède une durée négative";
	public static String ANNULATION_OUVERTURE_FICHIER = "Annulation";
	public static String PROBLEME_OUVERTURE_FICHIER = "Probleme a l'ouverture du fichier"; 
	public static String DOCUMENT_NON_CONFORME = "Document non conforme";
	public static String ANNULATION_SAUVEGARDE_FEUILLE_DE_ROUTE = "Annulation de la sauvegarde";
	public static String PROBLEME_SAUVEGARDE_FEUILLE_DE_ROUTE = "Probleme lors de la sauvegarde du fichier";
	public static String LONGUEUR_NEGATIVE = "Longueur d'un troncon négative";
	public static String DUREE_NEGATIVE = "Durée d'une livraison négative";
	public static String PLAGE_HORAIRE_INCORRECTE = "Une plage horaire est incorrecte";
	
	/**
	 * Constructeur de l'exception personnalie. 
	 * Elle a juste besoin d'un message.
	 * @param message String
	 */
	public ExceptionPlanCo(String message) {
		super(message);
	}
}
