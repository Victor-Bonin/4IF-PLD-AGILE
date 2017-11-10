package modele;

/**
 * Segment entre deux intersections compose d'un nom et d'une longueur
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
 * 
 *  
 * @author 4104
 */
public class Troncon {

	private float longueur;
	private String nomRue;
	private Intersection interDebut;
	private Intersection interFin;

	/**
	 * Constructeur de troncon a partir de deux intersections, une de debut, une de fin, le nom de la rue et la longueur du troncon.
	 * @param debut l'intersection d'origine
	 * @param fin l'intersection de destination
	 * @param rue le nom de la rue
	 * @param l la longueur en metre de la rue
	 */
	public Troncon(Intersection debut, Intersection fin, String rue, float l) {
		longueur = l;
		nomRue = rue;
		interDebut = debut;
		interFin = fin;
	}

	/**
	 * Constructeur de troncon a partir de deux intersection, une de début, une de fin. 
	 * @param debut l'intersection d'origine
	 * @param fin l'intersection de destination
	 */
	public Troncon(Intersection debut, Intersection fin) {
		interDebut = debut;
		interFin = fin;
	}

	/**
	 * Compare les troncons en comparant le début et la fin de chaque troncon.
	 * @param obj le troncon a comparer
	 * @return true si les intersections sont les memes, false sinon
	 */
	public boolean equals(Object obj) {
		return (this.interDebut.equals(((Troncon)obj).getDebut()) && this.interFin.equals(((Troncon)obj).getFin()));
	}

	/**
	 * Retourne la longueur du troncon en metre.
	 * @return la longueur du troncon
	 */
	public float getLongueur(){
		return this.longueur;
	}

	/**
	 * Retourne le debut du troncon.
	 * @return l'intersection d'origine du troncon
	 */
	public Intersection getDebut(){
		return this.interDebut;
	}

	/**
	 * Retourne l'arrivee du troncon.
	 * @return l'intersection de destination du troncon
	 */
	public Intersection getFin(){
		return this.interFin;
	}

	/**
	 * Retourne le nom de la rue.
	 * @return String
	 */
	public String GetNomRue() {
		return nomRue;
	}
}
