package vue;

import java.awt.Color;
import java.awt.Font;

/**
 * Cette classe contient les constantes liées au style visuel des IHM
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
 * 
 * 
 * @author 4104
 */
public interface CharteGraphique {
	
	
	public static final Color BG_COULEUR = Color.WHITE;
	public static final Color SEPARATEUR_COULEUR = new Color(220,220,220);
	public static final Color SEPARATEUR_SOMBRE_COULEUR = new Color(150,150,150);
	public static final Color LIVRAISON_SELECTIONNEE = new Color(244, 255, 255);

	//TEXTS
	public static final Color TITRE_COULEUR = Color.RED;
	public static final Font TITRE_POLICE = new Font("Arial", Font.PLAIN, 30);

	public static final Color NOTIFICATION_COULEUR = new  Color(150,150,150);
	public static final Color NOTIFICATION_AVERTISSEMENT_COULEUR = Color.ORANGE;
	public static final Color NOTIFICATION_INTERDIT_COULEUR = Color.RED;
	public static final Color NOTIFICATION_OK_COULEUR = Color.GREEN;
	
	public static final Color TEXT_SECONDAIRE_COULEUR = new  Color(150,150,150);
	public static final Font TEXT_SECONDAIRE_POLICE = new Font("Arial", Font.PLAIN, 14);
	
	public static final Color TEXTE_ENTREPOT_COULEUR = Color.RED;
	public static final Color TEXTE_ID_ENTREPOT_COULEUR = Color.RED;

	public static final Font TEXTE_PETIT_POLICE = new Font("Arial", Font.PLAIN, 10);
	public static final Font TEXTE_GRAND_POLICE = new Font("Arial", Font.PLAIN, 17);
	public static final Font TEXTE_GRAND_GRAS_POLICE = new Font("Arial", Font.BOLD, 17);
	public static final Font TEXTE_PLUS_GRAND_POLICE = new Font("Arial", Font.PLAIN, 25);

	//BUTTONS
	public static final Color BOUTON_VERT_BG = new  Color(203,232,186); 
	public static final Color BOUTON_VERT_CONTOUR = new  Color(146,211,110); 
	public static final Color BOUTON_BLANC_BG = Color.WHITE; 
	public static final Color BOUTON_BLANC_CONTOUR = new Color(220,220,220); 
	
	//ICONS
	public static final String ICONE_LIVRAISON = "ressources/map-marker2.png";
	public static final String ICONE_LIVRAISON_SURVOL = "ressources/map-marker3.png";
	public static final String ICONE_HANGAR = "ressources/entrepot-marker.png";
	public static final String ICONE_LIVRAISON_BOUTON = "ressources/map-marker-button.png";
	public static final String ICONE_HANGAR_SURVOL = "ressources/entrepot-marker2.png";
	public static final String ICONE_VALIDER = "ressources/valider.png";
	public static final String ICONE_SUPPRIMER = "ressources/supprimer.png";
	public static final String ICONE_ANNULER = "ressources/effacer.png";
	public static final String ICONE_RETOUR_AVANT = "ressources/retour-avant.png";
	public static final String ICONE_RETOUR_ARRIERE = "ressources/retour-arriere.png";

	
	//GRAPH
	public static final Color GRAPH_TRONCON = Color.WHITE;
	public static final Color GRAPH_TRONCON_CHEMIN = new Color(20,150,20);
	public static final Color GRAPH_BG =  new Color(210,210,210);
	public static final Color GRAPH_TEXTE_COULEUR =  Color.BLACK;
	
	//LIST
	public static final Color LIVRAISON_RETARD = new Color(250, 120, 100);
}
