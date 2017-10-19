package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modele.Entrepot;
import modele.Livraison;

public class ElementTournee extends JPanel{
	private static final long serialVersionUID = 6534684555513953601L;
	private int numero;
	private long id;
	private Date date;
	private int duree;
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
		
		nomLabel = new JLabel(Textes.TOURNEE_LIVRAISON + numero + " - ");
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		
		idLabel = new JLabel(" " + livraison.getId());
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		
		dureeLivraisonLabel = new JLabel(Textes.TOURNEE_DUREE + (int)(livraison.getDuree()/60) + " min");
		dureeLivraisonLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 1;
	    c.gridheight = 3;
	    c.insets = new Insets(0,0,0,10);
	    
	    /*
    	String texte = "Heure de passage estimée : " + date.getHours() + "h";
    	if(date.getMinutes()<10) {
    		texte += "0";
    	}
    	texte += date.getMinutes();
    	heureLabel = new JLabel(texte);
		heureLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		*/

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
		
		System.out.println(entrepot.getId());
		System.out.println(entrepot.getHeureDepart().getHours());
		System.out.println(entrepot.getHeureDepart().getMinutes());
		
		nomLabel = new JLabel(Textes.TOURNEE_ENTREPOT + " - ");
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		nomLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
		
		idLabel = new JLabel(String.valueOf(entrepot.getId()));
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_ID_HANGAR_COLOR);
		
		String texte = entrepot.getHeureDepart().getHours() + "h";
    	if(entrepot.getHeureDepart().getMinutes()<10) {
    		texte += "0";
    	}
    	texte += entrepot.getHeureDepart().getMinutes();
    	heureLabel = new JLabel(texte);
		heureLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		heureLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
		
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
	}
	
	
	public ElementTournee(int num, long i, Date d, int tpsLiv, boolean isHangar ) {
		super();
		
		setOpaque(true);
		setBackground(CharteGraphique.BG_COLOR);
		
		numero = num;
		id = i;
		date = d;
		duree = tpsLiv;
		
		setBorder(new CompoundBorder(
				new EmptyBorder(10, 10, 0, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
						new EmptyBorder(10, 10, 10, 10)
						)
				));
		
		nomLabel = new JLabel("Livraison " + numero + " - ");
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		
		idLabel = new JLabel(" " + id);
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		
		dureeLivraisonLabel = new JLabel("Durée " + duree + " min");
		dureeLivraisonLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 1;
	    c.gridheight = 3;
	    c.insets = new Insets(0,0,0,10);
	    
	    if(isHangar) {
	    	nomLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
	    	heureLabel = new JLabel();
	    	heureLabel.setForeground(CharteGraphique.TEXT_HANGAR_COLOR);
	    	idLabel.setForeground(CharteGraphique.TEXT_ID_HANGAR_COLOR);
	    } else {
	    	String texte = "Heure de passage estimée : " + date.getHours() + "h";
	    	if(date.getMinutes()<10) {
	    		texte += "0";
	    	}
	    	texte += date.getMinutes();
	    	heureLabel = new JLabel(texte);
			heureLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
	    }

		try {
			BufferedImage img;
			if(isHangar) {
				img = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR));
			} else {
				img = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			}
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
		
		c.gridwidth = 2;
		c.gridy = 2;
		add(dureeLivraisonLabel, c);
	}
	
	
	
}
