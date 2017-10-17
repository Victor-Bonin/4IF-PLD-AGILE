package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VueHeader extends JPanel{
	private static final long serialVersionUID = 6074161301613552065L;

	private JLabel notificationLabel;
	
	public VueHeader(){
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		notificationLabel = new JLabel();
		JLabel titre = new JLabel("PlanCo");
		
		add(titre, BorderLayout.NORTH);
		add(notificationLabel, BorderLayout.CENTER);
	}

	public void changeNotification(String texte){
		notificationLabel.setText(texte);
	}
}
