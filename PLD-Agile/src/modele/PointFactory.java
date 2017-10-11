package modele;


public class PointFactory {
	private static Point points[][];
	private static int largeur;
	private static int hauteur;

	/**
	 * Cree une fabrique de points capable de creer des points dont les coordonnees (x,y) 
	 * sont telles que 0 <= x <= largeur et 0 <= y <= hauteur
	 * Utilisation du design pattern fly weight pour recycler les points crees
	 * @param largeur
	 * @param hauteur
	 */
	public static void initPointFactory(int largeur, int hauteur){
		PointFactory.largeur = largeur;
		PointFactory.hauteur = hauteur;
		PointFactory.points = new Point[largeur+1][hauteur+1];
	}
	
	/** 
	 * @param x
	 * @param y
	 * @return une instance p de Point telle que p.x = x et p.y = y
	 */
	public static Point creePoint(int x, int y){
		if ((x > largeur) || (x < 0) || (y > hauteur) || (y < 0))
			return null;
		if (points[x][y] == null)
			points[x][y] = new Point(x,y);
		return points[x][y];
	}
}
