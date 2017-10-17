package vue;

import javax.swing.JPanel;

import modele.DemandeLivraison;

/**
 * Cette classe correspond à la vue des tournées
 * 
 * @author 4104
 *
 */
public class VueTournee extends JPanel{
	private static final long serialVersionUID = 5007192571949757684L;
	
	private DemandeLivraison livraison;
	
	public VueTournee(){
		setBackground(CharteGraphique.TITLE_COLOR);
	}

}
