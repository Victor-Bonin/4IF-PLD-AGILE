package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class VueCentrale extends JPanel{
	private static final long serialVersionUID = -305055101326859318L;

	private JPanel panneauGauche;
	private JPanel panneauCentre;
	
	public VueCentrale(JPanel vuePlan){
		setLayout(new BorderLayout());
		
		panneauGauche = new JPanel();
		panneauCentre = vuePlan;
		
		panneauGauche.setBackground(Color.GRAY);
		panneauCentre.setBackground(Color.BLACK);

		add(panneauGauche, BorderLayout.WEST);
		add(panneauCentre, BorderLayout.CENTER);
	}
}
