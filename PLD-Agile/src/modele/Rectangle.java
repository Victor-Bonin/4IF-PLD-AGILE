package modele;

public class Rectangle extends Forme {

	private Point coin;
	private int largeur;
	private int hauteur;

	public Rectangle(Point coin, int largeur, int hauteur ){
		super();
		this.coin = coin;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
			
	public Point getCoin() {
		return coin;
	}

	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur(){
		return hauteur;
	}
	
	@Override
	public boolean contient(Point p) {
		return (p.getX() >= coin.getX()) && (p.getX() <= coin.getX() + largeur) &&
				(p.getY() >= coin.getY()) && (p.getY() <= coin.getY() + hauteur);
	}
	
	@Override
	public void deplace(int deltaX, int deltaY) {
		coin = coin.deplace(deltaX, deltaY);
		super.deplace(deltaX,deltaY);
	}
	
	@Override
	public boolean contenuDans(Rectangle r) {
		Point coinOppose = PointFactory.creePoint(coin.getX()+largeur,coin.getY()+hauteur);
		return (coinOppose != null) && (r.contient(coin)) && (r.contient(coinOppose));
	}
	
	/**
	 * Met a jour this de sorte que (x,y) corresponde a un coin de this et p au coin oppose a (x,y)
	 * Si apres la mise a jour une forme de plan n'est pas disjointe de this, alors ajuste largeur 
	 * et hauteur de sorte que this soit disjoint de toutes les formes de plan
	 * @param x
	 * @param y
	 * @param p
	 * @param plan
	 */
	public void miseAJourTaille(int x, int y, Point p, Plan plan) {
		Point sauveCoin = coin;
		int sauveLargeur = largeur;
		int sauveHauteur = hauteur;
		largeur = Math.abs(p.getX() - x);
		coin = PointFactory.creePoint(Math.min(x, p.getX()),coin.getY());
		if (!plan.tousDisjoints(this)){
			largeur = sauveLargeur;
			coin = sauveCoin;
		}
		hauteur = Math.abs(p.getY() - y);
		coin = PointFactory.creePoint(coin.getX(),Math.min(y, p.getY()));
		if (!plan.tousDisjoints(this)){
			hauteur = sauveHauteur;
			coin = sauveCoin;
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean disjoint(Forme f) {
		if (f instanceof Cercle)
			return disjoint((Cercle)f);
		else
			return disjoint((Rectangle)f);
	}
	
	private boolean disjoint(Rectangle r){    
        int xOverlap = Math.max(0, 
        		Math.min(coin.getX()+largeur,r.getCoin().getX()+r.getLargeur()) -
        		Math.max(coin.getX(),r.getCoin().getX()));
        int yOverlap = Math.max(0, 
        		Math.min(coin.getY()+hauteur,r.getCoin().getY()+r.getHauteur()) - 
        		Math.max(coin.getY(),r.getCoin().getY()));
		return (xOverlap == 0) || (yOverlap == 0);
	}
	
	private boolean disjoint(Cercle c){
		int xProche = Math.min(Math.max(c.getCentre().getX(), coin.getX()), coin.getX()+largeur);
		int yProche = Math.min(Math.max(c.getCentre().getY(), coin.getY()), coin.getY()+hauteur);
		return c.getCentre().distance(new Point(xProche,yProche)) >= c.getRayon();
	}

	@Override
	public boolean deplacementPossible(int deltaX, int deltaY, Plan planche) {
		Point nouveauCoin = coin.deplace(deltaX, deltaY);
		if (nouveauCoin == null) return false;
		coin = nouveauCoin;
		boolean b = planche.contient(this) && planche.tousDisjoints(this);
		coin = nouveauCoin.deplace(-deltaX,  -deltaY);
		return b;
	}
	
	@Override
	public void affiche(VisiteurPourAfficherFormes v){
		v.affiche(this);
	}
}
