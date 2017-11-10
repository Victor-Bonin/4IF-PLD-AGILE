package modele;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.lang.Object;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ensemble ordonne de livraisons avec un point de depart et d’arrivee ainsi qu’un itineraire
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
public class Tournee extends DemandeLivraison{
	private Itineraire itineraire;
	private String feuilleDeRoute;

	public Tournee(Entrepot entrepot, List<Livraison> meilleureSolution, Itineraire iti){
		this.itineraire = iti;
		super.setEntrepot(entrepot);
		super.setLivraisons(meilleureSolution);
		this.feuilleDeRoute = "";
	}

	public Itineraire getItineraire(){
		return itineraire;
	}

	public boolean equals(Object obj) {
		return this.getItineraire().equals(((Tournee)obj).getItineraire());
	}

	
	public void exportFeuilleDeRoute() throws IOException, ExceptionPlanCo {
		Calendar heureDepart = this.getEntrepot().getHeureDepart();
		Calendar heureArrivee = this.getEntrepot().getHeureArrivee();
		String nomRue = "";
		float longueurTroncon;
		String longueurRue = "";
		feuilleDeRoute = "<h2> Bonjour ! <br/>Début de la tournée à "+ heureDepart.get(Calendar.HOUR_OF_DAY)+"h"+ heureDepart.get(Calendar.MINUTE) +"min<br/>Fin de livraison prévue à "+ heureArrivee.get(Calendar.HOUR_OF_DAY)+"h"+ heureArrivee.get(Calendar.MINUTE) +"min</h2>";
		int nbTronconsConseq;
		// Liste des livraisons
		List<Livraison> livraisons = this.getLivraisons();
		// Liste des chemins empruntés
		List<Chemin> chemins = this.getItineraire().getChemins();
		for (int i = 0; i < chemins.size(); i++) {
			// Liste des troncons empruntés sur ce chemin
			List<Troncon> troncons = chemins.get(i).getTroncons();
			for (int j = 0; j < troncons.size(); j = j + 1 + nbTronconsConseq) {

				nomRue = troncons.get(j).getNomRue();
				longueurTroncon = troncons.get(j).getLongueur();
				long fin = troncons.get(j).getFin().getId();
				nbTronconsConseq = 0;
				if (j < troncons.size()-1) {
					while (nomRue.equals( troncons.get(j + nbTronconsConseq + 1).getNomRue())){
						longueurTroncon += troncons.get(j + nbTronconsConseq + 1).getLongueur();
						nbTronconsConseq ++;
						if (j + nbTronconsConseq == troncons.size() -1) {
							break;
						}
					}
				}

				longueurRue = String.valueOf(Math.round(longueurTroncon));
				if (nomRue.equals("")) {
					nomRue = "Rue 'Inconnue'";
				}
				feuilleDeRoute += "Prenez la rue "+ nomRue +" pendant "+ longueurRue +"m<br/>";
			}
			if (i < livraisons.size()){
				if (livraisons.get(i) instanceof LivraisonPlageHoraire) {
					feuilleDeRoute += "<br/><h3> Livraison n° "+ (i+1) 
							+" :  Arrivée à "+ (((LivraisonPlageHoraire) livraisons.get(i)).getArriveeEstimee().get(Calendar.HOUR_OF_DAY)) +"h "+ ((LivraisonPlageHoraire) livraisons.get(i)).getArriveeEstimee().get(Calendar.MINUTE)
							+"min</h3><b> Vous pouvez livrer à partir de : "+ (((LivraisonPlageHoraire) livraisons.get(i)).getDebut().get(Calendar.HOUR_OF_DAY)) +"h "+ ((LivraisonPlageHoraire) livraisons.get(i)).getDebut().get(Calendar.MINUTE)
							+"min<br/>Attente estimée : "+ (Math.round(((LivraisonPlageHoraire) livraisons.get(i)).getAttente()/60))
							+"min<br/>Retard maximum : "+ (Math.round(((LivraisonPlageHoraire) livraisons.get(i)).getRetardPossible()/60))
							+"min<br/> Durée : "+ (livraisons.get(i).getDuree()/60) +" min <br/></b>";
				} 
				else {
					feuilleDeRoute += "<br/><h3> Livraison n° "+ (i+1) +" : Arrivée à "+ livraisons.get(i).getHeurePassage().get(Calendar.HOUR_OF_DAY) +"h "+ livraisons.get(i).getHeurePassage().get(Calendar.MINUTE) +" min, Durée : "+ (livraisons.get(i).getDuree()/60) +" min  </h3>";
				}
			}
		}
		feuilleDeRoute += "<br/><br/><h4> Vous êtes arrivé !</h4>";
		creerFeuilleDeRoute (feuilleDeRoute);
	}

	public void creerFeuilleDeRoute(String feuilleDeRoute) throws ExceptionPlanCo, IOException {

		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier HTML", "html");
		fileChooser.setDialogTitle("Choisir où sauvegarder");
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnVal = fileChooser.showSaveDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (!(file.getName().endsWith(".html"))) {
				String fileName = (file.getName());
				file = new java.io.File(fileName +".html"); 
			}
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(file));
			String contenu = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Feuille de Route</title></head><body><p>"+ feuilleDeRoute +"</p></body></html>";
			bw.write(contenu);
			bw.close();
			Desktop.getDesktop().browse(file.toURI());
		}
		else 
			if (returnVal == JFileChooser.CANCEL_OPTION)
				throw new ExceptionPlanCo(ExceptionPlanCo.ANNULATION_SAUVEGARDE_FEUILLE_DE_ROUTE);
			else
				throw new ExceptionPlanCo(ExceptionPlanCo.PROBLEME_SAUVEGARDE_FEUILLE_DE_ROUTE);	
	}

	public String getFeuilleDeRoute() {
		return feuilleDeRoute;
	}
}
