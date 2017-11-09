package vue;

import java.awt.Color;
import java.awt.Font;

/**
 * Cette classe contient les constantes liées au style visuel des IHM
 * 
 * @author 4104
 *
 */
public interface CharteGraphique {
	
	
	public static final Color BG_COLOR = Color.WHITE;
	public static final Color SEPARATOR_COLOR = new Color(220,220,220);
	public static final Color SEPARATOR_DARK_COLOR = new Color(150,150,150);

	//TEXTS
	public static final Color TITLE_COLOR = Color.RED;
	public static final Font TITLE_FONT = new Font("Arial", Font.PLAIN, 30);

	public static final Color NOTIFICATION_COLOR = new  Color(150,150,150);
	public static final Color NOTIFICATION_WARNING_COLOR = Color.ORANGE;
	public static final Color NOTIFICATION_FORBIDDEN_COLOR = Color.RED;
	public static final Color NOTIFICATION_OK_COLOR = Color.GREEN;
	
	public static final Color TEXT_SECONDARY_COLOR = new  Color(150,150,150);
	public static final Font TEXT_SECONDARY_FONT = new Font("Arial", Font.PLAIN, 14);
	
	public static final Color TEXT_HANGAR_COLOR = Color.RED;
	public static final Color TEXT_ID_HANGAR_COLOR = Color.RED;

	public static final Font TEXT_SMALL_FONT = new Font("Arial", Font.PLAIN, 10);
	public static final Font TEXT_BIG_FONT = new Font("Arial", Font.PLAIN, 17);
	public static final Font TEXT_BIG_FAT_FONT = new Font("Arial", Font.BOLD, 17);
	public static final Font TEXT_BIGGER_FONT = new Font("Arial", Font.PLAIN, 25);

	//BUTTONS
	public static final Color BUTTON_GREEN_BG = new  Color(203,232,186); 
	public static final Color BUTTON_GREEN_BORDER = new  Color(146,211,110); 
	public static final Color BUTTON_WHITE_BG = Color.WHITE; 
	public static final Color BUTTON_WHITE_BORDER = new Color(220,220,220); 
	
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
	public static final Color GRAPH_TRONCON_WAY = new Color(20,150,20);
	public static final Color GRAPH_BG =  new Color(210,210,210);
	public static final Color GRAPH_TEXT_COLOR =  Color.BLACK;
	
	//LIST
	public static final Color LIVRAISON_RETARD = new Color(250, 120, 100);
}
