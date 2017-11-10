package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * <pre>
 * Cette classe correspond à la vue principale affichant le menu latéral de la tournée
 *  et le plan
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
public class VueCentrale extends JPanel{
	private static final long serialVersionUID = -305055101326859318L;

	private JPanel panneauGauche;
	private JPanel panneauCentre;
	
	public VueCentrale(JPanel vuePlan, JPanel vueTournee){

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		setBackground(CharteGraphique.BUTTON_GREEN_BG);
		setOpaque(true);
		panneauGauche = vueTournee; 
		panneauCentre = vuePlan;

		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(panneauGauche, gbc);
		gbc.weightx = 1;
		gbc.gridx = 1;
		add(panneauCentre, gbc);
		
		//Design 
		setBackground(CharteGraphique.BG_COLOR);
		setBorder(new CompoundBorder(
				new EmptyBorder(0, 50, 0, 50),
				new MatteBorder(1,1,1,1, CharteGraphique.SEPARATOR_DARK_COLOR)));
	}
}
