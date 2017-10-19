package vue;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Plan;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 4042713508717400450L;
	public static final int VUE_DEFAUT = 0;
	public static final int VUE_PLAN = 1;
	public static final int VUE_LIVRAISON_CHARGEE = 2;
	public static final int VUE_TOURNEE_CALCULEE = 3;

	private Controleur ctrl;
	
	private VueHeader header;
	
	private VueCentrale contentContainer;
	private JPanel jpanelCentral; //the first central JPanel
	private VuePlan vuePlan;
	private VueTournee vueTournee;

	private EcouteurDeBouton ecouteurBoutons;
	
	private JPanel footer;
	private PersoButton importPlanButton;
	private PersoButton importDemandeLivraisonButton;
	private PersoButton calculTourneeButton;
	private PersoButton exportButton;
	private Plan plan;
	
	
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
		
		header.changeNotification(Textes.NOTIF_MUST_IMPORT);
		
		getContentPane().add(header, BorderLayout.NORTH);
	}
	
	private void initFooter(){
		footer = new JPanel();
		footer.setBackground(CharteGraphique.BG_COLOR);
	}
	
	
	private void setContent(){
		
		
		vuePlan = new VuePlan(ctrl, plan);
		vueTournee = new VueTournee(ctrl, plan.getDemandeLivraison());
		//vueTournee.addMouseWheelListener(ecouteurSouris);
		
		if(contentContainer != null){
			getContentPane().remove(contentContainer);
		}
		getContentPane().remove(jpanelCentral);
		
		contentContainer = new VueCentrale(vuePlan, vueTournee);
		
		getContentPane().add(contentContainer, BorderLayout.CENTER);
	}
	
	private void setFooter(int vueInt){
		switch(vueInt){
		case VUE_DEFAUT:
			footer.remove(importDemandeLivraisonButton);
			footer.remove(exportButton);
			footer.remove(calculTourneeButton);
			break;
		case VUE_PLAN:
			footer.remove(exportButton);
			footer.remove(calculTourneeButton);
			footer.add(importDemandeLivraisonButton);
			break;
		case VUE_LIVRAISON_CHARGEE:
			footer.remove(importDemandeLivraisonButton);
			footer.remove(exportButton);
			footer.add(calculTourneeButton);
			break;
		case VUE_TOURNEE_CALCULEE:
			footer.remove(importDemandeLivraisonButton);
			footer.remove(calculTourneeButton);
			footer.add(exportButton);
			break;
		}
		
		getContentPane().add(footer, BorderLayout.SOUTH);
	}

	public void goToVue(int vue){

		if(plan!=null){
			
			switch(vue){
			case VUE_DEFAUT:
				break;
			case VUE_PLAN:
				setContent();
				break;
			case VUE_LIVRAISON_CHARGEE:
				break;
			case VUE_TOURNEE_CALCULEE:
				break;
			}

			setFooter(vue);
			setVisible(true);
			repaint();
		}
	}
	
	public void changeNotification(String texte) {
		header.changeNotification(texte);
	}

}
