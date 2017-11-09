package modele;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFileChooser;

/**
 * Ensemble ordonne de livraisons avec un point de depart et d’arrivee ainsi qu’un itineraire
 * @author 4104
 */
public class Tournee extends DemandeLivraison{
	private Itineraire itineraire;

	public Tournee(Entrepot entrepot, List<Livraison> meilleureSolution, Itineraire iti){
		this.itineraire = iti;
		super.setEntrepot(entrepot);
		super.setLivraisons(meilleureSolution);
	}

	public Itineraire getItineraire(){
		return itineraire;
	}

	public boolean equals(Object obj) {
		return this.getItineraire().equals(((Tournee)obj).getItineraire());
	}

	// TODO : recupérer les rues qui composent l'intersection + plages horaires + probleme évident avec les heures + améliorer popup + notification
	public void exportFeuilleDeRoute() throws IOException, ExceptionPlanCo {
		this.getEntrepot().getHeureDepart();
		String nomRue = "";
		float longueurTroncon;
		String longueurRue = "";
		String feuilleDeRoute = "";
		int nbTronconsConseq;
		// Liste des livraisons
		List<Livraison> livraisons = this.getLivraisons();
		// Liste des chemins empruntés
		List<Chemin> chemins = this.getItineraire().getChemins();
		for (int i = 0; i < chemins.size(); i++) {
			// Liste des troncons empruntés sur ce chemin
			List<Troncon> troncons = chemins.get(i).getTroncons();
			for (int j = 0; j < troncons.size(); j = j + 1 + nbTronconsConseq) {

				nomRue = troncons.get(j).GetNomRue();
				longueurTroncon = troncons.get(j).getLongueur()/10;
				long fin = troncons.get(j).getFin().getId();
				nbTronconsConseq = 0;
				if (j < troncons.size()-1) {
					while (nomRue.equals( troncons.get(j + nbTronconsConseq + 1).GetNomRue())){
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
				feuilleDeRoute += "<br>Prenez la rue "+ nomRue +" pendant "+ longueurRue +"m \r\n </br>";
			}
			if (i < livraisons.size()){
				if (livraisons.get(i) instanceof LivraisonPlageHoraire) {
					feuilleDeRoute += "<br> <b> Livraison n° "+ (i+1) +" : Arriv&eacutee &agrave "+ livraisons.get(i).getHeurePassage().get(Calendar.HOUR_OF_DAY) +"h "+ livraisons.get(i).getHeurePassage().get(Calendar.MINUTE) +", Dur&eacutee : "+ (livraisons.get(i).getDuree()/60) +" min \r\n </b></br>";
				}
				else {
					feuilleDeRoute += "<br> <b> Livraison n° "+ (i+1) +" : Arriv&eacutee &agrave "+ livraisons.get(i).getHeurePassage().get(Calendar.HOUR_OF_DAY) +"h "+ livraisons.get(i).getHeurePassage().get(Calendar.MINUTE) +", Dur&eacutee : "+ (livraisons.get(i).getDuree()/60) +" min \r\n </b></br>";
				}
			}
		}

		creerFeuilleDeRoute (feuilleDeRoute);
	}

	public void creerFeuilleDeRoute(String feuilleDeRoute) throws ExceptionPlanCo, IOException {

		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
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
}
