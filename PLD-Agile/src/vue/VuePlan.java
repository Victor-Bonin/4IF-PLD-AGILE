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

public class VuePlan extends JPanel{
	private static final long serialVersionUID = 7580988360699236386L;
	private int hauteurBalise = 40;
	private int largeurBalise = 40;
	private Plan plan;
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
	}
	
	public void paintComponent(Graphics g){
		try {
			/*for(int i=0; i<plan.getTroncons().size(); i++) {
				
			}*/
			Image img = ImageIO.read(new File("ressources/map-marker2.png"));
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(10));
			
			// TODO : Boucle sur intersections et sommets
			g2d.drawLine(x1+largeurBalise/2, y1+hauteurBalise/2, x2+largeurBalise/2, y2+hauteurBalise/2);
			g2d.drawLine(x1+largeurBalise/2, y1+hauteurBalise/2, x3+largeurBalise/2, y3+hauteurBalise/2);
			g2d.drawLine(x2+largeurBalise/2, y2+hauteurBalise/2, x3+largeurBalise/2, y3+hauteurBalise/2);
			g2d.drawImage(img, x1, y1, largeurBalise, hauteurBalise, this);
			g2d.drawImage(img, x2, y2, largeurBalise, hauteurBalise, this);
			g2d.drawImage(img, x3, y3, largeurBalise, hauteurBalise, this);
			
			
			
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }                
	  }

}