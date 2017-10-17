package modele;

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
	
	public long getid()
	{
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
