package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Plan;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 4042713508717400450L;

	private VueHeader header;
	
	private VueCentrale contentContainer;

	private JPanel footer;
	private JButton exportButton;
	private Plan plan;
	
	
	public Fenetre(Plan plan){
		super(Textes.NOM_APPLI);
		
		initFenetre();
		
		initHeader();
		initContent();
		initFooter();
		
		setVisible(true);
		this.plan = plan;
	}
	
	private void initFenetre(){
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
	}
	
	private void initContent(){
		contentContainer = new VueCentrale(new VuePlan(plan));
		getContentPane().add(contentContainer, BorderLayout.CENTER);
	}

	private void initHeader(){
		header = new VueHeader();
		
		header.changeNotification(Textes.NOTIF_MUST_IMPORT);
		
		getContentPane().add(header, BorderLayout.NORTH);
	}
	
	private void initFooter(){
		
		footer = new JPanel();
		footer.setBackground(CharteGraphique.BG_COLOR);
		
		exportButton = new JButton(Textes.BUTTON_EXPORT_ROUTE);

		footer.add(exportButton);
		
		getContentPane().add(footer, BorderLayout.SOUTH);
	}

}
