package vue;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Troncon;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe correspond à la vue du plan en particulier
 * 
 * @author 4104
 *
 */
public class VuePlan extends JPanel{
	private static final long serialVersionUID = 7580988360699236386L;

	private Controleur ctrl;
	
	private int hauteurBalise = 40;
	private int largeurBalise = 40;
	private BufferedImage imgLivraison;
	private BufferedImage imgEntrepot;
	
	private Plan plan;
	private float coordonneeX;
	private float coordonneeY;
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
	
	
	public VuePlan(Controleur ctrl, Plan plan){
		this.ctrl = ctrl;
		this.plan = plan;
		
		try {
			imgLivraison = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			imgEntrepot = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR));
		} catch (IOException e) {
	    	e.printStackTrace();
	    }  
		
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
	//TODO augmenter la taille des routes avec le zoom
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		if (firstCall){

			initMinMax();
			
			/*float centreX = (minX+maxX)/2;
			float centreY = (minY+maxY)/2;*/
			float rapportX = (maxX-minX)/this.getWidth();	
			float rapportY = (maxY-minY)/this.getHeight();
			
			if(rapportX>rapportY) {
				zoom = rapportX;
			} else {
				zoom = rapportY;
			}

			coordonneeX = getWidth()/2*(1 + (minX+maxX)/(minX-maxX));
			coordonneeY = this.getHeight()/2;
			
			firstCall = false;
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		//L'antialiasing permet de lisser les lignes !
		g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(CharteGraphique.GRAPH_TRONCON);
		g2d.setStroke(new BasicStroke(2));
		//g2d.setStroke(new BasicStroke((100-zoom)/10));

		//Dessiner chacune des rues
		for(int i=0; i<plan.getTroncons().size(); i++) {
			Troncon t = plan.getTroncons().get(i);
			g2d.drawLine(positionX(t.getDebut().getX()), 
				positionY(t.getDebut().getY()), 
				positionX(t.getFin().getX()), 
				positionY(t.getFin().getY()));
			
		}
		
		//Dessiner les tronçons de la tournée
		if(plan.getTournee()!=null){
			g2d.setColor(CharteGraphique.GRAPH_TRONCON_WAY);
			for(int i=0; i<plan.getTournee().getItineraire().size(); i++) {
				for(int j=0; j<plan.getTournee().getItineraire().get(i).getTroncons().size();j++){
					Troncon troncon = plan.getTournee().getItineraire().get(i).getTroncons().get(j);
					g2d.drawLine(positionX(troncon.getDebut().getX()), 
							positionY(troncon.getDebut().getY()),
							positionX(troncon.getFin().getX()),
							positionY(troncon.getFin().getY()));
				}
			}
		}
		
		//Dessiner les icones de points de livraisons
		for (Livraison livraison : plan.getDemandeLivraison().getLivraisons()) {
			g2d.drawImage(imgLivraison, 
					positionX(livraison.getX())-largeurBalise/2, 
					positionY(livraison.getY())-hauteurBalise, 
					largeurBalise, 
					hauteurBalise, this);
		}
		//Dessiner l'icone de l'entrepot
		if (plan.getDemandeLivraison().getEntrepot()!=null) {
		 g2d.drawImage(imgEntrepot, 
				 positionX(plan.getDemandeLivraison().getEntrepot().getX())-largeurBalise/2, 
				 positionY(plan.getDemandeLivraison().getEntrepot().getY())-hauteurBalise, 
				 largeurBalise, 
				 hauteurBalise, 
				 this);
		}
		
		// Ecrireles numéros de la tournée
		g2d.setColor(CharteGraphique.GRAPH_TEXT_COLOR);
		g2d.setFont(CharteGraphique.TEXT_BIG_FAT_FONT);
		if(plan.getTournee()!=null){
			for(int i=0; i<plan.getTournee().getLivraisons().size(); i++) {
				Livraison livraison = plan.getTournee().getLivraisons().get(i);
				g2d.drawString(Integer.toString(i+1), 
						positionX(livraison.getX())-8, 
						positionY(livraison.getY())+20);
			}
		}
		
		// Ecrire les numéros de la demande de livraison
		g2d.setColor(CharteGraphique.GRAPH_TEXT_COLOR);
		g2d.setFont(CharteGraphique.TEXT_BIG_FAT_FONT);
		if(plan.getTournee()==null){
			for(int i=0; i<plan.getDemandeLivraison().getLivraisons().size(); i++) {
				Livraison livraison = plan.getDemandeLivraison().getLivraisons().get(i);
				g2d.drawString(Integer.toString(i+1), 
						positionX(livraison.getX())-8, 
						positionY(livraison.getY())+20);
			}
		}
			              
	  }

	private int positionX(int x) {
		return (int) ((x-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom));
	}
	private int positionY(int y) {
		return (int)((y-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom));
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