package app;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.Plan;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

import vue.Fenetre;

public class Agile {
	private static final int echelleInitiale = 10;
	private static final int hauteurPlan = 40;
	private static final int largeurPlan = 40;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Plan plan = new Plan(largeurPlan, hauteurPlan);
		new Controleur(plan, echelleInitiale); **/
		System.out.println("Si ma Tante en avait 2, on l'appellerait mon Oncle");
		
		Fenetre test = new Fenetre();
	}

}
