package vue.etat;

import javax.swing.JPanel;
import vue.Fenetre;

public abstract class EtatDefaut implements Etat{

	@Override
	public void setFooter(JPanel footer, Fenetre fenetre) {}
	@Override
	public void afficherVue(Fenetre fenetre) {}
}
