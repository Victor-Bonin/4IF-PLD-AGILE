package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.NumberFormatter;

import controleur.Controleur;
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
	protected JPopupMenu menu;
		
	protected Controleur controleur;	
	protected JLabel nomLabel;
	protected JLabel idLabel;
	protected JLabel imageLabel;
	protected JPanel infos;
	protected JPanel details;
	
	protected ImageIcon imageIconNormal;
	protected ImageIcon imageIconSurvol;
	
	protected EcouteurDeBoutonsElementTournee ecouteurBoutons;
	
	protected boolean areDetailsVisible = false;
	
	// TODO : mettre tous les JLabel en attribut

	
	public ElementTournee(Controleur ctrl) {
		super();
		
		this.controleur = ctrl;
		
		setOpaque(true);
		setBackground(CharteGraphique.BG_COLOR);
		
		setBorder(new CompoundBorder(
				new EmptyBorder(10, 10, 0, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
						new EmptyBorder(10, 10, 10, 10)
						)
				));
		
		nomLabel = new JLabel();
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		
		idLabel = new JLabel();
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		
		imageLabel = new JLabel();
		
		infos = new JPanel();
		infos.setLayout(new BorderLayout());
		infos.setBackground(CharteGraphique.BG_COLOR);
		
		details = new JPanel();
		details.setBackground(CharteGraphique.BG_COLOR);
		details.setLayout(new BorderLayout());

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 1;
	    c.gridheight = 2;
	    c.insets = new Insets(0,0,0,10);
	    add(imageLabel,c);
	   		
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
		c.fill = GridBagConstraints.VERTICAL;
		add(infos, c);
		
		c.insets = new Insets(10,0,0,0);
		c.gridwidth = 4;
		c.gridy = 2;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(details, c);
	}
	
	
	
	
	protected String composeToolTipString(Intersection intersec, Set<String> listeNomsRues) {
		//TODO : Afficher le nom des rues qui y passent avec
		String s = "";
		if(intersec instanceof Entrepot) {
			s= Textes.TOURNEE_ENTREPOT ;
		}else if(intersec instanceof Livraison) {
			s= Textes.TOURNEE_LIVRAISON ;
		}
		s += intersec.getId() + "<br>"
				+ Textes.TOURNEE_INTERSECTION;
		
		for(String nom : listeNomsRues){
			if (nom.equals("")){
				nom = "Rue Inconnue";
			}
			s += "<br>" + nom;
		}
		/*
		 * Faudra avoir un truc comme Ã§a
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
	
	
	
	public void afficherDetails() {
		if(areDetailsVisible){
			details.setVisible(false);
		} else {
			details.setVisible(true);
		}
		areDetailsVisible = !areDetailsVisible;
	}
	
}
