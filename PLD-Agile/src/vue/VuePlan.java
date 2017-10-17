package vue;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.Intersection;
import modele.Plan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Cette classe correspond à la vue du plan en particulier
 * 
 * @author 4104
 *
 */
public class VuePlan extends JPanel{
	private static final long serialVersionUID = 7580988360699236386L;
	private int hauteurBalise = 40;
	private int largeurBalise = 40;
	private Plan plan;
	private float coordoneeX;
	private float coordoneeY;
	private float zoom;
	// TODO : Supprimer
	private int x1 = 20;
	private int y1 = 20;
	private int x2 = 200;
	private int y2 = 200;
	private int x3 = 20;
	private int y3 = 200;
	
	
	public VuePlan(Plan plan){
		setBackground(CharteGraphique.GRAPH_BG); 
		this.plan = plan;
		this.coordoneeX = 27562;
		this.coordoneeY = 15366;
		this.zoom = 80;
	}
	
	
	//TODO antialiasing !!!! je souffre des yeux là 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try {

			Image img = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(CharteGraphique.GRAPH_TRONCON);
			g2d.setStroke(new BasicStroke(2));
			float maxX = Float.MIN_VALUE;
			float maxY = Float.MIN_VALUE;
			float minX = Float.MAX_VALUE;
			float minY = Float.MAX_VALUE;
			
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
			

			
			float centreX = (minX+maxX)/2;
			float centreY = (minY+maxY)/2;
			float rapportX = (maxX-minX)/this.getWidth();			
			float rapportY = (maxY-minY)/this.getHeight();
			
			if(rapportX>rapportY) {
				zoom = rapportX;
			} else {
				zoom = rapportY;
			}
			

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

}