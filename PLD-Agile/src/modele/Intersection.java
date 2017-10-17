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
	
	public long getid()
	{
		return id;
	}
}
