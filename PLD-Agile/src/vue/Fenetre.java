package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.evenement.EvenementInsertion;
import modele.evenement.EvenementSuppression;
import vue.etat.*;

/**
 * <pre>
 * Extension de JFrame permettant d'afficher et d'interagir avec les éléments de PlanCo
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
public class Fenetre extends JFrame implements Observer {
	private static final long serialVersionUID = 4042713508717400450L;
	/** Constante d'identification de la vue par defaut */
	public static final int VUE_DEFAUT = 0;
	/** Constante d'identification de la vue plan charge */
	public static final int VUE_PLAN = 1;
	/** Constante d'identification apres livraison chargee */
	public static final int VUE_LIVRAISON_CHARGEE = 2;
	/** Constante d'identification de la vue tournee calculee */
	public static final int VUE_TOURNEE_CALCULEE = 3;
	/** Constante d'identification de la vue d'ajout d'une livraison a la tournee */
	public static final int VUE_TOURNEE_AJOUT = 4;
	/** Constante d'identification de la vue de calcul en cours */
	public static final int VUE_TOURNEE_CALCUL_EN_COURS = 5;

	private Controleur ctrl;
	
	private VueHeader header;
	
	private VueCentrale contentContainer;
	private JPanel jpanelCentral; //the first central JPanel
	private VuePlan vuePlan;
	private VueTournee vueTournee;

	private EcouteurDeBouton ecouteurBoutons;
	private EcouteurDeSourisDeSynchronisation ecouteurSynchro;
	private EcouteurDeClavier ecouteurClavier;
	
	private JPanel footer;
	private PersoButton importPlanButton;
	private PersoButton importDemandeLivraisonButton;
	private PersoButton calculTourneeButton;
	private PersoButton exportButton;
	private Plan plan;
	
	private Etat etatCourant;
	public final EtatInit etatInit = new EtatInit();
	public final EtatDemandeOuverte etatDemandeOuverte = new EtatDemandeOuverte();
	public final EtatPlanOuvert etatPlanOuvert = new EtatPlanOuvert();
	public final EtatCalculEnCours etatCalculEnCours = new EtatCalculEnCours();
	public final EtatCalcule etatCalcule = new EtatCalcule();
	public final EtatAjoutLivraison etatAjoutLivraison = new EtatAjoutLivraison();
	public final EtatModifie etatModifie = new EtatModifie();
	
	/**
	 * Constructeur
	 * @param ctrl controleur lie a la fenetre
	 * @param plan plan (vide ou pas) qui sera affiche et modifie par la fenetre
	 */
	public Fenetre(Controleur ctrl, Plan plan){
		super(Textes.NOM_APPLI);
		this.ctrl = ctrl;
		this.plan = plan;
		plan.addObserver(this);
		
		initListeners();
		
		initButtons();
		
		initFenetre();
		
		initHeader();
		initContent();
		initFooter();
		
		setVisible(true);
		
	}
	
	/** initialisation des ecouteurs de la fenetre */
	private void initListeners(){
		ecouteurBoutons = new EcouteurDeBouton(ctrl);
		ecouteurClavier = new EcouteurDeClavier(ctrl);
		addKeyListener(ecouteurClavier);
	}
	
	/** initialisation des boutons */
	private void initButtons(){
		exportButton = new PersoButton(Textes.BUTTON_EXPORT_ROUTE,1);
		exportButton.addActionListener(ecouteurBoutons);
		exportButton.setActionCommand("export-feuille");
		
		importPlanButton = new PersoButton(Textes.BUTTON_IMPORT_PLAN,1);
		importPlanButton.addActionListener(ecouteurBoutons);
		importPlanButton.setActionCommand("import-plan");
		
		importDemandeLivraisonButton = new PersoButton(Textes.BUTTON_IMPORT_DEMANDE_LIVRAISON,1);
		importDemandeLivraisonButton.addActionListener(ecouteurBoutons);
		importDemandeLivraisonButton.setActionCommand("import-demande-livraison");
		
		calculTourneeButton = new PersoButton(Textes.BUTTON_CALCUL_TOURNEE, 1);
		calculTourneeButton.addActionListener(ecouteurBoutons);
		calculTourneeButton.setActionCommand("calcul-tournee");
	}
	
	/** initialiser la parametrisation de la fenetre */
	private void initFenetre(){
		setSize(1000,800);
		//setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setFocusable(true);
		requestFocus();
	}
	
	/** initialisation des panneaux contenus dans la fenetre */
	private void initContent(){
		jpanelCentral = new JPanel();
		jpanelCentral.setLayout(new GridBagLayout());
		jpanelCentral.setBackground(CharteGraphique.BG_COULEUR);
		
		jpanelCentral.add(importPlanButton);
		
		getContentPane().add(jpanelCentral, BorderLayout.CENTER);
	}

	/** initialisation du panneau superieur de la fenetre, dont la notification */
	private void initHeader(){
		header = new VueHeader();
		header.changeNotification(Textes.NOTIF_MUST_IMPORT, CharteGraphique.NOTIFICATION_COULEUR);
		
		getContentPane().add(header, BorderLayout.NORTH);
	}
	
	/** initialisation du panneau inferieur */
	private void initFooter(){
		footer = new JPanel();
		footer.setBackground(CharteGraphique.BG_COULEUR);
	}
	
	/** Met a jour le contenu de la fenêtre en fonction de son etat */
	public void setContent(){
		vuePlan = new VuePlan(ctrl, plan);
		vueTournee = new VueTournee(ctrl, plan);
		//vueTournee.addMouseWheelListener(ecouteurSouris);
		
		this.addComponentListener(new ResizeListener(vuePlan));
		
		if(contentContainer != null){
			getContentPane().remove(contentContainer);
		}
		getContentPane().remove(jpanelCentral);
		
		contentContainer = new VueCentrale(vuePlan, vueTournee);
		
		getContentPane().add(contentContainer, BorderLayout.CENTER);
	}
	
	private void setFooter(){
		etatCourant.setFooter(footer, this);		
		getContentPane().add(footer, BorderLayout.SOUTH);
	}

	/**
	 * Permet de faire basculer la fenêtre vers une vue ou une autre
	 */
	public void goToVue(){
		if(plan!=null){
			etatCourant.afficherVue(this);
		}
		setFooter();
		setVisible(true);
		repaint();
	}

	/**
	 * Afficher une notification dans la fenêtre
	 * @param texte texte a afficher
	 * @param color couleur de ce texte
	 */
	public void changeNotification(String texte, Color color) {
		header.changeNotification(texte, color);
	}
	
	/**
	 * Initialise les ecouteurs de synchronisation
	 */
	public void ajouterEcouteursSynchro (){
		for (int i = 0; i<vuePlan.getIconesLivraison().size(); i++) {
			ecouteurSynchro = new EcouteurDeSourisDeSynchronisation(i, vuePlan, vueTournee);
			vuePlan.getIconesLivraison().get(i).addMouseListener(ecouteurSynchro);
		}
		ecouteurSynchro = new EcouteurDeSourisDeSynchronisation(-1, vuePlan, vueTournee);
		vuePlan.getIconeEntrepot().addMouseListener(ecouteurSynchro);
		for (int i = 0; i<vueTournee.getElementsTournee().size(); i++) {
			ecouteurSynchro = new EcouteurDeSourisDeSynchronisation(i-1, vuePlan, vueTournee);
			vueTournee.getElementsTournee().get(i).addMouseListener(ecouteurSynchro);
		}
	}

	/** ajoute une icone par rapport pour placer une nouvelle livraison */
	//TODO a ameliorer avec un grisage
	public void ajouterIcone(Intersection intersection) {
		vuePlan.afficherIcone(intersection);
		vueTournee.setIntersectionEnCreation(intersection);
	}
	
	/** Entre en mode selection d'une adresse */
	public void commencerChoixIntersection() {
		vuePlan.commencerChoixIntersection();
	}
	
	/** Sort du mode selection d'adresse */
	public void annulerCreation() {
		vueTournee.annulerCreation();
		vuePlan.annulerCreation();
		repaint();
	}

	public PersoButton getImportPlanButton() {
		return importPlanButton;
	}
	
	public PersoButton getImportDemandeLivraisonButton() {
		return importDemandeLivraisonButton;
	}
	
	public PersoButton getExportButton() {
		return exportButton;
	}
	
	public PersoButton getCalculTourneeButton() {
		return calculTourneeButton;
	}

	public VueTournee getVueTournee() {
		return vueTournee;
	}
	
	public VuePlan getVuePlan() {
		return vuePlan;
	}
	
	public Plan getPlan() {
		return plan;
	}
	
	public void setEtatCourant(Etat etat){
		etatCourant = etat;
	}


	/** {@inheritDoc}  */
	@Override
	public void update(Observable arg0, Object arg1) {
		Plan p = (Plan) arg0;
		// code demandé par Clara
		if(vuePlan.getIconeLivraisonSouris().getParent() == vuePlan)
			vuePlan.remove(vuePlan.getIconeLivraisonSouris());

		if(arg1 instanceof EvenementInsertion)
		{
			Livraison livraison = ((EvenementInsertion) arg1).getLivraison();
			updateAjoutLivraison(p, livraison);
		}
		if(arg1 instanceof EvenementSuppression)
		{
			EvenementSuppression evtSuppr = ((EvenementSuppression) arg1); 
			Livraison livraison = evtSuppr.getLivraison();
			int index = evtSuppr.getIndex();
			updateSuppressionLivraison(p, livraison, index);
		}
	}
	
	/**
	 * Met a jour l'interface apres un ajout de livraison
	 * @param p le plan contenant la livraison
	 * @param livraison la livraison en question
	 */
	public void updateAjoutLivraison(Plan p, Livraison livraison) {
		vueTournee.initTournee();
		vueTournee.ajouterBoutonPlus();
		vueTournee.afficherBoutonsSuppression();
		vueTournee.ajouterDragAndDropListener();

		vuePlan.annulerCreation();
		int index = p.getDemandeLivraison().getLivraisons().indexOf(livraison);
		
		JLabel iconeLivraison = vuePlan.afficherIconeLivraison(livraison);
	    iconeLivraison.addMouseListener(new EcouteurDeSourisDeSynchronisation(index, vuePlan, vueTournee));
	    vuePlan.afficherIcones(plan.getDemandeLivraison());
		
	    revalidate();
		setVisible(true);
		repaint();
		
		vuePlan.revalidate();
		vuePlan.setVisible(true);
		vuePlan.repaint();
	}
	
	/**
	 * Met a jour l'interface apres la suppression de livraison
	 * @param p le plan contenant la livraison
	 * @param livraison la livraison en question
	 * @param index index de cette livraison
	 */
	public void updateSuppressionLivraison (Plan p, Livraison livraison, int index) {
		JLabel iconeLivraison = vuePlan.getIconesLivraison().get(index);
		iconeLivraison.removeMouseListener(iconeLivraison.getMouseListeners()[0]);
		vuePlan.remove(iconeLivraison);
		vuePlan.getIconesLivraison().remove(iconeLivraison);
		
		vueTournee.initTournee();
		vueTournee.ajouterBoutonPlus();
		vueTournee.afficherBoutonsSuppression();
		vueTournee.ajouterDragAndDropListener();
		vuePlan.afficherIcones(plan.getDemandeLivraison());
		
		revalidate();
		setVisible(true);
		repaint();

		vuePlan.revalidate();
		vuePlan.setVisible(true);
		vuePlan.repaint();
	}
	
	/** Nettoyage de l'interface apres l'annulation de la creation d'une livraison */
	public void nettoyerNouvelleLivraison() {
		vuePlan.annulerCreation();
	}
}
