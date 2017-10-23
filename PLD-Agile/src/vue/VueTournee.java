package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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
	private JPanel pan;
	
	public VueTournee(Controleur ctrl, DemandeLivraison livr){
		super();
		this.ctrl = ctrl;
		demLivraison = livr;
		
		setBackground(CharteGraphique.BG_COLOR);
		setLayout(new GridBagLayout());

		c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		c.gridx = 0;
	    c.gridy = 0;
		
		tourneeTitre = new JLabel(Textes.TITRE_TOURNEE);
	    tourneeTitre.setFont(CharteGraphique.TEXT_BIGGER_FONT);
		add(tourneeTitre, c);
		tourneeTitre.setBorder(new EmptyBorder(20, 20, 0, 145));
		

		c.weighty = 1;
		c.gridy = 1;
		pan = new JPanel();
		setBackground(CharteGraphique.BG_COLOR);
		JScrollPane scrollPane = new JScrollPane(pan);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane,c);
		
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                
		pan.setBackground(CharteGraphique.BG_COLOR);
		
		
	}
	
	/**
	 * Actualise le panneau des tournées selon la DemandeLivraison donnee en parametre (peut être une tournee)
	 * @param dem DemandeLivraison qui doit être représentée
	 */
	public void initTournee(DemandeLivraison dem) {
		demLivraison = dem;
		
		pan.removeAll();
		pan.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;

		pan.add(new ElementTournee(demLivraison.getEntrepot()), c);
		int i = 0;
		for(Livraison livraison : demLivraison.getLivraisons()) {
			i++;
		    c.gridy = i;
		    pan.add(new ElementTournee(livraison, i), c);
		}
		c.gridy = i+1;
		c.weighty = 1;
		pan.add(new JLabel(), c);
	}

}
