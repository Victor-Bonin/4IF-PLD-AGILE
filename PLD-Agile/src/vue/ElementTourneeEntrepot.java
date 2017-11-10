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

public class ElementTourneeEntrepot extends ElementTournee{

	private static final long serialVersionUID = 567315132366254821L;
	private JLabel heureArriveeLabel;
	private JLabel heureDepartLabel;
	
	public ElementTourneeEntrepot(Controleur ctrl, Entrepot entrepot) {
		super(ctrl);

		nomLabel.setText(Textes.TOURNEE_ENTREPOT + " - ");
		nomLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
		
		idLabel.setText(String.valueOf(entrepot.getId()));
		idLabel.setForeground(CharteGraphique.TEXT_ID_HANGAR_COLOR);
		
		heureDepartLabel = new JLabel();
		String texte = entrepot.getHeureDepart().get(Calendar.HOUR_OF_DAY) + "h";
    	if(entrepot.getHeureDepart().get(Calendar.MINUTE)<10) {
    		texte += "0";
    	}
    	texte += entrepot.getHeureDepart().get(Calendar.MINUTE) ;
    	heureDepartLabel.setText(texte);
    	heureDepartLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
    	heureDepartLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
    	
    	heureArriveeLabel = new JLabel();
    	if (entrepot.getHeureArrivee() != null) {
	    	texte = entrepot.getHeureArrivee().get(Calendar.HOUR_OF_DAY) + "h";
	    	if(entrepot.getHeureArrivee().get(Calendar.MINUTE)<10) {
	    		texte += "0";
	    	}
	    	texte += entrepot.getHeureArrivee().get(Calendar.MINUTE);
	    	if((entrepot.getHeureArrivee().getTimeInMillis()-entrepot.getHeureDepart().getTimeInMillis())>1000*60*60*24) {
	    		texte += " - " + (entrepot.getHeureArrivee().getTimeInMillis()-entrepot.getHeureDepart().getTimeInMillis())/(1000*60*60*24) + "j aprÃ¨s";
	    		System.out.println(entrepot.getHeureArrivee().get(Calendar.HOUR_OF_DAY) + "  " +entrepot.getHeureDepart().get(Calendar.HOUR_OF_DAY));
	    	}
	    	
	    	heureArriveeLabel.setText(texte);
	    	heureArriveeLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
	    	heureArriveeLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
    	}

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
		
		String description = composeToolTipString(entrepot, listeTronconsIntersection);
		setToolTipText("<html>" + description + "</html>");
    	
		try {
			BufferedImage imgNorm = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR));
			Image scaledImageNormal = imgNorm.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconNormal = new ImageIcon(scaledImageNormal);
			BufferedImage imgSurvol = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR_SURVOL));
			Image scaledImageSurvol = imgSurvol.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconSurvol = new ImageIcon(scaledImageSurvol);
			imageLabel.setIcon(imageIconNormal);
		} catch (IOException e) {
	    		System.out.println("Une image est manquante");
	    }

		infos.add(heureArriveeLabel, BorderLayout.WEST );
		infos.add(heureDepartLabel, BorderLayout.PAGE_START );
		
		menu = new JPopupMenu("Popup");
		JMenuItem item = new JMenuItem("Nouvelle livraison");
		menu.add(item);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.creerLivraisonApres(-1);
			}
		});
		/*
		addMouseListener(new MouseAdapter (){
			public void mousePressed(MouseEvent ev) {
				if (ev.isPopupTrigger()) {
					menu.show(ev.getComponent(), ev.getX(), ev.getY());
				}
			}

			public void mouseReleased(MouseEvent ev) {
				if (ev.isPopupTrigger()) {
					menu.show(ev.getComponent(), ev.getX(), ev.getY());
				}
			}
		});
		*/
	}	
}
