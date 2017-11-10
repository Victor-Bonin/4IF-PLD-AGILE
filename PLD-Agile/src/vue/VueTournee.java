package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
 * <pre>
 * Cette classe correspond à la vue des tournées
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
public class VueTournee extends JPanel{
	private static final long serialVersionUID = 5007192571949757684L;

	private Controleur ctrl;
	
	private DemandeLivraison demLivraison;
	private Plan plan;
	
	private boolean dragEnCours = false;
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
	
	/**
	 * Constructeur d'un JPanel corrspondant a l'affichage de la tournee
	 * @param ctrl : le controleur associe a la vue
	 * @param p : l'objet Plan contenant la tournee a afficher
	 */
	public VueTournee(Controleur ctrl, Plan p){
		super();
		this.ctrl = ctrl;
		plan = p;
		
		elementsTournee = new ArrayList<ElementTournee>();
		
		setBackground(CharteGraphique.BG_COULEUR);
		setLayout(new GridBagLayout());
		
		// JPanel contenant le bouton d'ajout
		panelAjout = new JPanel();
		panelAjout.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelAjout.setBackground(CharteGraphique.BG_COULEUR);
		panelAjout.setLayout(new BorderLayout());
		
		// Bouton d'ajout de livraison
		ajouterLivraison = new JButton("+");
		ajouterLivraison.setBackground(CharteGraphique.BG_COULEUR);
		ajouterLivraison.setFont(CharteGraphique.TITRE_POLICE);
		ajouterLivraison.setForeground(CharteGraphique.NOTIFICATION_COULEUR);
		ajouterLivraison.setBorder(new CompoundBorder(
				new EmptyBorder(0, 10, 0, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATEUR_COULEUR),
						new EmptyBorder(5, 0, 5, 0)
						)
				));
		ecouteurBoutons = new EcouteurDeBouton(ctrl);
		ajouterLivraison.addActionListener(ecouteurBoutons);
		ajouterLivraison.setActionCommand("nouvelle-livraison");
		ajouterLivraison.setFocusPainted(false);
		panelAjout.add(ajouterLivraison, BorderLayout.PAGE_START);
		
		// Placement des JPanels dans le layout
		c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		c.gridx = 0;
	    c.gridy = 0;
		
		tourneeTitre = new JLabel(Textes.TITRE_TOURNEE);
	    tourneeTitre.setFont(CharteGraphique.TEXTE_PLUS_GRAND_POLICE);
		add(tourneeTitre, c);
		tourneeTitre.setBorder(new EmptyBorder(20, 20, 0, 145));
		

		c.weighty = 1;
		c.gridy = 1;
		
		pan = new JPanel();
		setBackground(CharteGraphique.BG_COULEUR);
		
		// Ajout d'une barre de défilement
		JScrollPane scrollPane = new JScrollPane(pan);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane,c);
		
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                
		pan.setBackground(CharteGraphique.BG_COULEUR);
		
		ecouteurDrag =  new EcouteurDeSourisDragnDrop(this);
		ecouteurDragEntrepot =  new EcouteurDeSourisDragnDropEntrepot(this);
	}
	
	/**
	 * Actualise le panneau des tournees selon la DemandeLivraison donnee en parametre (peut être une tournee)
	 */
	public void initTournee() {
		demLivraison = plan.getDemandeLivraison();
		
		elementsTournee.clear();
		elementEnCreation = null;
		
		pan.removeAll();
		
		// Layout vertical
		pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
		ElementTournee entrepot = new ElementTourneeEntrepot(ctrl, plan.getDemandeLivraison().getEntrepot());
		entrepot.setMaximumSize(entrepot.getPreferredSize());
		pan.add(entrepot);
		entrepot.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		int i = 0;
		elementsTournee.add(entrepot);

		for(Livraison livraison : plan.getDemandeLivraison().getLivraisons()) {
			
		    ElementTournee liv = new ElementTourneeLivraison(ctrl, livraison, i+1, i);
		    ajoutElementTournee(liv);
		    i++;
		}
		pan.revalidate();
	}
	
	/**
	 * Affiche un encart de creation d'une nouvelle livraison en fin de tournee
	 */
	public void creerLivraison() {
		this.annulerCreation();
		
		panelCreation = new JPanel();
		panelCreation.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelCreation.setBackground(CharteGraphique.BG_COULEUR);
		panelCreation.setLayout(new BorderLayout());
		elementEnCreation = new ElementTourneeLivraison(ctrl, demLivraison.getLivraisons().size());
		pan.remove(panelAjout);
		panelCreation.add(elementEnCreation, BorderLayout.PAGE_START);
		pan.add(panelCreation);
		pan.revalidate();
		pan.repaint();
	}
	
	
	/**
	 * Affiche un encart de creation d'une nouvelle livraison juste apres la position donnee dans la tournee
	 * @param place : la place ou inserer la livraison 
	 */
	public void creerLivraisonApres(int place) {
		this.annulerCreation();
		panelCreation = new JPanel();
		panelCreation.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelCreation.setBackground(CharteGraphique.BG_COULEUR);
		panelCreation.setLayout(new BorderLayout());
		
		elementEnCreation = new ElementTourneeLivraison(ctrl, place+1);

		pan.remove(panelAjout);
		panelCreation.add(elementEnCreation, BorderLayout.PAGE_START);
		pan.add(panelCreation, place+2);
		pan.revalidate();
		pan.repaint();
	}
	
	public ArrayList<ElementTournee> getElementsTournee(){
		return elementsTournee;
	}

	/**
	 * Indique a un element qu'il est survole par la souris pour le mettre en surbrillance
	 * @param index : place de l'element dans la liste d'elements de la demande de livraison
	 */
	public void survol(int index){
		elementsTournee.get(index+1).survolElement();
	}
	
	/**
	 * Indique a un element qu'il n'est plus survole par la souris pour enlever la surbrillance
	 * @param index : place de l'element dans la liste d'elements de la demande de livraison
	 */
	public void antiSurvol(int index){
		elementsTournee.get(index+1).antiSurvolElement();
	}
	
	/**
	 * Affiche les details d'un element de la demande de livraison
	 * @param index : place de l'element dans la liste d'elements de la demande de livraison
	 */
	public void afficherDetails(int index){
		if(elementDetaille != null) {
			elementDetaille.afficherDetails();
		}
		elementDetaille = elementsTournee.get(index+1);
		elementDetaille.afficherDetails();
		
	}
	
	/**
	 * Donne a la livraison en creation une intersection
	 * @param i : l'intersection a renseigner
	 */
	public void setIntersectionEnCreation(Intersection i){
		elementEnCreation.setIntersection(i);
	}
	
	/**
	 * Annule la creation d'une livraison
	 */
	public void annulerCreation() {
		if(panelCreation != null)
		{
			// Enlever l'encart de création
			pan.remove(panelCreation);
			// Ajouter en fin le bouton d'ajout
			pan.add(panelAjout);
			// Supprimer l'élément en cours
			elementEnCreation = null;
		}
		revalidate();
		repaint();
	}
	
	/**
	 * Ajoute le bouton d'ajout de livraison en fin de tournee
	 */
	public void ajouterBoutonPlus() {
		pan.add(panelAjout);	
	}
	
	/**
	 * Ajoute un element a la tournee affichee
	 * @param element : l'element de la tournee a ajouter
	 */
	private void ajoutElementTournee(ElementTournee element) {
		pan.add(element);
	    element.setMaximumSize(element.getPreferredSize());
	    element.setAlignmentX(Component.LEFT_ALIGNMENT);
	    elementsTournee.add(element);
	}
	
	/**
	 * Affiche les boutons de suppression des elements de la tournee
	 */
	public void afficherBoutonsSuppression() {
		for(ElementTournee element : elementsTournee) {
			if(element instanceof ElementTourneeLivraison)
			{
				((ElementTourneeLivraison) element).afficherBoutonSupprimer();
			}
		}
	}
	
	/**
	 * Ajoute un ecouteur pour le drag and drop a la liste des elements de la tournee
	 */
	public void ajouterDragAndDropListener() {
		for(ElementTournee element : elementsTournee) {
			if(element instanceof ElementTourneeLivraison)
			{
				((ElementTourneeLivraison) element).addMouseMotionListener(ecouteurDrag);
				((ElementTourneeLivraison) element).addMouseListener(ecouteurDrag);
			} else if (element instanceof ElementTourneeEntrepot){
				((ElementTourneeEntrepot) element).addMouseMotionListener(ecouteurDragEntrepot);
				((ElementTourneeEntrepot) element).addMouseListener(ecouteurDragEntrepot);
			}
		}
	}
	
	/**
	 * Enregistre l'élement en cours de drag and drop
	 * @param elemt : l'element a drag and drop
	 */
	public void dragCommencer(ElementTourneeLivraison elemt) {
		dragEnCours = true;
		dragSource = elemt;
	}
	
	/**
	 * Indique visuellement a l'utilisateur ou il s'apprete a relacher l'element en cours de deplacement
	 * @param elemt : l'element en train d'etre survole, apres qui placer la livraison en deplacement
	 */
	public void dragSurElement(ElementTournee elemt) {
		if(dragEnCours == true){
			elemt.setBorder(new CompoundBorder(
					new EmptyBorder(10, 10, 0, 10),
					new CompoundBorder(
							new MatteBorder(0,0,5,0, CharteGraphique.SEPARATEUR_COULEUR),
							new EmptyBorder(10, 10, 10, 10)
							)
					));
			dragCible = elemt;
		}
	}
	/**
	 * Enleve l'indication du deplacement lorsque l'utilisateur ne survole plus un emplacement 
	 * @param elemt : l'element qui n'est plus survole
	 */
	public void dragEnDehorsElement(ElementTournee elemt) {
		if(dragEnCours == true){
			elemt.setBorder(new CompoundBorder(
					new EmptyBorder(10, 10, 5, 10),
					new CompoundBorder(
							new MatteBorder(0,0,1,0, CharteGraphique.SEPARATEUR_COULEUR),
							new EmptyBorder(10, 10, 10, 10)
							)
					));
			dragCible = null;
		}
	}
	
	/**
	 * Deplace l'element qui a subi un drag and drop a sa nouvelle place
	 */
	public void stopDrag() {
		dragEnCours = false;
		dragSource.setBorder(new CompoundBorder(
				new EmptyBorder(10, 10, 5, 10),
				new CompoundBorder(
						new MatteBorder(0,0,1,0, CharteGraphique.SEPARATEUR_COULEUR),
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
	}

}
