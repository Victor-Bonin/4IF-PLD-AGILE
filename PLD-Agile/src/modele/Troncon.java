package modele;

/**
 * Segment entre deux intersections compose d'un nom et d'une longueur
 * @author 4104
 */
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
	
	public Troncon(Intersection debut, Intersection fin) {
		interDebut = debut;
		interFin = fin;
	}
	
	public boolean equals(Object obj) {
		return (this.interDebut == ((Troncon)obj).getDebut() && this.interFin == ((Troncon)obj).getFin());
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
