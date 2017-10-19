package app;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.DemandeLivraison;
import modele.Plan;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

import vue.Fenetre;

import controleur.Controleur;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

public class Agile {
	private static final int echelleInitiale = 10;
	private static final int hauteurPlan = 40;
	private static final int largeurPlan = 40;
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Plan plan = new Plan();
		//Controleur ctrl = new Controleur(plan);
		try {
			DeserialiseurXML.charger(plan);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DeserialiseurXML.chargerDemandeLivraison(plan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		plan.calculTournee();
	}

}
