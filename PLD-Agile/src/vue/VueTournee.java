package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	
	private GridBagConstraints c;
	private JLabel tourneeTitre;
	
	public VueTournee(Controleur ctrl, DemandeLivraison livr){
		super();
		this.ctrl = ctrl;
		demLivraison = livr;
		//JScrollPane scrollPane = new JScrollPane(this);
		//this.add(scrollPane);
		setOpaque(true);
		setBackground(CharteGraphique.BG_COLOR);
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;
	    
	    tourneeTitre = new JLabel(Textes.TITRE_TOURNEE);
	    tourneeTitre.setFont(CharteGraphique.TEXT_BIGGER_FONT);
	    add(tourneeTitre, c);
	}
	
	public void initTournee(DemandeLivraison dem) {
		demLivraison = dem;
	    c.gridy = 1;

		add(new ElementTournee(demLivraison.getEntrepot()), c);
		int i = 0;
		for(Livraison livraison : demLivraison.getLivraisons()) {
			i++;
		    c.gridy = i+1;
			add(new ElementTournee(livraison, i), c);
		}
		c.gridy = i+1;
		c.weighty = 1;
		add(new JLabel(), c);
	}

}
