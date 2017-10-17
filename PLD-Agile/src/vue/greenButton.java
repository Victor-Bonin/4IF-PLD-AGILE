package vue;

import javax.swing.JButton;

public class greenButton extends JButton{
	private static final long serialVersionUID = 6534684555513953601L;
	
	public greenButton(String str){
		super(str);
		setBackground(CharteGraphique.BUTTON_GREEN_BG);
		setFocusPainted(false);
		setFont(CharteGraphique.TEXT_SECONDARY_FONT);
	}
	
	
	
}
