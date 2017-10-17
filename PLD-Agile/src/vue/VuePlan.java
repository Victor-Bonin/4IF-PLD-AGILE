package vue;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.Plan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
		this.setBackground(new Color(168,168,168)); 
		this.plan = plan;
		this.coordoneeX = 27562;
		this.coordoneeY = 15366;
		this.zoom = 80;
	}
	
	public void paintComponent(Graphics g){
		try {

			Image img = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(2));
			float maxX = plan.getIntersections().get(0).getX();
			float maxY = plan.getIntersections().get(0).getY();
			float minX = plan.getIntersections().get(0).getX();
			float minY = plan.getIntersections().get(0).getY();
			for(int i=0; i<plan.getIntersections().size(); i++) {
				if(plan.getIntersections().get(i).getX()>maxX) {
					maxX = plan.getIntersections().get(i).getX();
				} else if (plan.getIntersections().get(i).getX()<minX) {
					minX = plan.getIntersections().get(i).getX();
				}
				if(plan.getIntersections().get(i).getY()>maxY) {
					maxY = plan.getIntersections().get(i).getY();
				} else if (plan.getIntersections().get(i).getY()<minY){
					minY = plan.getIntersections().get(i).getY();
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