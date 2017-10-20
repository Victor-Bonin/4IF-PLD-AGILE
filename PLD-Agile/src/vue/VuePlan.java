package vue;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controleur.Controleur;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Troncon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
	//private static final long serialVersionUID = 7580988360699236386L;

	private Controleur ctrl;
	
	private int hauteurBalise = 40;
	private int largeurBalise = 40;
	private BufferedImage imgLivraison;
	private BufferedImage imgEntrepot;
	
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
	//TODO écouteur de souris dans VuePlan et pas Fenetre
	//TODO charger l'image en dehors du paintComponent
	//TODO enlever le maximum du try catch
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
				//System.out.println(plan.getTroncons().get(i).getDepart().getX()+"  "+plan.getTroncons().get(i).getDepart().getY());
				g2d.drawLine((int)((plan.getTroncons().get(i).getDebut().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)), (int)((plan.getTroncons().get(i).getDebut().getY()-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom))
						, (int)((plan.getTroncons().get(i).getFin().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)), (int)((plan.getTroncons().get(i).getFin().getY()-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom)));
				
			}
			
			//Dessiner les tronçons de la tournée
			if(plan.getTournee()!=null){
				g2d.setColor(CharteGraphique.GRAPH_TRONCON_WAY);
				for(int i=0; i<plan.getTournee().getItineraire().size(); i++) {
					for(int j=0; j<plan.getTournee().getItineraire().get(i).getTroncons().size();j++){
						Troncon troncon = plan.getTournee().getItineraire().get(i).getTroncons().get(j);
						g2d.drawLine((int)((troncon.getDebut().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)), (int)((troncon.getDebut().getY()-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom))
								, (int)((troncon.getFin().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)), (int)((troncon.getFin().getY()-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom)));
					}
				}
			}
			
			//Dessiner les icones de points de livraisons
			for (Livraison livraison : plan.getDemandeLivraison().getLivraisons()) {
				g2d.drawImage(imgLivraison, (int)((livraison.getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)-largeurBalise/2), (int)((livraison.getY()-hauteurBalise-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom)-hauteurBalise), largeurBalise, hauteurBalise, this);
			}
			//Dessiner l'icone de l'entrepot
			if (plan.getDemandeLivraison().getEntrepot()!=null) {
			 g2d.drawImage(imgEntrepot, (int)((plan.getDemandeLivraison().getEntrepot().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)-largeurBalise/2), (int)((plan.getDemandeLivraison().getEntrepot().getY()-hauteurBalise-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom)-hauteurBalise), largeurBalise, hauteurBalise, this);
			}
			
			// Ecrireles numéros de la tournée
			g2d.setColor(CharteGraphique.GRAPH_TEXT_COLOR);
			g2d.setFont(CharteGraphique.TEXT_BIG_FAT_FONT);
			if(plan.getTournee()!=null){
				for(int i=0; i<plan.getTournee().getLivraisons().size(); i++) {
					Livraison livraison = plan.getTournee().getLivraisons().get(i);
					g2d.drawString(Integer.toString(i+1), (int)((livraison.getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)-8), (int)((livraison.getY()-hauteurBalise-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom)+20));
				}
			}
			
			// Ecrireles numéros de la demande de livraison
			g2d.setColor(CharteGraphique.GRAPH_TEXT_COLOR);
			g2d.setFont(CharteGraphique.TEXT_BIG_FAT_FONT);
			if(plan.getTournee()==null){
				for(int i=0; i<plan.getDemandeLivraison().getLivraisons().size(); i++) {
					Livraison livraison = plan.getDemandeLivraison().getLivraisons().get(i);
					g2d.drawString(Integer.toString(i+1), (int)((livraison.getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)-8), (int)((livraison.getY()-hauteurBalise-minY)/zoom+this.getHeight()/2-(maxY-minY)/(2*zoom)+20));
				}
			}
			
			// TODO : Livraison
			/*
			g2d.drawLine(x1+largeurBalise/2, y1+hauteurBalise/2, x2+largeurBalise/2, y2+hauteurBalise/2);
			g2d.drawLine(x1+largeurBalise/2, y1+hauteurBalise/2, x3+largeurBalise/2, y3+hauteurBalise/2);
			g2d.drawLine(x2+largeurBalise/2, y2+hauteurBalise/2, x3+largeurBalise/2, y3+hauteurBalise/2);
			g2d.drawImage(img, x1, y1, largeurBalise, hauteurBalise, this);
			g2d.drawImage(img, x2, y2, largeurBalise, hauteurBalise, this);
			g2d.drawImage(img, x3, y3, largeurBalise, hauteurBalise, this);*/
			
			//  (plan.getTroncons().get(i).getDebut().getX()-minX)/zoom+this.getWidth()/2-(maxX-minX)/(2*zoom)
			              
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