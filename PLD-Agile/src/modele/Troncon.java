package modele;

public class Troncon {
	private int longueur;
	private String nomRue;
	private Intersection interDebut;
	private Intersection interFin;
	
	public int getLongueur(){
		return this.longueur;
	}
	
	public Intersection getDebut(){
		return this.interDebut;
	}
	
	public Intersection getFin(){
		return this.interFin;
	}
	

}
