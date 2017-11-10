package modele;

/**
 * Objet représentant un point sur la carte (x, y et id)
 * @author 4104
 */
public class Intersection {
	private long id;
	private int x;
	private int y;

	public Intersection(int coordX, int coordY, long identifiant) {
		x = coordX;
		y = coordY;
		id = identifiant;
	}
	
	public Intersection(Intersection inter) {
		x = inter.x;
		y = inter.y;
		id = inter.id;
	}
		
	/**
	 * Verifie si l'adresse de l'entrepot et de l'intersection sont les memes
	 * @param obj Une intersection
	 * @return true si les adresses sont identiques
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Intersection))
			return false;
		return (this.id == ((Intersection)obj).getId());
	}
	
	public long getId(){
		return this.id;
	}

	// Rotation de la carte car les donnees des fichiers XML n'ont pas le même repère
	public int getX() {
		return y;
	}

	public int getY() {
		// Rotation de la carte car les donnees des fichiers XML n'ont pas le même repère
		return 10000000 - x;
	}

}
