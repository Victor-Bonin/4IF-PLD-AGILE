package vue;

import javax.swing.JButton;

/**
 * <pre>
 * Extension de JButton pour afficher un bouton personnalise
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
public class PersoButton extends JButton{
	private static final long serialVersionUID = 6534684555513953601L;
	
	/**
	 * Constructeur d'un JButton personnalise
	 * @param str : le texte du bouton
	 * @param style : le style a appliquer (1: vert, 2: blanc)
	 */
	public PersoButton(String str, int style){
		super(str);
		switch(style){
		case 1:
			setBackground(CharteGraphique.BOUTON_VERT_BG);
			break;
		case 2:
			setBackground(CharteGraphique.BOUTON_BLANC_BG);
			break;
		}
		setFocusPainted(false);
		setFont(CharteGraphique.TEXT_SECONDAIRE_POLICE);
	}	
}
