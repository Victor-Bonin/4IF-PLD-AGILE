package vue;

/**
 * <pre>
 * Cette classe contient les constantes liées au texte affiché dans les IHM
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
public interface Textes {
	public static final String NOM_APPLI = "PlanCo"; //Nom de la fenêtre
	
	public static final String TITRE_APPLI = "PlanCo"; //Grand titre affiché
	

	public static final String NOTIF_LOADING = "Chargement en cours...";
	public static final String NOTIF_MUST_IMPORT = "Vous devez importer un plan avant de continuer";
	public static final String NOTIF_MUST_IMPORT_DEMANDE = "Vous pouvez maintenant importer une demande de livraison";
	public static final String NOTIF_MUST_CALCUL_TOURNEE = "Vous pouvez maintenant calculer votre tournée";
	public static final String NOTIF_IMPORT_PLAN_FAILED = "Echec lors de l'import du plan. Vérifier votre fichier.";
	public static final String NOTIF_IMPORT_DEMANDE_LIVRAISON_FAILED = "Echec lors de l'import de la demande de livraison. Vérifier votre fichier.";
	public static final String NOTIF_TOURNEE_CALCULE = "Vous pouvez maintenant éditer votre tournée ou exporter une feuille de route";
	public static final String NOTIF_ITINERAIRE_CALCULE = "Itinéraire mis à jour, vous pouvez toujours éditer votre tournée ou exporter une feuille de route";
	public static final String NOTIF_LIVRAISON_AJOUTEE = "Une livraison a été ajoutée à l'itinéraire.";
	public static final String NOTIF_LIVRAISON_SUPPRIMEE = "Une livraison a été supprimée de l'itinéraire.";
	public static final String NOTIF_CALCUL_TOURNEE = "Calcul en cours...";
	public static final String NOTIF_CALCUL_TOURNEE_FAILED = "Echec lors du calcul de votre tournée";
	public static final String NOTIF_CALCUL_ITINERAIRE = "Calcul de l'itinéraire en cours...";
	public static final String NOTIF_CALCUL_ITINERAIRE_FAILED = "Echec lors du calcul de votre itinéraire";
	public static final String NOTIF_UNDO = "Action annulée";
	public static final String NOTIF_REDO = "Action refaite";
	public static final String NOTIF_FDR_EXPORTEE = "La feuille de route a été exportée";
	
	
	public static final String BUTTON_EXPORT_ROUTE = "Exporter la feuille de route";
	public static final String BUTTON_IMPORT_DEMANDE_LIVRAISON = "Importer une demande de livraison";
	public static final String BUTTON_IMPORT_PLAN = "Importer un plan";
	public static final String BUTTON_NOUVEAU_PLAN = "Changer de plan";
	public static final String BUTTON_NOUVELLE_LIVRAISON = "<div style='text-align:center'>Importer nouvelle <br>demande de livraison</div>";
	public static final String BUTTON_CALCUL_TOURNEE = "Calculer la tournée";
	public static final String BUTTON_UNDO = "Undo";
	public static final String BUTTON_REDO = "Redo";
	
	public static final String TITRE_TOURNEE = "Tournée :";
	public static final String TOURNEE_LIVRAISON = "Livraison ";
	public static final String TOURNEE_LIVRAISON_NOUVELLE = "Nouvelle livraison ";
	public static final String TOURNEE_ENTREPOT = "Entrepot ";
	public static final String TOURNEE_DUREE = "Durée ";
	public static final String TOURNEE_DUREE_CREATION = "Durée en min :";
	public static final String TOURNEE_INTERSECTION = "Intersection de : ";
	public static final String TOURNEE_PLAGE = "Plage horaire : ";
	public static final String TOURNEE_PASSAGE = "Passage estimé : ";
	public static final String ATTENTE = "Attente : ";
}
