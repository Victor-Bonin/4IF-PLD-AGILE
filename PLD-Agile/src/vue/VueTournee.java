package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.DemandeLivraison;
import modele.Livraison;

/**
 * Cette classe correspond à la vue des tournées
 * 
 * @author 4104
 *
 */
public class VueTournee extends JPanel{
	private static final long serialVersionUID = 5007192571949757684L;

	private Controleur ctrl;
	
	private DemandeLivraison demLivraison;
	
	public VueTournee(Controleur ctrl, DemandeLivraison livr){
		super();
		this.ctrl = ctrl;
		demLivraison = livr;
		setOpaque(true);
		setBackground(CharteGraphique.BG_COLOR);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
	    c.gridy = 0;
	    add(new ElementTournee(2, 648465, new Date(2017, 10, 19, 8, 1), 5, true),c);
	    c.weighty = 1;
	    c.gridy = 1;
	    add(new ElementTournee(3, 648465, new Date(2017, 10, 19, 8, 1), 5, false),c);
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.add(new ElementTournee(2, 648465, 45, 5));
		//this.add(new ElementTournee(3, 648465, 45, 5));
	}
	
	private void initTournee() {
		for(Livraison livraison : demLivraison.getLivraisons()) {
			this.add(new ElementTournee(2, 648465, new Date(2017, 10, 19, 8, 1), 5, false));
		}
	}

}
