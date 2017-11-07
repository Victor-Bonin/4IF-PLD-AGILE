package modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

	public List<Chemin> getItineraire(){
		return itineraire.getChemins();
	}

	public void exportFeuilleDeRoute() throws IOException {
		this.getEntrepot().getHeureDepart();
		String nomRue = "";
		float longueurTroncon;
		String longueurRue = "";
		String feuilleDeRoute = "";
		int nbTronconsConseq;
		// Liste des livraisons
		List<Livraison> livraisons = this.getLivraisons();
		// Liste des chemins empruntés
		List<Chemin> chemins = this.getItineraire();
		for (int i = 0; i < chemins.size(); i++) {
			System.out.println(i);

			// Liste des troncons empruntés sur ce chemin
			List<Troncon> troncons = chemins.get(i).getTroncons();
			for (int j = 0; j < troncons.size(); j = j + 1 + nbTronconsConseq) {

				nomRue = troncons.get(j).GetNomRue();
				longueurTroncon = troncons.get(j).getLongueur()/10;
				long fin = troncons.get(j).getFin().getId(); // TODO recupérer les rues qui composent l'intersection
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
				feuilleDeRoute += "Prenez la rue "+ nomRue +" pendant "+ longueurRue +"m \r\n";
			}
			if (i < livraisons.size()){
				feuilleDeRoute += "Livraison n° "+ (i+1) +" : Arrivée à "+ livraisons.get(i).getHeurePassage().HOUR_OF_DAY +"h"+ livraisons.get(i).getHeurePassage().MINUTE +", Durée : "+ livraisons.get(i).getDuree() +" \r\n";
			}
		}

		String pathname = "./Feuille de Route.html";
		File file = new File(pathname);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		String contenu = "'<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'"
				+ "'http://www.w3.org/TR/html4/loose.dtd'>"
				+ "<html>"
				+ "<head>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8>"
				+ "<title>$title</title>"
				+ "</head>"
				+ "<body>" + feuilleDeRoute + "</body>"
				+ "</html>";
		bw.write(contenu);
		bw.close();

		System.out.println(feuilleDeRoute);


	}
}
