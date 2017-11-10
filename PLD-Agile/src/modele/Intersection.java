package modele;

/**
 * <pre>
 * Objet representant un point sur la carte (x, y et id).
 * 
 * Authors : 
 * romain.goutte-fangeas@insa-lyon.fr
 *               ____
 *           __--    --_
 *          /   -        -
 *         / /-- ------\  \
 *        / /           \  |
 *        | |           ?  |
 *        | ? _--   -== \ /?
 *         \| 'o > < o>  |||
 *         \\    / \      )|
 *          \\   .| )    |_/
 *           |  :_____: :|
 *            \  <==="  /|
 *             \      .: /|\
 *             )\_   .: / |:"--___
 *         __-:|\ """ _-  |:::::::
 *       _-::::\ "-_.-   /::::::::
 *    _--:::::::| .|"-_  |::::::::
 *  -"::::::::::\  | { -_|::::::::
 * lucas.ouaniche-herbin@insa-lyon.fr
 * lucas.marie@insa-lyon.fr
 * clara.pourcel@insa-lyon.fr
 * pierrick.chauvet@insa-lyon.fr
 * bastien.guiraudou@insa-lyon.fr
 * victor.bonin@insa-lyon.fr
 * </pre>
 *  
 * @author 4104
 */
public class Intersection {

	private long id;
	private int x;
	private int y;

	/**
	 * Constructeur d'une intersection, prend les coordonnes x et y ainsi que l'identifiant de l'intersection. 
	 * @param coordX l'abscisse de l'intersection sur le plan
	 * @param coordY l'ordonnee de l'intersection sur le plan
	 * @param identifiant l'identifiant de l'intersection
	 */
	public Intersection(int coordX, int coordY, long identifiant) {
		x = coordX;
		y = coordY;
		id = identifiant;
	}

	/**
	 * Constructeur d'une intersection a partir d'une autre intersection
	 * @param inter l'intersection
	 */
	public Intersection(Intersection inter) {
		x = inter.x;
		y = inter.y;
		id = inter.id;
	}

	/**
	 * Verifie si l'adresse de l'entrepot et de l'intersection sont les memes.
	 * Si c'est le cas retourne true, sinon false.
	 * @param obj l'intersection a comparer
	 * @return true si les id des 2 intersections sont égaux, false sinon
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Intersection))
			return false;
		return (this.id == ((Intersection)obj).getId());
	}

	/**
	 * Retourne l'id de l'intersection.
	 * @return l'id de l'intersection
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Retourne la coordonnée x de l'intersection.
	 * @return l'abscisse de l'intersection
	 */
	public int getX() {
		// Rotation de la carte car les donnees des fichiers XML n'ont pas le même repère
		return y;
	}

	/**
	 * Retourne la coordonnée y de l'intersection.
	 * @return l'ordonnée de l'intersection
	 */
	public int getY() {
		// Rotation de la carte car les donnees des fichiers XML n'ont pas le même repère
		return 10000000 - x;
	}

}
