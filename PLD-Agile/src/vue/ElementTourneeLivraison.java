package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

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
import javax.swing.text.NumberFormatter;

import controleur.Controleur;
import modele.Intersection;
import modele.Livraison;

public class ElementTourneeLivraison extends ElementTournee{
	
	private Livraison livraison;
	
	// TODO : A supprimer?
	private Calendar date;
	private int place;
	
	private JPopupMenu menu;
	private JLabel dureeLivraisonLabel;
	private JButton boutonAction;
	private JLabel heureLabel;
	private JSpinner dureeModification;
	
	public ElementTourneeLivraison(Controleur ctrl, Livraison livraison, int nom, int p) {
		super(ctrl);
		
		this.place = p;
		this.livraison = livraison;
		
		initialiserLivraison();
		
		nomLabel.setText(Textes.TOURNEE_LIVRAISON + nom + " - ");
		idLabel.setText(" " + livraison.getId());
		dureeLivraisonLabel.setText(Textes.TOURNEE_DUREE + (int)(livraison.getDuree()/60) + " min");

		// JPanel details
		try {
			BufferedImage img = ImageIO.read(new File(CharteGraphique.ICONE_SUPPRIMER));
			Image scaledSupprimer = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon supprimerIcon = new ImageIcon(scaledSupprimer);
			boutonAction.setIcon(supprimerIcon);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		details.add(boutonAction, BorderLayout.EAST);
		
		
		Set<String> listeTronconsIntersection = ctrl.nomsTronconsIntersection(livraison);
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
		
		String description = composeToolTipString(livraison, listeTronconsIntersection);
		setToolTipText("<html>" + description + "</html>");
		
	    if (livraison.getHeurePassage() != null) {
		    date = livraison.getHeurePassage();
	    	String texte = Textes.TOURNEE_PASSAGE + date.get(Calendar.HOUR_OF_DAY) + "h";
	    	if(date.get(Calendar.MINUTE)<10) {
	    		texte += "0";
	    	}
	    	texte += date.get(Calendar.MINUTE);
	    	heureLabel.setText(texte);
	    }

		ecouteurBoutons = new EcouteurDeBoutonsElementTournee(ctrl, this);
		boutonAction.addActionListener(ecouteurBoutons);
		boutonAction.setActionCommand("supprimer-livraison");
		
		menu = new JPopupMenu("Popup");
		JMenuItem item = new JMenuItem("Nouvelle livraison");
		menu.add(item);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.creerLivraison();
			}
	    });
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
	}
	
	public ElementTourneeLivraison(Controleur ctrl, int nom, int p) {
		super(ctrl);
		
		initialiserLivraison();
		
		nomLabel.setText(Textes.TOURNEE_LIVRAISON + nom + " - ");
		idLabel.setText("");
		dureeLivraisonLabel.setText(Textes.TOURNEE_DUREE + "- min");
		heureLabel.setText(Textes.TOURNEE_PASSAGE + "-");
		
		JButton boutonAnnuler = new JButton();
		boutonAnnuler.setFocusPainted(false);
		boutonAnnuler.setBackground(CharteGraphique.BG_COLOR);
		boutonAnnuler.setBorder(null);
		try {
			BufferedImage img = ImageIO.read(new File(CharteGraphique.ICONE_ANNULER));
			Image scaledAnnuler = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon annulerIcon = new ImageIcon(scaledAnnuler);
			boutonAnnuler.setIcon(annulerIcon);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		try {
			BufferedImage img = ImageIO.read(new File(CharteGraphique.ICONE_VALIDER));
			Image scaledValider = img.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon validerIcon = new ImageIcon(scaledValider);
			boutonAction.setIcon(validerIcon);
			
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		boutonAction.setEnabled(false);
		
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
		dureeModification = new JSpinner(modele);
		JFormattedTextField duree = ((JSpinner.NumberEditor) dureeModification.getEditor()).getTextField();
		((NumberFormatter) duree.getFormatter()).setAllowsInvalid(false);
		
		JLabel texteModifDuree = new JLabel(Textes.TOURNEE_DUREE);
		texteModifDuree.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		
		choixIntersec.add(texteChoixIntersec, BorderLayout.WEST);
		choixIntersec.add(boutonChoixIntersec, BorderLayout.EAST);
		choixDuree.add(texteModifDuree, BorderLayout.WEST);
		choixDuree.add(dureeModification, BorderLayout.EAST);
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		pan.add(choixIntersec, BorderLayout.PAGE_START);
		pan.add(choixDuree, BorderLayout.PAGE_END);
		details.add(pan, BorderLayout.PAGE_START);
		details.add(boutonAction, BorderLayout.EAST);
		
		
		ecouteurBoutons = new EcouteurDeBoutonsElementTournee(ctrl, this);
		boutonChoixIntersec.addActionListener(ecouteurBoutons);
		boutonChoixIntersec.setActionCommand("choisir-intersection");
		boutonAction.addActionListener(ecouteurBoutons);
		boutonAction.setActionCommand("valider-creation");
		boutonAnnuler.addActionListener(ecouteurBoutons);
		boutonAnnuler.setActionCommand("annuler-creation");
		
	}
	
	private void initialiserLivraison() {
		
		dureeLivraisonLabel = new JLabel();
		
		nomLabel.setFont(CharteGraphique.TEXT_BIG_FONT);
		idLabel.setFont(CharteGraphique.TEXT_SECONDARY_FONT);
		idLabel.setForeground(CharteGraphique.TEXT_SECONDARY_COLOR);
		dureeLivraisonLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
		
		heureLabel = new JLabel();
		heureLabel.setFont(CharteGraphique.TEXT_SMALL_FONT);
		
		boutonAction = new JButton();
		boutonAction.setFocusPainted(false);
		boutonAction.setBackground(CharteGraphique.BG_COLOR);
		boutonAction.setBorder(null);
		
		try {
			BufferedImage imgNorm = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			Image scaledImageNormal = imgNorm.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconNormal = new ImageIcon(scaledImageNormal);
			BufferedImage imgSurvol = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON_SURVOL));
			Image scaledImageSurvol = imgSurvol.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			imageIconSurvol = new ImageIcon(scaledImageSurvol);
		} catch (IOException e) {
	    		System.out.println("Une image est manquante");
	    }
		
		imageLabel.setIcon(imageIconNormal);
		
		infos.add(dureeLivraisonLabel, BorderLayout.PAGE_START );
		infos.add(heureLabel, BorderLayout.WEST );
	}
	
		public Livraison getLivraison() {
			return livraison;
		}
		
		public void setDuree() {
			livraison.setDuree((Integer)dureeModification.getValue()*60);
		}
		
		public void setIntersection(Intersection i) {
			boutonAction.setEnabled(true);;
			livraison = new Livraison(i, (Integer)dureeModification.getValue()*60);
		}

}
