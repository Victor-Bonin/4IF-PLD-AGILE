package vue;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 4042713508717400450L;
	
	public Fenetre(){
		super("PlanCo");
		
		initFenetre();
	}
	
	private void initFenetre(){
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		VuePlan plan = new VuePlan();
		
		/* TODO : A supprimer */
		setContentPane(plan);     
	}

}
