package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modele.Entrepot;
import modele.Intersection;
import modele.Livraison;
import modele.LivraisonPlageHoraire;

/**
 * Extension de JPanel affichant une tournee et ses informations primaires
 * 
 * @author 4104
 *
 */
public class ElementTournee extends JPanel{
	private static final long serialVersionUID = 6534684555513953601L;
	private Calendar date;
	private JLabel nomLabel;
	private JLabel idLabel;
	private JLabel heureLabel;
	private JLabel dureeLivraisonLabel;
	
	public ElementTournee(Livraison livraison, int numero) {
		super();
		
		setOpaque(true);
		setBackground(CharteGraphique.BG_COLOR);
		
		setBorder(new CompoundBorder(
				new EmptyBorder(10, 10, 0, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
						new EmptyBorder(10, 10, 10, 10)
						)
				));
		
		
		String description = composeToolTipString(livraison);
		setToolTipText("<html>" + description + "</html>");
		
		
		nomLabel = new JLabel(Textes.TOURNEE_LIVRAISON + numero + " - ");
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		
		idLabel = new JLabel(" " + livraison.getId());
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		
		dureeLivraisonLabel = new JLabel(Textes.TOURNEE_DUREE + (int)(livraison.getDuree()/60) + " min");
		dureeLivraisonLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 1;
	    c.gridheight = 3;
	    c.insets = new Insets(0,0,0,10);
	    
	    if (livraison.getHeurePassage() != null) {
		    date = livraison.getHeurePassage();
	    	String texte = "Heure de passage estimée : " + date.get(Calendar.HOUR_OF_DAY) + "h";
	    	if(date.get(Calendar.MINUTE)<10) {
	    		texte += "0";
	    	}
	    	texte += date.get(Calendar.MINUTE);
	    	heureLabel = new JLabel(texte);
			heureLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
	    }

		try {
			BufferedImage img = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			Image scaledImage = img.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(scaledImage);
			JLabel imageLabel = new JLabel(imageIcon);
			add(imageLabel,c);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		c.insets = new Insets(0,0,0,0);
		c.gridheight = 1;
		c.gridx = 1;
		add(nomLabel,c);
		
		c.gridx = 2;
		add(idLabel,c);
		
		c.gridwidth = 2;
		c.gridy = 1;
		c.gridx = 1;
		add(dureeLivraisonLabel, c);
		
		if(livraison.getHeurePassage() != null) {
			c.gridwidth = 2;
			c.gridy = 2;
			c.gridx = 1;
			add(heureLabel, c);
		}
	}
	
	public ElementTournee(Entrepot entrepot) {
		super();
		
		setOpaque(true);
		setBackground(CharteGraphique.BG_COLOR);
		
		setBorder(new CompoundBorder(
				new EmptyBorder(10, 10, 0, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
						new EmptyBorder(10, 10, 10, 10)
						)
				));
		
		String description = composeToolTipString(entrepot);
		
		setToolTipText("<html>" + description + "</html>");
		
		nomLabel = new JLabel(Textes.TOURNEE_ENTREPOT + " - ");
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		nomLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
		
		idLabel = new JLabel(String.valueOf(entrepot.getId()));
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_ID_HANGAR_COLOR);
		
		String texte = entrepot.getHeureDepart().get(Calendar.HOUR_OF_DAY) + "h";
    	if(entrepot.getHeureDepart().get(Calendar.MINUTE)<10) {
    		texte += "0";
    	}
    	texte += entrepot.getHeureDepart().get(Calendar.MINUTE) ;
    	
    	heureLabel = new JLabel(texte);
		heureLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
		heureLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
		
    	if (entrepot.getHeureArrivee() != null) {
	    	texte = entrepot.getHeureArrivee().get(Calendar.HOUR_OF_DAY) + "h";
	    	if(entrepot.getHeureArrivee().get(Calendar.MINUTE)<10) {
	    		texte += "0";
	    	}
	    	texte += entrepot.getHeureArrivee().get(Calendar.MINUTE);
	    	if((entrepot.getHeureArrivee().getTimeInMillis()-entrepot.getHeureDepart().getTimeInMillis())>1000*60*60*24) {
	    		texte += " - " + (entrepot.getHeureArrivee().getTimeInMillis()-entrepot.getHeureDepart().getTimeInMillis())/(1000*60*60*24) + "j après";
	    		System.out.println(entrepot.getHeureArrivee().get(Calendar.HOUR_OF_DAY) + "  " +entrepot.getHeureDepart().get(Calendar.HOUR_OF_DAY));
	    	}
	    	
	    	dureeLivraisonLabel = new JLabel(texte);
			dureeLivraisonLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
			dureeLivraisonLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
    	}

    	
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 1;
	    c.gridheight = 3;
	    c.insets = new Insets(0,0,0,10);


		try {
			BufferedImage img = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR));
			Image scaledImage = img.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(scaledImage);
			JLabel imageLabel = new JLabel(imageIcon);
			add(imageLabel,c);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		c.insets = new Insets(0,0,0,0);
		c.gridheight = 1;
		c.gridx = 1;
		add(nomLabel,c);
		
		c.gridx = 2;
		add(idLabel,c);
		
		c.gridwidth = 2;
		c.gridy = 1;
		c.gridx = 1;
		add(heureLabel, c);
		
		if(entrepot.getHeureArrivee() != null) {
			c.gridwidth = 2;
			c.gridy = 2;
			c.gridx = 1;
			add(dureeLivraisonLabel, c);
		}
	}	
	
	private String composeToolTipString(Intersection intersec) {
		//TODO : Afficher le nom des rues qui y passent avec
		String s = "";
		if(intersec instanceof Entrepot) {
			s= Textes.TOURNEE_ENTREPOT ;
		}else if(intersec instanceof Livraison) {
			s= Textes.TOURNEE_LIVRAISON ;
		}
		s += intersec.getId() + "<br>"
				+ Textes.TOURNEE_INTERSECTION;
		/*
		 * Faudra avoir un truc comme ça
		 * 
		 * 
		 * liste = entrepot.getRues();
		 * 
		 * ou
		 * 
		 * liste = plan.getRues(entrepot);
		 * 
		 * for(i:liste)
		 * 		description += "<br> - " + i;
		 */

		if(intersec instanceof LivraisonPlageHoraire) {
			s+= "<br> " + Textes.TOURNEE_PLAGE;
			LivraisonPlageHoraire livraison = (LivraisonPlageHoraire)intersec;
			if(livraison.getDebut()!= null)
				s+=  livraison.getDebut().get(Calendar.HOUR_OF_DAY);
			else
				s+= ".";
			s+= " - ";
			if(livraison.getFin()!= null)
				s+= livraison.getFin().get(Calendar.HOUR_OF_DAY);
			else
				s+= ".";
			
		}
		return s;
	}
}
