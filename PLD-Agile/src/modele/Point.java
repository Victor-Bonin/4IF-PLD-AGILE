package modele;

public class Point {
	private int x;
	private int y;
	
	/**
	 * Cree un point de coordonnees (x,y)
	 * @param x
	 * @param y
	 */
	protected Point(int x, int y){ // Design Pattern FlyWeight : les instances de Point sont creees par PointFactory
		this.x = x;
		this.y = y;
	}

	/**
	 * Retourne le point correspondant au deplacement de this selon (deltaX,deltaY)
	 * @param deltaX 
	 * @param deltaY 
	 */
	public Point deplace(int deltaX, int deltaY) {
		return PointFactory.creePoint(x+deltaX, y+deltaY);
	}
		
	/**
	 * Calcule la distance entre les points this et p
	 * @param p
	 * @return la distance entre this et p
	 */
	public int distance(Point p){
		return (int)(Math.sqrt((x-p.getX())*(x-p.getX()) + (y-p.getY())*(y-p.getY())));
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
