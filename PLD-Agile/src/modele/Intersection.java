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
	
	public long getId(){
		return this.id;
	}
	
	public boolean equals(Object obj) {
		return (this.id == ((Intersection)obj).getId());
	}
	
	public Intersection(Intersection inter) {
		x = inter.x;
		y = inter.y;
		id = inter.id;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
