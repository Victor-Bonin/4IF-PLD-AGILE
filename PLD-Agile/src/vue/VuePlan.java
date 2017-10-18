package vue;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controleur.Controleur;
import modele.Intersection;
import modele.Plan;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe correspond à la vue du plan en particulier
 * 
 * @author 4104
 *
 */
public class VuePlan extends JPanel{
	//private static final long serialVersionUID = 7580988360699236386L;

	private Controleur ctrl;
	
	//private int hauteurBalise = 40;
	//private int largeurBalise = 40;
	private Plan plan;
	//private float coordoneeX;
	//private float coordoneeY;
	private float zoom;
	private boolean firstCall = true;
	private float maxX = Float.MIN_VALUE;
	private float maxY = Float.MIN_VALUE;
	private float minX = Float.MAX_VALUE;
	private float minY = Float.MAX_VALUE;

	private PersoButton changerPlanButton;
	private PersoButton changerDemandeLivraisonButton;

	private EcouteurDeBouton ecouteurBoutons;
	private EcouteurDeSouris ecouteurSouris;
	
	// TODO : Supprimer
	//private int x1 = 20;
	//private int y1 = 20;
	//private int x2 = 200;
	//private int y2 = 200;
	//private int x3 = 20;
	//private int y3 = 200;
	
	
	public VuePlan(Controleur ctrl, Plan plan){
		this.ctrl = ctrl;
		this.plan = plan;
		
		ecouteurBoutons = new EcouteurDeBouton(ctrl);
		ecouteurSouris = new EcouteurDeSouris(ctrl, this);

		addMouseWheelListener(ecouteurSouris);

		changerPlanButton = new PersoButton(Textes.BUTTON_NOUVEAU_PLAN,2);
		changerPlanButton.addActionListener(ecouteurBoutons);
		changerPlanButton.setActionCommand("import-plan");
		
		changerDemandeLivraisonButton = new PersoButton(Textes.BUTTON_NOUVELLE_LIVRAISON,2);
		changerDemandeLivraisonButton.addActionListener(ecouteurBoutons);
		changerDemandeLivraisonButton.setActionCommand("import-demande-livraison");
		
		add(changerPlanButton);
		add(changerDemandeLivraisonButton);

		setBackground(CharteGraphique.GRAPH_BG);
	}
	
	private void initMinMax(){
		for (Intersection intersection : plan.getIntersections().values()) {
			if(intersection.getX()>maxX) {
				maxX = intersection.getX();
			} else if (intersection.getX()<minX) {
				minX = intersection.getX();
			}
			if(intersection.getY()>maxY) {
				maxY = intersection.getY();
			} else if (intersection.getY()<minY){
				minY = intersection.getY();
			}
		}
		
		
	}
	//TODO antialiasing !!!! je souffre des yeux là 
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		if (firstCall){

			initMinMax();
			
			float centreX = (minX+maxX)/2;
			float centreY = (minY+maxY)/2;
			float rapportX = (maxX-minX)/this.getWidth();	
			float rapportY = (maxY-minY)/this.getHeight();
			
			if(rapportX>rapportY) {
				zoom = rapportX;
			} else {
				zoom = rapportY;
			}
			
			firstCall = false;
		}
		
		try {

			Image img = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(CharteGraphique.GRAPH_TRONCON);
			g2d.setStroke(new BasicStroke(2));
			//g2d.setStroke(new BasicStroke((100-zoom)/10));


			for(int i=0; i<plan.getTroncons().size(); i++) {
				//System.out.println(plan.getTroncons().get(i).getDepart().getX()+"  "+plan.getTroncons().get(i).getDepart().getY());
				g2d.drawLine((int)((plan.getTroncons().get(i).getDepart().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)), (int)((plan.getTroncons().get(i).getDepart().getY()-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom))
						, (int)((plan.getTroncons().get(i).getArrivee().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)), (int)((plan.getTroncons().get(i).getArrivee().getY()-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom)));
				
			}
			
			// TODO : Livraison
			/*
			g2d.drawLine(x1+largeurBalise/2, y1+hauteurBalise/2, x2+largeurBalise/2, y2+hauteurBalise/2);
			g2d.drawLine(x1+largeurBalise/2, y1+hauteurBalise/2, x3+largeurBalise/2, y3+hauteurBalise/2);
			g2d.drawLine(x2+largeurBalise/2, y2+hauteurBalise/2, x3+largeurBalise/2, y3+hauteurBalise/2);
			g2d.drawImage(img, x1, y1, largeurBalise, hauteurBalise, this);
			g2d.drawImage(img, x2, y2, largeurBalise, hauteurBalise, this);
			g2d.drawImage(img, x3, y3, largeurBalise, hauteurBalise, this);*/
			
			
			
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }                
	  }
	
	public void zoom(){
		this.zoom-=5;
		if(zoom<=0) {
			zoom = 1;
		}
		repaint();
	}
	
	public void dezoom(){
		this.zoom+=5;
		repaint();
	}

	public JButton getButtonPlan(){
		return changerPlanButton;
	}
	public JButton getButtonDemandeLivraison(){
		return changerDemandeLivraisonButton;
	}

}