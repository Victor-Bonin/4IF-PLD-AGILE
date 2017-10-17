package modele;

public class Intersection {
	private long id;
	private int x;
	private int y;
	
	public long getId(){
		return this.id;
	}
	
	public boolean equals(Intersection obj) {
		return (this.id == obj.getId());
	}
}
