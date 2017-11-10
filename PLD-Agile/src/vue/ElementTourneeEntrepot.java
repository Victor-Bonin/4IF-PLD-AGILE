package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controleur.Controleur;
import modele.Entrepot;

/**
 * <pre>
 * Extension de ElementTournee affichant un element d'une tournee de type Entrepot
 * @see modele.Entrepot
 * @see ElementTournee
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
public class ElementTourneeEntrepot extends ElementTournee{

	private static final long serialVersionUID = 567315132366254821L;
	private JLabel heureArriveeLabel;
	private JLabel heureDepartLabel;
	

	/**
	 * Contructeur d'un ElementTourneeEntrepot
	 * @param ctrl : le controleur associe a la vue
	 * @param entrepot : l'entrepot a afficher
	 */
	public ElementTourneeEntrepot(Controleur ctrl, Entrepot entrepot) {
		super(ctrl);

		nomLabel.setText(Textes.TOURNEE_ENTREPOT + " - ");
		nomLabel.setForeground(CharteGraphique.TEXTE_ENTREPOT_COULEUR);
		
		idLabel.setText(String.valueOf(entrepot.getId()));
		idLabel.setForeground(CharteGraphique.TEXTE_ID_ENTREPOT_COULEUR);
		
		// Mise en forme des heures
		heureDepartLabel = new JLabel();
		String texte = entrepot.getHeureDepart().get(Calendar.HOUR_OF_DAY) + "h";
    	if(entrepot.getHeureDepart().get(Calendar.MINUTE)<10) {
    		texte += "0";
    	}
    	texte += entrepot.getHeureDepart().get(Calendar.MINUTE) ;
    	heureDepartLabel.setText(texte);
    	heureDepartLabel.setForeground(CharteGraphique.TEXTE_ENTREPOT_COULEUR);
    	heureDepartLabel.setFont(CharteGraphique.TEXTE_PETIT_POLICE);
    	
    	heureArriveeLabel = new JLabel();
    	if (entrepot.getHeureArrivee() != null) {
	    	texte = entrepot.getHeureArrivee().get(Calendar.HOUR_OF_DAY) + "h";
	    	if(entrepot.getHeureArrivee().get(Calendar.MINUTE)<10) {
	    		texte += "0";
	    	}
	    	texte += entrepot.getHeureArrivee().get(Calendar.MINUTE);
	    	if((entrepot.getHeureArrivee().getTimeInMillis()-entrepot.getHeureDepart().getTimeInMillis())>1000*60*60*24) {
	    		texte += " - " + (entrepot.getHeureArrivee().getTimeInMillis()-entrepot.getHeureDepart().getTimeInMillis())/(1000*60*60*24) + "j après";
	    	}
	    	
	    	heureArriveeLabel.setText(texte);
	    	heureArriveeLabel.setFont(CharteGraphique.TEXTE_PETIT_POLICE);
	    	heureArriveeLabel.setForeground(CharteGraphique.TEXTE_ENTREPOT_COULEUR);
    	}
    	
    	// Recuperation de la liste des troncons passant par l'intersection de l'entrepot
		Set<String> listeTronconsIntersection = ctrl.nomsTronconsIntersection(entrepot);
		JPanel nomsTronconsIntersection = new JPanel();
		nomsTronconsIntersection.setLayout(new BoxLayout(nomsTronconsIntersection, BoxLayout.PAGE_AXIS));
		nomsTronconsIntersection.setBackground(Color.WHITE);
		for(String nomTroncon : listeTronconsIntersection){
			if (nomTroncon.equals("")){
				nomTroncon = "Rue Inconnue";
			}
			JLabel labelNomTroncon = new JLabel (nomTroncon);
			nomsTronconsIntersection.add(labelNomTroncon);
			labelNomTroncon.setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		details.add(nomsTronconsIntersection, BorderLayout.NORTH);
		details.setVisible(false);
		
		// Creation la popup d'informations
		String description = composeToolTipString(entrepot, listeTronconsIntersection);
		setToolTipText("<html>" + description + "</html>");
    	
		// recuperation l'icone
		try {
			BufferedImage imgNorm = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR));
			Image scaledImageNormal = imgNorm.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconeNormal = new ImageIcon(scaledImageNormal);
			BufferedImage imgSurvol = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR_SURVOL));
			Image scaledImageSurvol = imgSurvol.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconeSurvol = new ImageIcon(scaledImageSurvol);
			imageLabel.setIcon(imageIconeNormal);
		} catch (IOException e) {
	    }

		infos.add(heureArriveeLabel, BorderLayout.WEST );
		infos.add(heureDepartLabel, BorderLayout.PAGE_START );
		
		// Creation d'un menu contextuel pour ajouter une livraison apres
		menu = new JPopupMenu("Popup");
		JMenuItem item = new JMenuItem("Nouvelle livraison");
		menu.add(item);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.creerLivraisonApres(-1);
			}
		});
	}	
}
