package modele;

import modele.Cercle;
import modele.Rectangle;

public interface VisiteurPourAfficherFormes {
	// Mise en oeuvre du design pattern Visiteur pour afficher des formes
	public void affiche(Cercle c);
	public void affiche(Rectangle r);
}
