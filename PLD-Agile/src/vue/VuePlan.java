package vue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.DemandeLivraison;
import modele.Intersection;
import modele.Livraison;
import modele.Plan;
import modele.Troncon;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Cette classe correspond à la vue du plan en particulier
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
public class VuePlan extends JPanel{
	private static final long serialVersionUID = 7580988360699236386L;

	private Controleur ctrl;
	
	private int hauteurBalise = 40;
	private int largeurBalise = 40;
	private BufferedImage imgLivraison;
	private BufferedImage imgLivraisonSurvol;
	private BufferedImage imgEntrepot;
	private BufferedImage imgEntrepotSurvol;
	
	private Plan plan;
	private float coordonneeX = 0;
	private float coordonneeY = 0;
	private float zoom;
	private boolean firstCall = true;
	private float maxX = Float.MIN_VALUE;
	private float maxY = Float.MIN_VALUE;
	private float minX = Float.MAX_VALUE;
	private float minY = Float.MAX_VALUE;
	private double posSourisX;
	private double posSourisY;

	private PersoButton changerPlanButton;
	private PersoButton changerDemandeLivraisonButton;
	private PersoButton undoButton;
	private PersoButton redoButton;

	private EcouteurDeBouton ecouteurBoutons;
	private EcouteurDeSouris ecouteurSouris;
	private EcouteurDeSourisChoixIntersection ecouteurSourisChoixIntersec;
	
	private ArrayList<JLabel> iconesLivraison;
	private JLabel iconeEntrepot; 
	private JLabel iconeLivraisonSouris;
	private JLabel iconeNouvelleLivraison;
	private ImageIcon imageIconL;
	private ImageIcon imageIconLS;
	private ImageIcon imageIconE;
	private ImageIcon imageIconES;
	
	Intersection nouvelleIntersection;
	
	public VuePlan(Controleur ctrl, Plan plan){
		this.ctrl = ctrl;
		this.plan = plan;

		this.setLayout(null);

		try {
			imgLivraison = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON));
			imgLivraisonSurvol = ImageIO.read(new File(CharteGraphique.ICONE_LIVRAISON_SURVOL));
			imgEntrepot = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR));
			imgEntrepotSurvol = ImageIO.read(new File(CharteGraphique.ICONE_HANGAR_SURVOL));
			Image scaledImageL = imgLivraison.getScaledInstance(largeurBalise, hauteurBalise, java.awt.Image.SCALE_SMOOTH);
			imageIconL = new ImageIcon(scaledImageL);
			Image scaledImageLS = imgLivraisonSurvol.getScaledInstance(largeurBalise, hauteurBalise, java.awt.Image.SCALE_SMOOTH);
			imageIconLS = new ImageIcon(scaledImageLS);
			Image scaledImageE = imgEntrepot.getScaledInstance(largeurBalise, hauteurBalise, java.awt.Image.SCALE_SMOOTH);
			imageIconE = new ImageIcon(scaledImageE);
			Image scaledImageES = imgEntrepotSurvol.getScaledInstance(largeurBalise, hauteurBalise, java.awt.Image.SCALE_SMOOTH);
			imageIconES = new ImageIcon(scaledImageES);
			iconeEntrepot = new JLabel(imageIconE);
		} catch (IOException e) {
	    	e.printStackTrace();
	    }  
		
		iconeLivraisonSouris = new JLabel(imageIconL);
		iconeNouvelleLivraison = new JLabel(imageIconL);
		
		ecouteurBoutons = new EcouteurDeBouton(ctrl);
		ecouteurSouris = new EcouteurDeSouris(ctrl, this);

		addMouseWheelListener(ecouteurSouris);
		addMouseListener(ecouteurSouris);
		addMouseMotionListener(ecouteurSouris);
		
		changerPlanButton = new PersoButton(Textes.BUTTON_NOUVEAU_PLAN,2);
		changerPlanButton.addActionListener(ecouteurBoutons);
		changerPlanButton.setActionCommand("import-plan");
		
		changerDemandeLivraisonButton = new PersoButton("<html>" + Textes.BUTTON_NOUVELLE_LIVRAISON + "</html>",2);
		changerDemandeLivraisonButton.addActionListener(ecouteurBoutons);
		changerDemandeLivraisonButton.setActionCommand("import-demande-livraison");

		initAnnulationBouton();

		add(changerPlanButton);
		add(changerDemandeLivraisonButton);

		setBackground(CharteGraphique.GRAPH_BG);
		
		iconesLivraison = new ArrayList<JLabel>();
		
		//TODO : supprimer
		ecouteurSourisChoixIntersec = new EcouteurDeSourisChoixIntersection(ctrl, this);
	}
	
	private void initMinMax(){
		for (Intersection intersection : plan.getIntersections().values()) {
			if(intersection.getX()>maxX) {
				maxX = intersection.getX();
			} else if (intersection.getX()<minX) {
				minX = intersection.getX();
			}
			if(intersection.getY()>maxY) {
				maxY = intersection.getY();
			} else if (intersection.getY()<minY){
				minY = intersection.getY();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		
		/*
		posSourisX = this.getMousePosition().getX();
		double sourisPlanX = (posSourisX - coordonneeX - this.getWidth()/ 2 + (maxX-minX)/(2*zoom))*zoom + minX;
		posSourisY = this.getMousePosition().getY();
		double sourisPlanY = (posSourisY - coordonneeY - this.getHeight()/ 2 + (maxY-minY)/(2*zoom))*zoom + minY;
		System.out.println(sourisPlanX);
		System.out.println(sourisPlanY);
		*/
		
		if (firstCall){

			initMinMax();
			
			float rapportX = (maxX-minX)/this.getWidth();	
			float rapportY = (maxY-minY)/this.getHeight();
			
			if(rapportX>rapportY) {
				zoom = rapportX;
			} else {
				zoom = rapportY;
			}
			
			firstCall = false;
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		//L'antialiasing permet de lisser les lignes !
		g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(CharteGraphique.GRAPH_TRONCON);
		
		
		g2d.setStroke(new BasicStroke(200/zoom, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

		//Dessiner chacune des rues
		for(int i=0; i<plan.getTroncons().size(); i++) {
			Troncon t = plan.getTroncons().get(i);
			g2d.drawLine(positionX(t.getDebut().getX()), 
				positionY(t.getDebut().getY()), 
				positionX(t.getFin().getX()), 
				positionY(t.getFin().getY()));
			
		}

		
		g2d.setStroke(new BasicStroke(Math.max(100/zoom, 4), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		float decalageRueDoubleSens = Math.max(100/zoom, 4);

		//Dessiner les tronçons de la tournée
		if(plan.getTournee()!=null){
			g2d.setColor(CharteGraphique.GRAPH_TRONCON_WAY);
			for(int i=0; i<plan.getTournee().getItineraire().getChemins().size(); i++) {
				for(int j=0; j<plan.getTournee().getItineraire().getChemins().get(i).getTroncons().size();j++){
					Troncon troncon = plan.getTournee().getItineraire().getChemins().get(i).getTroncons().get(j);

					//Deplacement lie à ce vecteur
					int x1 = positionX(troncon.getDebut().getX());
					int y1 = positionY(troncon.getDebut().getY());
					
					int x2 = positionX(troncon.getFin().getX());
					int y2 = positionY(troncon.getFin().getY());
					
					if(100/zoom>2) {

						float norm = (float) Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
						float xNorm = ((float)(x2-x1))/norm;
						float yNorm = ((float)(y2-y1))/norm;

						//Considération du vecteur précédent
						float xNormPrec = 0;
						float yNormPrec = 0;
						if(j>0) {
							int x1Prec = x1;
							int y1Prec = y1;
							Troncon tronconPrec = plan.getTournee().getItineraire().getChemins().get(i).getTroncons().get(j-1);
							x1Prec = positionX(tronconPrec.getDebut().getX());
							y1Prec = positionY(tronconPrec.getDebut().getY());
							float normPrec = (float) Math.sqrt((x1-x1Prec)*(x1-x1Prec)+(y1-y1Prec)*(y1-y1Prec));
							xNormPrec = ((float)(x1-x1Prec))/normPrec;
							yNormPrec = ((float)(y1-y1Prec))/normPrec;
						}

						//Considération du vecteur suivant
						float xNormSuiv = 0;
						float yNormSuiv = 0;
						if(j+1<plan.getTournee().getItineraire().getChemins().get(i).getTroncons().size()) {
							int x2Suiv = x2;
							int y2Suiv = y2;
							Troncon tronconSuiv = plan.getTournee().getItineraire().getChemins().get(i).getTroncons().get(j+1);
							x2Suiv = positionX(tronconSuiv.getFin().getX());
							y2Suiv = positionY(tronconSuiv.getFin().getY());
							float normSuiv = (float) Math.sqrt((x2Suiv-x2)*(x2Suiv-x2)+(y2Suiv-y2)*(y2Suiv-y2));
							xNormSuiv = ((float)(x2Suiv-x2))/normSuiv;
							yNormSuiv = ((float)(y2Suiv-y2))/normSuiv;
						}
						

						x1 = x1 - (int)(decalageRueDoubleSens*(yNorm/2+yNormPrec/2));
						x2 = x2 - (int)(decalageRueDoubleSens*(yNorm/2+yNormSuiv/2));
					
						y1 = y1 + (int)(decalageRueDoubleSens*(xNorm/2+xNormPrec/2));
						y2 = y2 + (int)(decalageRueDoubleSens*(xNorm/2+xNormSuiv/2));
					}
					g2d.drawLine(x1, y1, x2, y2);
				}
			}
		}
	
		g2d.setStroke(new BasicStroke(1));
		
		
		// Ecrire les numéros de la tournée
		g2d.setColor(CharteGraphique.GRAPH_TEXT_COLOR);
		g2d.setFont(CharteGraphique.TEXT_BIG_FAT_FONT);
		if(plan.getTournee()!=null){
			for(int i=0; i<plan.getTournee().getLivraisons().size(); i++) {
				Livraison livraison = plan.getTournee().getLivraisons().get(i);
				g2d.drawString(Integer.toString(i+1), 
						positionX(livraison.getX())-8, 
						positionY(livraison.getY())+20);
			}
		}
		
		// Ecrire les numéros de la demande de livraison
		g2d.setColor(CharteGraphique.GRAPH_TEXT_COLOR);
		g2d.setFont(CharteGraphique.TEXT_BIG_FAT_FONT);
		if(plan.getTournee()==null){
			for(int i=0; i<plan.getDemandeLivraison().getLivraisons().size(); i++) {
				Livraison livraison = plan.getDemandeLivraison().getLivraisons().get(i);
				g2d.drawString(Integer.toString(i+1), 
						positionX(livraison.getX())-8, 
						positionY(livraison.getY())+20);
			}
		}
		
		
		//Repositionner les boutons
		changerDemandeLivraisonButton.setBounds((int)(getWidth()-changerDemandeLivraisonButton.getPreferredSize().getWidth()), (int)changerDemandeLivraisonButton.getPreferredSize().getHeight(), (int)changerDemandeLivraisonButton.getPreferredSize().getWidth(), (int)changerDemandeLivraisonButton.getPreferredSize().getHeight());
		changerPlanButton.setBounds((int)(getWidth()-changerDemandeLivraisonButton.getPreferredSize().getWidth()), 0, (int)changerDemandeLivraisonButton.getPreferredSize().getWidth(), (int)changerDemandeLivraisonButton.getPreferredSize().getHeight());         
	  }

	private int positionX(int x) {
		return (int) ((x-minX)/zoom + this.getWidth()/ 2 -(maxX-minX)/(2*zoom) + coordonneeX);
	}
	
	private int positionY(int y) {
		return (int)((y-minY)/zoom + this.getHeight()/2 - (maxY-minY)/(2*zoom) + coordonneeY);
	}

	public void zoom(){
		
		// On calcule les coordonnées de la souris sur le plan
		// TODO : fonction
		posSourisX = this.getMousePosition().getX();
		double sourisPlanX = (posSourisX - coordonneeX - this.getWidth()/ 2 + (maxX-minX)/(2*zoom))*zoom + minX;
		posSourisY = this.getMousePosition().getY();
		double sourisPlanY = (posSourisY - coordonneeY - this.getHeight()/ 2 + (maxY-minY)/(2*zoom))*zoom + minY;
		
		double zoomPrec = this.zoom;
		
		this.zoom-=5;
		if(zoom<=0) {
			zoom = 1;
		}		
		
		// On calcule la différence entre les coordonnées de la souris précédant le zoom et suivant le zoom pour pouvoir recentrer le point pointé précédemment par la souris sur la souris
		double decZoomX = (sourisPlanX-minX)/zoom-(maxX-minX)/(2*zoom)-((sourisPlanX-minX)/zoomPrec-(maxX-minX)/(2*zoomPrec));
		coordonneeX = (float)(coordonneeX - decZoomX);
		double decZoomY = (sourisPlanY-minY)/zoom-(maxY-minY)/(2*zoom)-((sourisPlanY-minY)/zoomPrec-(maxY-minY)/(2*zoomPrec));
		coordonneeY = (float)(coordonneeY - decZoomY);
		
		actualiserIcones();
		repaint();
	}
	public void move(int x, int y){
		this.coordonneeX += x;
		this.coordonneeY += y;
		actualiserIcones();
		repaint();
	}
	
	public void dezoom(){
		
		posSourisX = this.getMousePosition().getX();
		double sourisPlanX = (posSourisX - coordonneeX - this.getWidth()/ 2 + (maxX-minX)/(2*zoom))*zoom + minX;
		posSourisY = this.getMousePosition().getY();
		double sourisPlanY = (posSourisY - coordonneeY - this.getHeight()/ 2 + (maxY-minY)/(2*zoom))*zoom + minY;
		double zoomPrec = this.zoom;
		
		this.zoom+=5;
		
		double decZoomX = (sourisPlanX-minX)/zoom-(maxX-minX)/(2*zoom)-((sourisPlanX-minX)/zoomPrec-(maxX-minX)/(2*zoomPrec));
		coordonneeX = (float)(coordonneeX - decZoomX);
		double decZoomY = (sourisPlanY-minY)/zoom-(maxY-minY)/(2*zoom)-((sourisPlanY-minY)/zoomPrec-(maxY-minY)/(2*zoomPrec));
		coordonneeY = (float)(coordonneeY - decZoomY);
		
		actualiserIcones();
		repaint();
	}

	public JButton getButtonPlan(){
		return changerPlanButton;
	}
	public JButton getButtonDemandeLivraison(){
		return changerDemandeLivraisonButton;
	}
	
	public void afficherIcones(DemandeLivraison demande){
		nettoyerIcones();

		//Dessiner les icones de points de livraisons
		iconesLivraison = new ArrayList<JLabel>();
		for (Livraison livraison : demande.getLivraisons()) {
			afficherIconeLivraison(livraison);
		}
		//Dessiner l'icone de l'entrepot
		if (demande.getEntrepot()!=null) {
			this.add(iconeEntrepot);
			iconeEntrepot.setBounds(positionX(demande.getEntrepot().getX())-largeurBalise/2, positionY(plan.getDemandeLivraison().getEntrepot().getY())-hauteurBalise, largeurBalise, hauteurBalise);
		}
	}
	
	public void actualiserIcones(){
		//Dessiner les icones de points de livraisons
		int i = 0;
		if(!plan.getDemandeLivraison().getLivraisons().isEmpty())
			for (Livraison livraison : plan.getDemandeLivraison().getLivraisons()) {
				iconesLivraison.get(i).setBounds(positionX(livraison.getX())-largeurBalise/2, positionY(livraison.getY())-hauteurBalise, largeurBalise, hauteurBalise);
				i++;
			}
		//Dessiner l'icone de l'entrepot
		if (plan.getDemandeLivraison().getEntrepot()!=null) {
			iconeEntrepot.setBounds(positionX(plan.getDemandeLivraison().getEntrepot().getX())-largeurBalise/2, positionY(plan.getDemandeLivraison().getEntrepot().getY())-hauteurBalise, largeurBalise, hauteurBalise);
		}
		if(iconeNouvelleLivraison.getParent() == this) {
			iconeNouvelleLivraison.setBounds(positionX(nouvelleIntersection.getX())-largeurBalise/2, positionY(nouvelleIntersection.getY())-hauteurBalise, largeurBalise, hauteurBalise);
		}
	}
	
	public void nettoyerIcones() {
		//Supprimer les anciennes icones
		for (int i = 0; i<iconesLivraison.size(); i++) {
			this.remove(iconesLivraison.get(i));
		}
		this.remove(iconeEntrepot);
		this.remove(iconeNouvelleLivraison);
	}
	
	public void survol(int index){
		if (index == -1){
			iconeEntrepot.setIcon(imageIconES);
		}else{
			iconesLivraison.get(index).setIcon(imageIconLS);
		}
	}
	
	public void antiSurvol(int index){
		if (index == -1){
			iconeEntrepot.setIcon(imageIconE);
		}else{
			iconesLivraison.get(index).setIcon(imageIconL);
		}
	}
	
	public ArrayList<JLabel> getIconesLivraison(){
		return iconesLivraison;
	}
	
	public JLabel getIconeEntrepot(){
		return iconeEntrepot;
	}
	
	public void commencerChoixIntersection() {
		addMouseListener(ecouteurSourisChoixIntersec);
		addMouseMotionListener(ecouteurSourisChoixIntersec);
		this.add(iconeLivraisonSouris);
	}
	
	public void actualiserIconeSouris(int x, int y) {
		// Activer le listener
		iconeLivraisonSouris.setBounds(x-largeurBalise/2, y-hauteurBalise, largeurBalise, hauteurBalise);
	}
	
	public void terminerChoixIntersection() {
		// Enlever le listener
		removeMouseListener(ecouteurSourisChoixIntersec);
		removeMouseMotionListener(ecouteurSourisChoixIntersec);
	}
	
	public int positionXPlan(int xJPanel) {
		return (int)((xJPanel - coordonneeX - this.getWidth()/ 2 + (maxX-minX)/(2*zoom))*zoom + minX);
	}
	
	public int positionYPlan(int yJPanel) {
		return (int)((yJPanel - coordonneeY - this.getHeight()/ 2 + (maxY-minY)/(2*zoom))*zoom + minY);
	}
	
	public void afficherIcone(Intersection intersection) {
		nouvelleIntersection = intersection;
		iconeLivraisonSouris.setBounds(100, 100, largeurBalise, hauteurBalise);
		this.remove(iconeLivraisonSouris);
		//JLabel liv = new JLabel(imageIconL);
		if (iconeNouvelleLivraison.getParent() != this) {
			this.add(iconeNouvelleLivraison);
		}
		iconeNouvelleLivraison.setBounds(positionX(nouvelleIntersection.getX())-largeurBalise/2, positionY(nouvelleIntersection.getY())-hauteurBalise, largeurBalise, hauteurBalise);
	}
	
	public void annulerCreation() {
		terminerChoixIntersection();
		remove(iconeNouvelleLivraison);
		remove(iconeLivraisonSouris);
		nouvelleIntersection = null;
	}

	public void activerBouton(boolean activer) {
		changerDemandeLivraisonButton.setEnabled(activer);
		changerPlanButton.setEnabled(activer);
	}


	public void activerAnnulationBouton(boolean activer) {
		if (activer) {
			add(undoButton);
			add(redoButton);
		}
		else {
			remove(undoButton);
			remove(redoButton);
		}
	}
	
	private void initAnnulationBouton() {
		undoButton = new PersoButton("", 2);
		undoButton.setMargin(new Insets(10,20,10,20));
		undoButton.setBounds(0, 0, (int)undoButton.getPreferredSize().getWidth(), (int)undoButton.getPreferredSize().getHeight());
		undoButton.addActionListener(ecouteurBoutons);
		undoButton.setActionCommand("undo_action");
		
		redoButton = new PersoButton("", 2);
		redoButton.setMargin(new Insets(10,20,10,20));
		redoButton.setBounds((int)undoButton.getPreferredSize().getWidth(), 0, (int)redoButton.getPreferredSize().getWidth(), (int)redoButton.getPreferredSize().getHeight());
		redoButton.addActionListener(ecouteurBoutons);
		redoButton.setActionCommand("redo_action");
		
		try {
			BufferedImage undoImage = ImageIO.read(new File(CharteGraphique.ICONE_RETOUR_ARRIERE));
			BufferedImage redoImage = ImageIO.read(new File(CharteGraphique.ICONE_RETOUR_AVANT));
			ImageIcon imageIconUndo = new ImageIcon(undoImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
			ImageIcon imageIconRedo = new ImageIcon(redoImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
			redoButton.setIcon(imageIconRedo);
			undoButton.setIcon(imageIconUndo);
		} catch (IOException e) {
			undoButton.setText(Textes.BUTTON_UNDO);
			redoButton.setText(Textes.BUTTON_REDO);
		}
	}
	
	public JLabel afficherIconeLivraison(Livraison livraison) {
		JLabel liv = new JLabel(imageIconL);
		this.add(liv);
		liv.setBounds(positionX(livraison.getX())-largeurBalise/2, positionY(livraison.getY())-hauteurBalise, largeurBalise, hauteurBalise);
		iconesLivraison.add(liv);
		return liv;
	}
	
	public JLabel getIconeNouvelleLivraison() {
		return iconeNouvelleLivraison;
	}
	
	public JLabel getIconeLivraisonSouris() {
		return iconeLivraisonSouris;
	}
}