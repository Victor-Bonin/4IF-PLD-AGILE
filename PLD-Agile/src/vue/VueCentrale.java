package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * Cette classe correspond à la vue principale affichant le menu latéral de la tournée
 *  et le plan
 * 
 * @author 4104
 *
 */
public class VueCentrale extends JPanel{
	private static final long serialVersionUID = -305055101326859318L;

	private JPanel panneauGauche;
	private JPanel panneauCentre;
	//private GridBagConstraints gbc;
	
	public VueCentrale(JPanel vuePlan, JPanel vueTournee){
		//setLayout(new BorderLayout());
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		setBackground(CharteGraphique.BUTTON_GREEN_BG);
		setOpaque(true);
		panneauGauche = vueTournee; // l'avoir en parametre
		panneauCentre = vuePlan;
		/*
		panneauGauche = new JPanel();
		panneauCentre = new JPanel();
		
		panneauGauche.setBackground(Color.RED);
		panneauCentre.setBackground(Color.BLUE);
		*/
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		//gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(panneauGauche, gbc);
		gbc.weightx = 1;
		//gbc.weightx = 0.5;
		gbc.gridx = 1;
		add(panneauCentre, gbc);

		//add(panneauGauche, BorderLayout.WEST);
		//add(panneauCentre, BorderLayout.CENTER);
		
		//Design 
		setBackground(CharteGraphique.BG_COLOR);
		setBorder(new CompoundBorder(
				new EmptyBorder(0, 50, 0, 50),
				new MatteBorder(1,1,1,1, CharteGraphique.SEPARATOR_DARK_COLOR)));
	}
}
