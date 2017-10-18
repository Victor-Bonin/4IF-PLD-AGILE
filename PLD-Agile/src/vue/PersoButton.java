package vue;

import javax.swing.JButton;

public class PersoButton extends JButton{
	private static final long serialVersionUID = 6534684555513953601L;
	
	public PersoButton(String str, int style){
		super(str);
		switch(style){
		case 1:
			setBackground(CharteGraphique.BUTTON_GREEN_BG);
			break;
		case 2:
			setBackground(CharteGraphique.BUTTON_WHITE_BG);
			break;
		}
		setFocusPainted(false);
		setFont(CharteGraphique.TEXT_SECONDARY_FONT);
	}
	
	
	
}
