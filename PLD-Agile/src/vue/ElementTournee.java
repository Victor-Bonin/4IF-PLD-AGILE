package vue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
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
	private JLabel imageLabel;
	
	private ImageIcon imageIconNormal;
	private ImageIcon imageIconSurvol;
	
	// TODO : mettre tous les JLabel en attribut
	
	private int place;
	
	private boolean isSelected = false;
	
	public ElementTournee(Livraison livraison, int nom, int p) {
		super();
		
		place = p;
		
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
		
		
		nomLabel = new JLabel(Textes.TOURNEE_LIVRAISON + nom + " - ");
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
	    	String texte = Textes.TOURNEE_PASSAGE + date.get(Calendar.HOUR_OF_DAY) + "h";
	    	if(date.get(Calendar.MINUTE)<10) {
	    		texte += "0";
	    	}
	    	texte += date.get(Calendar.MINUTE);
	    	heureLabel = new JLabel(texte);
			heureLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
	    }

		try {
			BufferedImage imgNorm = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			Image scaledImageNormal = imgNorm.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconNormal = new ImageIcon(scaledImageNormal);
			BufferedImage imgSurvol = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON_SURVOL));
			Image scaledImageSurvol = imgSurvol.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconSurvol = new ImageIcon(scaledImageSurvol);
			imageLabel = new JLabel(imageIconNormal);
			add(imageLabel,c);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		c.insets = new Insets(0,0,0,0);
		c.gridheight = 1;
		c.gridx = 1;
		add(nomLabel,c);
		
		c.weightx = 1;
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
			BufferedImage imgNorm = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR));
			Image scaledImageNormal = imgNorm.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconNormal = new ImageIcon(scaledImageNormal);
			BufferedImage imgSurvol = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR_SURVOL));
			Image scaledImageSurvol = imgSurvol.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconSurvol = new ImageIcon(scaledImageSurvol);
			imageLabel = new JLabel(imageIconNormal);
			add(imageLabel,c);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		c.insets = new Insets(0,0,0,0);
		c.gridheight = 1;
		c.gridx = 1;
		add(nomLabel,c);
		
		c.weightx = 1;
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
	
	public ElementTournee(int nom, int p) {
		super();
		
		place = p;
		
		setOpaque(true);
		setBackground(CharteGraphique.BG_COLOR);
		
		setBorder(new CompoundBorder(
				new EmptyBorder(10, 10, 0, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
						new EmptyBorder(10, 10, 10, 10)
						)
				));
		
		
		
		nomLabel = new JLabel(Textes.TOURNEE_LIVRAISON + nom + " - ");
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		
		idLabel = new JLabel("");
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		
		dureeLivraisonLabel = new JLabel(Textes.TOURNEE_DUREE + "- min");
		dureeLivraisonLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
		
		heureLabel = new JLabel(Textes.TOURNEE_PASSAGE + "-");
		heureLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
		
		
		// Création du JPanel de modification de la livraison
		JPanel details = new JPanel();
		details.setBackground(CharteGraphique.BG_COLOR);
		details.setLayout(new BorderLayout());
		
		JPanel choixIntersec = new JPanel();
		choixIntersec.setLayout(new BorderLayout());
		choixIntersec.setBackground(CharteGraphique.BG_COLOR);
		JLabel texteChoixIntersec = new JLabel(Textes.TOURNEE_INTERSECTION);
		JButton boutonChoixIntersec = new JButton();
		texteChoixIntersec.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		boutonChoixIntersec.setFocusPainted(false);
		boutonChoixIntersec.setBackground(CharteGraphique.BG_COLOR);
		try {
			BufferedImage bouton = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON_BOUTON));
			Image scaledBouton = bouton.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon boutonIcon = new ImageIcon(scaledBouton);
			boutonChoixIntersec.setIcon(boutonIcon);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		JPanel choixDuree = new JPanel();
		choixDuree.setLayout(new BorderLayout());
		choixDuree.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		choixDuree.setBackground(CharteGraphique.BG_COLOR);
		SpinnerModel modele =
		        new SpinnerNumberModel(0, //initial value
		                               0, //min
		                               10000, //max
		                               1);                //step
		JSpinner dureeModification = new JSpinner(modele);
		JLabel texteModifDuree = new JLabel(Textes.TOURNEE_DUREE);
		texteModifDuree.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		
		choixIntersec.add(texteChoixIntersec, BorderLayout.WEST);
		choixIntersec.add(boutonChoixIntersec, BorderLayout.EAST);
		choixDuree.add(texteModifDuree, BorderLayout.WEST);
		choixDuree.add(dureeModification, BorderLayout.EAST);
		details.add(choixIntersec, BorderLayout.PAGE_START);
		details.add(choixDuree, BorderLayout.PAGE_END);
		
		
		// Ajout à l'ElementTournee
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 1;
	    c.gridheight = 3;
	    c.insets = new Insets(0,0,0,10);
	    
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
		
		c.weightx = 1;
		c.gridx = 2;
		add(idLabel,c);
		
		c.gridwidth = 2;
		c.gridy = 1;
		c.gridx = 1;
		add(dureeLivraisonLabel, c);
		
		c.gridwidth = 2;
		c.gridy = 2;
		c.gridx = 1;
		add(heureLabel, c);
		
		c.insets = new Insets(10,0,0,0);
		c.gridwidth = 3;
		c.gridy = 3;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(details, c);
		
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
	
	public void survolElement(){
		imageLabel.setIcon(imageIconSurvol);
	}
	
	public void antiSurvolElement(){
		imageLabel.setIcon(imageIconNormal);
	}
	
}
