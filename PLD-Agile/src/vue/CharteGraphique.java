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
	
	public static final Color TEXT_SECONDARY_COLOR = new  Color(150,150,150);
	public static final Font TEXT_SECONDARY_FONT = new Font("Arial", Font.PLAIN, 14);
	
	public static final Color TEXT_HANGAR_COLOR = Color.RED;
	public static final Color TEXT_ID_HANGAR_COLOR = Color.RED;
	
	public static final Font TEXT_BIG_FONT = new Font("Arial", Font.PLAIN, 17);

	//BUTTONS
	public static final Color BUTTON_GREEN_BG = new  Color(203,232,186); 
	public static final Color BUTTON_GREEN_BORDER = new  Color(146,211,110); 
	public static final Color BUTTON_WHITE_BG = Color.WHITE; 
	public static final Color BUTTON_WHITE_BORDER = new Color(220,220,220); 
	
	//ICONS
	public static final String ICONE_LIVRAISON = "ressources/map-marker2.png";
	public static final String ICONE_HANGAR = "ressources/hangar-marker.png";
	
	//GRAPH
	public static final Color GRAPH_TRONCON = Color.WHITE;
	public static final Color GRAPH_BG =  new Color(220,220,220);
	

}
