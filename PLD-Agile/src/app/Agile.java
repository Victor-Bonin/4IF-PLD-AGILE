package app;

import modele.ExceptionPlanCo;
import modele.Plan;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controleur.Controleur;

public class Agile {
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
		} catch (ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionPlanCo e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		plan.calculTournee();
	}

}
