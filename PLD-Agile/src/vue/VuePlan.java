package vue;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class VuePlan extends JPanel{
	private static final long serialVersionUID = 7580988360699236386L;
	
	
	public VuePlan(){
		setBackground(Color.GRAY); 
		
	}
	
	public void paintComponent(Graphics g){
		try {
			Image img = ImageIO.read(new File("ressources/map-marker.png"));
			//drawImage(Image img, int x, int y, int width, int height, Observer obs)
			g.drawImage(img, 0, 0, 40, 40, this);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }                
	  }

}