package modele;

public class Cercle extends Forme{
	private Point centre;
	private int rayon;
	
	/**
	 * Cree un cercle de centre c et de rayon r
	 * @param c
	 * @param r
	 */
	public Cercle(Point c, int r){
		super();
		this.rayon = r;
		this.centre = c;
	}
	
	public Point getCentre() {
		return centre;
	}
	
	public int getRayon() {
		return rayon;
	}
	
	@Override
	public boolean contient(Point p) {
		return centre.distance(p) <= rayon;
	}
	
	@Override
	public void deplace(int deltaX, int deltaY) {
		centre = centre.deplace(deltaX, deltaY);
		super.deplace(deltaX, deltaY);
	}
	
	@Override
	public boolean contenuDans(Rectangle r) {
		Point pMin = PointFactory.creePoint(centre.getX()-rayon, centre.getY()-rayon);
		if (pMin == null) 
			return false;
		Point pMax = PointFactory.creePoint(centre.getX()+rayon, centre.getY()+rayon);
		if (pMax == null) 
			return false;
		return r.contient(pMin) && r.contient(pMax);
	}
	
	/**
	 * Met a jour le rayon de this en fonction d'un point p appartenant a son perimetre, 
	 * de telle sorte que this soit entierement inclus dans plan, et soit disjoint
	 * de toutes les formes de planche.
	 * @param p un point appartenant au perimetre de this
	 */
	public void miseAJourTaille(Point p, Plan plan) {
		int sauveRayon = rayon;
		rayon = p.distance(centre);
		if ((!plan.tousDisjoints(this)) || (!plan.contient(this))){
			rayon = sauveRayon;
		} else {
			setChanged();
			notifyObservers();
		}
	}
	
	@Override
	public boolean disjoint(Forme f) {
		if (f instanceof Cercle)
			return disjoint((Cercle)f);
		else
			return disjoint((Rectangle)f);
	}
	
	private boolean disjoint(Rectangle r){
		return r.disjoint(this);
	}
	
	private boolean disjoint(Cercle cercle){
		int a = Math.abs(cercle.getRayon()-rayon) ;
		a *= a;
		int b = cercle.getCentre().getX()-centre.getX();
		b *= b;
		int c = cercle.getCentre().getY()-centre.getY();
		c *= c;
		int d = Math.abs(cercle.getRayon()+rayon);
		d *= d;
		return (a >= b+c) || (b+c >= d);
	}

	@Override
	public boolean deplacementPossible(int x, int y, Plan plan) {
		if (centre.deplace(x, y) == null) return false;
		centre = centre.deplace(x, y);
		boolean b = plan.contient(this) && plan.tousDisjoints(this);
		centre = centre.deplace(-x, -y);
		return b;
	}
	
	@Override
	public void affiche(VisiteurPourAfficherFormes v){
		v.affiche(this);
	}

}
