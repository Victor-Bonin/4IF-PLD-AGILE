package vue;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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
		notificationLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		notificationLabel.setBorder(new MatteBorder(1,0,1,0, CharteGraphique.SEPARATOR_COLOR));
		
		notificationPanel.add(notificationLabel);
		
		JLabel titre = new JLabel(Textes.TITRE_APPLI);
		titre.setForeground(CharteGraphique.TITLE_COLOR);
		titre.setFont(new Font("Arial", Font.ITALIC, 30));
		
		add(titre, BorderLayout.NORTH);
		add(notificationPanel, BorderLayout.CENTER);
	}

	public void changeNotification(String texte){
		notificationLabel.setText(texte);
	}
}
