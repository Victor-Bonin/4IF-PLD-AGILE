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
	
	public boolean equals(Intersection obj) {
		return (this.id == obj.getId());
	}
	
	public Intersection(Intersection inter) {
		x = inter.x;
		y = inter.y;
		id = inter.id;
	}
	
	// Lol...
	//TODO wtf ?
	public int getX() {
		return y;
	}

	// Lol...
	//TODO wtf ?
	public int getY() {
		return 10000000 - x;
	}
}
