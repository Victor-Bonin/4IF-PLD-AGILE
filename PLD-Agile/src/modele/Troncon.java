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
	public float getLongueur(){
		return this.longueur;
	}
	
	public Intersection getDebut(){
		return this.interDebut;
	}
	
	public Intersection getFin(){
		return this.interFin;
	}
	
}
