package vue.etat;

import javax.swing.JPanel;

import vue.Fenetre;

public interface Etat {
	void setFooter(JPanel footer, Fenetre fenetre);
	void afficherVue(Fenetre fenetre);
}
