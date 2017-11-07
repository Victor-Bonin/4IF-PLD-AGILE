package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controleur.Controleur;
import modele.Intersection;
import modele.Plan;

import vue.etat.*;

/**
 * Extension de JFrame permettant d'afficher et d'interagir avec les éléments de PlanCo
 * 
 * @author 4104
 *
 */
public class Fenetre extends JFrame{
	private static final long serialVersionUID = 4042713508717400450L;
	public static final int VUE_DEFAUT = 0;
	public static final int VUE_PLAN = 1;
	public static final int VUE_LIVRAISON_CHARGEE = 2;
	public static final int VUE_TOURNEE_CALCULEE = 3;
	public static final int VUE_TOURNEE_AJOUT = 4;
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
	
	
	public Fenetre(Controleur ctrl, Plan plan){
		super(Textes.NOM_APPLI);
		this.ctrl = ctrl;
		this.plan = plan;
		
		initListeners();
		
		initButtons();
		
		initFenetre();
		
		initHeader();
		initContent();
		initFooter();
		
		setVisible(true);
		
	}
	
	
	private void initListeners(){
		ecouteurBoutons = new EcouteurDeBouton(ctrl);
		ecouteurClavier = new EcouteurDeClavier(ctrl);
		addKeyListener(ecouteurClavier);
	}
	
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
	
	private void initFenetre(){
		setSize(1000,800);
		//setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setFocusable(true);
		requestFocus();
	}
	
	private void initContent(){
		jpanelCentral = new JPanel();
		jpanelCentral.setLayout(new GridBagLayout());
		jpanelCentral.setBackground(CharteGraphique.BG_COLOR);
		
		jpanelCentral.add(importPlanButton);
		
		getContentPane().add(jpanelCentral, BorderLayout.CENTER);
	}

	private void initHeader(){
		header = new VueHeader();
		header.changeNotification(Textes.NOTIF_MUST_IMPORT, CharteGraphique.NOTIFICATION_COLOR);
		
		getContentPane().add(header, BorderLayout.NORTH);
	}
	
	private void initFooter(){
		footer = new JPanel();
		footer.setBackground(CharteGraphique.BG_COLOR);
	}
	
	
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
	 * @param vue int correspondant a la vue a charger
	 * @see #VUE_DEFAUT
	 * @see #VUE_LIVRAISON_CHARGEE
	 * @see #VUE_PLAN
	 * @see #VUE_TOURNEE_CALCULEE
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
	
	//TODO : a améliorer
	public void ajouterIcone(Intersection intersection) {
		vuePlan.afficherIcone(intersection);
		vueTournee.setIntersectionEnCreation(intersection);
	}
	
	public void commencerChoixIntersection() {
		vuePlan.commencerChoixIntersection();
	}
	
	//TODO : supprimer? (doit se faire avec le pattern)
	public void initialiserTournee() {
		vueTournee.initTournee(plan.getTournee());
		vuePlan.afficherIcones(plan.getDemandeLivraison());
		ajouterEcouteursSynchro();
	}
	
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
}
