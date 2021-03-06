package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * <pre>
 * Cette classe correspond à la vue affichée en haut avec le titre et le 
 * texte de notification
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
public class VueHeader extends JPanel{
	private static final long serialVersionUID = 6074161301613552065L;

	private JLabel notificationLabel;
	
	/**
	 * Constructeur d'une nouvelle en-tete
	 */
	public VueHeader(){
		setBorder(new EmptyBorder(10, 20, 10, 20));
		setLayout(new BorderLayout());
		setBackground(CharteGraphique.BG_COULEUR);
		
		JPanel notificationPanel = new JPanel();
		notificationPanel.setLayout(new BorderLayout());
		notificationPanel.setBorder(new EmptyBorder(10, 20, 10, 0));
		notificationPanel.setBackground(CharteGraphique.BG_COULEUR);
		
		notificationLabel = new JLabel();
		notificationLabel.setFont(CharteGraphique.TEXT_SECONDAIRE_POLICE);
		notificationLabel.setForeground(CharteGraphique.TEXT_SECONDAIRE_COULEUR);
		notificationLabel.setBorder(new CompoundBorder(
				new MatteBorder(1,0,1,0, CharteGraphique.SEPARATEUR_COULEUR),
				new EmptyBorder(10, 10, 10, 10)
				));
		
		notificationPanel.add(notificationLabel);
		
		JLabel titre = new JLabel(Textes.TITRE_APPLI);
		titre.setForeground(CharteGraphique.TITRE_COULEUR);
		titre.setFont(CharteGraphique.TITRE_POLICE);
		
		add(titre, BorderLayout.NORTH);
		add(notificationPanel, BorderLayout.CENTER);
	}

	/**
	 * Change la notification située dans l'en-tete afin de renseigner et avertir l'utilisateur
	 * @param texte : le texte a afficher
	 * @param color : la couleur du texte a afficher
	 */
	public void changeNotification(String texte, Color color){
		notificationLabel.setForeground(color);
		notificationLabel.setText(texte);
	}
}
