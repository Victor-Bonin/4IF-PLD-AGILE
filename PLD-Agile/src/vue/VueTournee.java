package vue;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controleur.Controleur;
import modele.DemandeLivraison;
import modele.Livraison;
import modele.Plan;

/**
 * Cette classe correspond à la vue des tournées
 * 
 * @author 4104
 *
 */
public class VueTournee extends JPanel{
	private static final long serialVersionUID = 5007192571949757684L;

	private Controleur ctrl;
	
	private DemandeLivraison demLivraison;
	private Plan plan;
	
	private GridBagConstraints c;
	private JLabel tourneeTitre;
	private JPanel pan;
	private JButton ajouterLivraison;
	
	private ArrayList<ElementTournee> elementsTournee;
	
	EcouteurDeBouton ecouteurBoutons;
	
	public VueTournee(Controleur ctrl, Plan p){
		super();
		this.ctrl = ctrl;
		plan = p;
		
		elementsTournee = new ArrayList<ElementTournee>();
		
		setBackground(CharteGraphique.BG_COLOR);
		setLayout(new GridBagLayout());
		

		// Bouton d'ajout de livraison
		ajouterLivraison = new JButton("+");
		ajouterLivraison.setBackground(CharteGraphique.BG_COLOR);
		ajouterLivraison.setFont(CharteGraphique.TITLE_FONT);
		ajouterLivraison.setForeground(CharteGraphique.NOTIFICATION_COLOR);
		ajouterLivraison.setBorder(new CompoundBorder(
				new EmptyBorder(0, 10, 0, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
						new EmptyBorder(5, 0, 5, 0)
						)
				));
		ecouteurBoutons = new EcouteurDeBouton(ctrl);
		ajouterLivraison.addActionListener(ecouteurBoutons);
		ajouterLivraison.setActionCommand("nouvelle-livraison");
		ajouterLivraison.setFocusPainted(false);
		
		c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		c.gridx = 0;
	    c.gridy = 0;
		
		tourneeTitre = new JLabel(Textes.TITRE_TOURNEE);
	    tourneeTitre.setFont(CharteGraphique.TEXT_BIGGER_FONT);
		add(tourneeTitre, c);
		tourneeTitre.setBorder(new EmptyBorder(20, 20, 0, 145));
		

		c.weighty = 1;
		c.gridy = 1;
		pan = new JPanel();
		setBackground(CharteGraphique.BG_COLOR);
		JScrollPane scrollPane = new JScrollPane(pan);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane,c);
		
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                
		pan.setBackground(CharteGraphique.BG_COLOR);
		
		
	}
	
	/**
	 * Actualise le panneau des tournées selon la DemandeLivraison donnee en parametre (peut être une tournee)
	 * @param dem DemandeLivraison qui doit être représentée
	 */
	public void initTournee(DemandeLivraison dem) {
		demLivraison = dem;
		
		elementsTournee.clear();
		
		pan.removeAll();
		pan.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
	    c.gridy = 0;

	    ElementTournee entrepot = new ElementTournee(plan.getDemandeLivraison().getEntrepot());
		pan.add(entrepot, c);
		int i = 0;
		elementsTournee.add(entrepot);
		
		for(Livraison livraison : plan.getDemandeLivraison().getLivraisons()) {
			
		    c.gridy = i+1;
		    ElementTournee liv = new ElementTournee(livraison, i+1, i);
		    pan.add(liv, c);
		    elementsTournee.add(liv);
		    i++;
		}
		
		c.gridy = i+1;
		c.weighty = 1;
		
		
		pan.add(ajouterLivraison, c);
	}
	
	public void creerLivraison() {
		c.gridy = demLivraison.getLivraisons().size()+1;
		pan.remove(ajouterLivraison);
		pan.add(new ElementTournee(demLivraison.getLivraisons().size()+1,demLivraison.getLivraisons().size()), c);
		
		c.gridy = demLivraison.getLivraisons().size()+2;
		c.weighty = 1;
		pan.add(new JLabel(), c);
	}
	
	public ArrayList<ElementTournee> getElementsTournee(){
		return elementsTournee;
	}

	public void survol(int index){
		elementsTournee.get(index+1).survolElement();
	}
	
	public void antiSurvol(int index){
		elementsTournee.get(index+1).antiSurvolElement();
	}
}
