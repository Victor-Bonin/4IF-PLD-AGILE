package vue;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Plan;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 4042713508717400450L;
	private static final int VUE_DEFAUT = 0;
	private static final int VUE_PLAN = 1;
	private static final int VUE_CHARGEE = 2;

	private Controleur ctrl;
	
	private VueHeader header;
	
	private VueCentrale contentContainer;
	private JPanel jpanelCentral; //the first central JPanel

	private EcouteurDeBouton ecouteurBoutons;
	
	private JPanel footer;
	private greenButton importPlanButton;
	private greenButton importDemandeLivraisonButton;
	private greenButton exportButton;
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
		
		//goToPlanOpened();

	}
	
	private void initListeners(){
		ecouteurBoutons = new EcouteurDeBouton(ctrl);
	}
	
	private void initButtons(){
		exportButton = new greenButton(Textes.BUTTON_EXPORT_ROUTE);
		exportButton.addActionListener(ecouteurBoutons);
		exportButton.setActionCommand("export-feuille");
		
		importPlanButton = new greenButton(Textes.BUTTON_IMPORT_PLAN);
		importPlanButton.addActionListener(ecouteurBoutons);
		importPlanButton.setActionCommand("import-plan");
		
		importDemandeLivraisonButton = new greenButton(Textes.BUTTON_IMPORT_DEMANDE_LIVRAISON);
		importDemandeLivraisonButton.addActionListener(ecouteurBoutons);
		importDemandeLivraisonButton.setActionCommand("import-demande-livraison");

	}
	
	private void initFenetre(){
		setSize(800,600);
		setResizable(false);
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
	
	
	private void setContent(JPanel panel){
		contentContainer = new VueCentrale(panel);
		getContentPane().remove(jpanelCentral);
		getContentPane().add(contentContainer, BorderLayout.CENTER);
	}
	
	private void setFooter(int vueInt){
		switch(vueInt){
		case VUE_DEFAUT:
			footer.remove(importDemandeLivraisonButton);
			footer.remove(exportButton);
			break;
		case VUE_PLAN:
			footer.add(importDemandeLivraisonButton);
			footer.remove(exportButton);
			break;
		case VUE_CHARGEE:
			footer.remove(importDemandeLivraisonButton);
			footer.add(exportButton);
			break;
		}
		
		getContentPane().add(footer, BorderLayout.SOUTH);
	}

	private void goToPlanOpened(){
		if(plan!=null){
			setContent(new VuePlan(plan));
			setFooter(VUE_PLAN);
			repaint();
		}
	}
	private void goToChargee(){
		if(plan!=null){
			setFooter(VUE_CHARGEE);
			repaint();
		}
	}

}
