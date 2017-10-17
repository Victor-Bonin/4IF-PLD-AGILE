package modele;

public class Troncon {
	private float longueur;
	private String nomRue;
	private Intersection interDebut;
	private Intersection interFin;
	
	public Troncon(Intersection debut, Intersection fin, String rue, float l) {
		longueur = l;
		nomRue = rue;
		interDebut = debut;
		interFin = fin;
	}
	
	public Intersection getDepart() {
		return interDebut;
	}
	
	public Intersection getArrivee() {
		return interFin;
	}
}
