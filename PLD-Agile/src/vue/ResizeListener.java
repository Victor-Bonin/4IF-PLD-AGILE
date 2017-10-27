package vue;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListener extends ComponentAdapter {
	private VuePlan vuePlan;
	
	public ResizeListener(VuePlan vp){
		vuePlan = vp;
	}
	
    public void componentResized(ComponentEvent e) {
    	vuePlan.actualiserIcones();
    }

}
