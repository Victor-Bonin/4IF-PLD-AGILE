package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controleur.Controleur;
import modele.DemandeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;

/**
 * Cette classe correspond à la vue des tournées
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
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
 * 
 * 
 * @author 4104
 */
public class VueTournee extends JPanel{
	private static final long serialVersionUID = 5007192571949757684L;

	private Controleur ctrl;
	
	private DemandeLivraison demLivraison;
	private Plan plan;
	
	private boolean isDragged = false;
	private ElementTourneeLivraison dragSource;
	private ElementTournee dragCible;
	
	private GridBagConstraints c;
	private JLabel tourneeTitre;
	private JPanel pan;
	private JButton ajouterLivraison;
	private JPanel panelAjout;
	private JPanel panelCreation;
	
	private ArrayList<ElementTournee> elementsTournee;
	ElementTournee elementDetaille;
	ElementTourneeLivraison elementEnCreation;
	
	EcouteurDeBouton ecouteurBoutons;
	EcouteurDeSourisDragnDrop ecouteurDrag;
	EcouteurDeSourisDragnDropEntrepot ecouteurDragEntrepot;
	
	public VueTournee(Controleur ctrl, Plan p){
		super();
		this.ctrl = ctrl;
		plan = p;
		
		elementsTournee = new ArrayList<ElementTournee>();
		
		setBackground(CharteGraphique.BG_COLOR);
		setLayout(new GridBagLayout());
		
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
		
		ecouteurDrag =  new EcouteurDeSourisDragnDrop(this);
		ecouteurDragEntrepot =  new EcouteurDeSourisDragnDropEntrepot(this);
	}
	
	/**
	 * Actualise le panneau des tournées selon la DemandeLivraison donnee en parametre (peut être une tournee)
	 * @param dem DemandeLivraison qui doit être représentée
	 */
	
	// TODO : Enelever le paramètre? on ne l'utilise pas!
	public void initTournee(DemandeLivraison dem) {
		demLivraison = dem;
		
		elementsTournee.clear();
		elementEnCreation = null;
		
		pan.removeAll();
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
		ElementTournee entrepot = new ElementTourneeEntrepot(ctrl, plan.getDemandeLivraison().getEntrepot());
		entrepot.setMaximumSize(entrepot.getPreferredSize());
		pan.add(entrepot);
		entrepot.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		int i = 0;
		elementsTournee.add(entrepot);
		
		entrepot.addMouseMotionListener(ecouteurDragEntrepot);
		entrepot.addMouseListener(ecouteurDragEntrepot);
		
		for(Livraison livraison : plan.getDemandeLivraison().getLivraisons()) {
			
		    ElementTournee liv = new ElementTourneeLivraison(ctrl, livraison, i+1, i);
		    ajoutElementTournee(liv);
		    i++;
		}
		
		panelAjout = new JPanel();
		panelAjout.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelAjout.setBackground(CharteGraphique.BG_COLOR);
		panelAjout.setLayout(new BorderLayout());
		pan.add(panelAjout);
		
		pan.revalidate();
	}
	
	public void creerLivraison() {
		this.annulerCreation();
		
		panelCreation = new JPanel();
		panelCreation.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelCreation.setBackground(CharteGraphique.BG_COLOR);
		panelCreation.setLayout(new BorderLayout());
		elementEnCreation = new ElementTourneeLivraison(ctrl, demLivraison.getLivraisons().size()+1,demLivraison.getLivraisons().size());
		pan.remove(panelAjout);
		panelCreation.add(elementEnCreation, BorderLayout.PAGE_START);
		pan.add(panelCreation);
		//elementEnCreation.setMaximumSize(elementEnCreation.getPreferredSize());
		//elementEnCreation.setAlignmentX(Component.LEFT_ALIGNMENT);
		//pan.add(new JLabel());
		pan.revalidate();
		pan.repaint();
	}
	
	
	public void creerLivraisonApres(int place) {
		this.annulerCreation();
		panelCreation = new JPanel();
		panelCreation.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelCreation.setBackground(CharteGraphique.BG_COLOR);
		panelCreation.setLayout(new BorderLayout());
		
		elementEnCreation = new ElementTourneeLivraison(ctrl, demLivraison.getLivraisons().size()+1,place+1);

		pan.remove(panelAjout);
		panelCreation.add(elementEnCreation, BorderLayout.PAGE_START);
		pan.add(panelCreation, place+2);
		pan.revalidate();
		pan.repaint();
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
	
	public void afficherDetails(int index){
		if(elementDetaille != null) {
			elementDetaille.afficherDetails();
		}
		elementDetaille = elementsTournee.get(index+1);
		elementDetaille.afficherDetails();
		
	}
	
	// TODO : combiner avec la modif?
	public void setIntersectionEnCreation(Intersection i){
		elementEnCreation.setIntersection(i);
	}
	
	public void annulerCreation() {
		if(panelCreation != null)
		{
			pan.remove(panelCreation);
			pan.add(panelAjout);
			elementEnCreation = null;
		}
		revalidate();
		repaint();
	}
	
	public void ajouterBoutonPlus() {
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
		
		panelAjout.add(ajouterLivraison, BorderLayout.PAGE_START);	
	}
	
	public void ajoutElementTournee(ElementTournee element) {
		pan.add(element);
	    element.setMaximumSize(element.getPreferredSize());
	    element.setAlignmentX(Component.LEFT_ALIGNMENT);
	    elementsTournee.add(element);
	    element.addMouseMotionListener(ecouteurDrag);
	    element.addMouseListener(ecouteurDrag);
	}
	
	public void masquerBoutonsSuppression() {
		for(ElementTournee element : elementsTournee) {
			if(element instanceof ElementTourneeLivraison)
			{
				((ElementTourneeLivraison) element).masquerBoutonSupprimer();
			}
		}
	}
	
	public void afficherBoutonsSuppression() {
		for(ElementTournee element : elementsTournee) {
			if(element instanceof ElementTourneeLivraison)
			{
				((ElementTourneeLivraison) element).afficherBoutonSupprimer();
			}
		}
	}
	
	public void dragCommencer(ElementTourneeLivraison elemt) {
		isDragged = true;
		dragSource = elemt;
		//elemt.setBackground(CharteGraphique.LIVRAISON_SELECTIONNEE);
	}
	
	public void dragIn(ElementTournee elemt) {
		if(isDragged == true){
			elemt.setBorder(new CompoundBorder(
					new EmptyBorder(10, 10, 0, 10),
					new CompoundBorder(
							new MatteBorder(0,0,5,0, CharteGraphique.SEPARATOR_COLOR),
							new EmptyBorder(10, 10, 10, 10)
							)
					));
			dragCible = elemt;
		}
	}
	
	public void dragOut(ElementTournee elemt) {
		if(isDragged == true){
			elemt.setBorder(new CompoundBorder(
					new EmptyBorder(10, 10, 5, 10),
					new CompoundBorder(
							new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
							new EmptyBorder(10, 10, 10, 10)
							)
					));
			dragCible = null;
		}
	}
	
	public void stopDrag(ElementTourneeLivraison elemt) {
		isDragged = false;
		dragSource.setBorder(new CompoundBorder(
				new EmptyBorder(10, 10, 5, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATOR_COLOR),
						new EmptyBorder(10, 10, 10, 10)
						)
				));
		if(dragSource != dragCible && dragCible != null) {
			if(dragCible instanceof  ElementTourneeLivraison){
				ctrl.permuterLivraison(dragSource.getLivraison(), dragSource.getPosition(), ((ElementTourneeLivraison)dragCible).getPosition());
			} else {
				ctrl.permuterLivraison(dragSource.getLivraison(), dragSource.getPosition(), -1);
			}
			revalidate();
			repaint();
		}
		dragCible = null;
		dragSource = null;
		//dragSource.setBackground(CharteGraphique.BG_COLOR);
	}

	public void supprimerElementDetaille() {
		this.remove(elementDetaille);	
	}
	
	public void autoriserClicDroit() {
		for(ElementTournee element : elementsTournee) {
			if(element instanceof ElementTourneeLivraison)
			{
				((ElementTourneeLivraison) element).autoriserClicDroit();
			}
		}
	}

}
