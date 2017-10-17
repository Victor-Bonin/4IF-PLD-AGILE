package vue;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * Cette classe correspond à la vue affichée en haut avec le titre et le 
 * texte de notification
 * 
 * @author 4104
 *
 */
public class VueHeader extends JPanel{
	private static final long serialVersionUID = 6074161301613552065L;

	private JLabel notificationLabel;
	
	public VueHeader(){
		setBorder(new EmptyBorder(10, 20, 10, 20));
		setLayout(new BorderLayout());
		setBackground(CharteGraphique.BG_COLOR);
		
		JPanel notificationPanel = new JPanel();
		notificationPanel.setLayout(new BorderLayout());
		notificationPanel.setBorder(new EmptyBorder(10, 20, 10, 0));
		notificationPanel.setBackground(CharteGraphique.BG_COLOR);
		
		notificationLabel = new JLabel();
		notificationLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		notificationLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		notificationLabel.setBorder(new CompoundBorder(
				new MatteBorder(1,0,1,0, CharteGraphique.SEPARATOR_COLOR),
				new EmptyBorder(10, 10, 10, 10)
				));
		
		notificationPanel.add(notificationLabel);
		
		JLabel titre = new JLabel(Textes.TITRE_APPLI);
		titre.setForeground(CharteGraphique.TITLE_COLOR);
		titre.setFont(CharteGraphique.TITLE_FONT);
		
		add(titre, BorderLayout.NORTH);
		add(notificationPanel, BorderLayout.CENTER);
	}

	public void changeNotification(String texte){
		notificationLabel.setText(texte);
	}
}
