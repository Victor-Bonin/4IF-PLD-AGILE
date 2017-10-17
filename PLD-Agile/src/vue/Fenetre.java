package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 4042713508717400450L;

	private VueHeader header;
	
	private VueCentrale contentContainer;

	private JPanel footer;
	private JButton exportButton;
	
	
	public Fenetre(){
		super("PlanCo");
		
		initFenetre();
		
		initHeader();
		initContent();
		initFooter();
		
		setVisible(true);
	}
	
	private void initFenetre(){
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
	}
	
	private void initContent(){
		contentContainer = new VueCentrale(new VuePlan());
		getContentPane().add(contentContainer, BorderLayout.CENTER);
	}

	private void initHeader(){
		header = new VueHeader();
		
		header.changeNotification("Vous devez importer un plan avant de continuer");
		
		getContentPane().add(header, BorderLayout.NORTH);
	}
	
	private void initFooter(){
		
		footer = new JPanel();
		footer.setBackground(Color.RED);
		
		exportButton = new JButton("Exporter la feuille de route");

		footer.add(exportButton);
		
		getContentPane().add(footer, BorderLayout.SOUTH);
	}

}
