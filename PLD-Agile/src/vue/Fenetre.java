package vue;

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modele.Plan;
import controleur.Controleur;
import vue.VueGraphique;

public class Fenetre extends JFrame {
	
	// Intitules des boutons de la fenetre
	protected final static String AJOUTER_CERCLE = "Ajouter un cercle";
	protected static final String AJOUTER_RECTANGLE = "Ajouter un rectangle";
	protected static final String SUPPRIMER = "Supprimer des formes";
	protected static final String DEPLACER = "Deplacer une forme";
	protected static final String AUGMENTER_ECHELLE = "Augmenter l'echelle";
	protected static final String DIMINUER_ECHELLE = "Diminuer l'echelle";
	protected static final String OUVRIR = "Charger un plan";
	protected static final String SAUVER = "Sauver le plan";
	protected static final String REDO = "Redo";
	protected static final String UNDO = "Undo";
	private ArrayList<JButton> boutons;
	private JLabel cadreMessages;
	private VueGraphique vueGraphique;
	private VueTextuelle vueTextuelle;
	private EcouteurDeBoutons ecouteurDeBoutons;
	private EcouteurDeSouris ecouteurDeSouris;
	private EcouteurDeClavier ecouteurDeClavier;
	
	private final String[] intitulesBoutons = new String[]{AJOUTER_CERCLE, AJOUTER_RECTANGLE, 
			SUPPRIMER, DEPLACER, DIMINUER_ECHELLE, AUGMENTER_ECHELLE,
			SAUVER, OUVRIR, UNDO, REDO};
	private final int hauteurBouton = 40;
	private final int largeurBouton = 150;
	private final int hauteurCadreMessages = 80;
	private final int largeurVueTextuelle = 400;

	
	/**
	 * Cree une fenetre avec des boutons, une zone graphique pour dessiner le plan p avec l'echelle e, 
	 * un cadre pour afficher des messages, une zone textuelle decrivant les formes de p,
	 * et des ecouteurs de boutons, de clavier et de souris qui envoient des messages au controleur c
	 * @param plan le plan
	 * @param echelle l'echelle
	 * @param controleur le controleur
	 */
	public Fenetre(Plan plan, int echelle, Controleur controleur){
		setLayout(null);
		creeBoutons(controleur);
		cadreMessages = new JLabel();
		cadreMessages.setBorder(BorderFactory.createTitledBorder("Messages..."));
		getContentPane().add(cadreMessages);
		vueGraphique = new VueGraphique(plan, echelle, this);
		vueTextuelle = new VueTextuelle(plan, this);
		ecouteurDeSouris = new EcouteurDeSouris(controleur,vueGraphique,this);
		addMouseListener(ecouteurDeSouris);
		addMouseMotionListener(ecouteurDeSouris);
		ecouteurDeClavier = new EcouteurDeClavier(controleur);
		addKeyListener(ecouteurDeClavier);
		setTailleFenetre();
		setVisible(true);
	}

	/**
	 * Cree les boutons correspondant aux intitules contenus dans intitulesBoutons
	 * cree un ecouteur de boutons qui ecoute ces boutons
	 * @param controleur
	 */
	private void creeBoutons(Controleur controleur){
		ecouteurDeBoutons = new EcouteurDeBoutons(controleur);
		boutons = new ArrayList<JButton>();
		for (String intituleBouton : intitulesBoutons){
			JButton bouton = new JButton(intituleBouton);
			boutons.add(bouton);
			bouton.setSize(largeurBouton,hauteurBouton);
			bouton.setLocation(0,(boutons.size()-1)*hauteurBouton);
			bouton.setFocusable(false);
			bouton.setFocusPainted(false);
			bouton.addActionListener(ecouteurDeBoutons);
			getContentPane().add(bouton);	
		}
	}
	
	/**
	 * Definit la taille du cadre et de ses composants en fonction de la taille de la vue
	 * @param largeurVue
	 * @param hauteurVue
	 */
	private void setTailleFenetre() {
		int hauteurBoutons = hauteurBouton*intitulesBoutons.length;
		int hauteurFenetre = Math.max(vueGraphique.getHauteur(),hauteurBoutons)+hauteurCadreMessages;
		int largeurFenetre = vueGraphique.getLargeur()+largeurBouton+largeurVueTextuelle+10;
		setSize(largeurFenetre, hauteurFenetre);
		cadreMessages.setSize(largeurFenetre,60);
		cadreMessages.setLocation(0,hauteurFenetre-hauteurCadreMessages);
		vueGraphique.setLocation(largeurBouton, 0);
		vueTextuelle.setSize(largeurVueTextuelle,hauteurFenetre-hauteurCadreMessages);
		vueTextuelle.setLocation(10+vueGraphique.getLargeur()+largeurBouton,0);
	}

	/**
	 * Affiche message dans la fenetre de dialogue avec l'utilisateur
	 * @param message
	 */
	public void afficheMessage(String message) {
		cadreMessages.setText(message);
	}
	
	/**
	 * Active les boutons si b = true, les desactive sinon
	 * @param b
	 */
	public void autoriseBoutons(Boolean b) {
		for (JButton bouton : boutons)
			bouton.setEnabled(b);
	}
	
	public int getEchelle(){
		return vueGraphique.getEchelle();
	}
	
	public void setEchelle(int echelle){
		vueGraphique.setEchelle(echelle);
		setTailleFenetre();
	}
}
