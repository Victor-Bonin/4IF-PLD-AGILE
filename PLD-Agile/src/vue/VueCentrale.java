package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

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
	
	public VueCentrale(JPanel vuePlan, JPanel vueTournee){
		setLayout(new BorderLayout());

		panneauGauche = vueTournee; // l'avoir en parametre
		panneauCentre = vuePlan;

		add(panneauGauche, BorderLayout.WEST);
		add(panneauCentre, BorderLayout.CENTER);
	}
}
