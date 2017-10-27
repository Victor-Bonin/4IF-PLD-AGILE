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
		Controleur ctrl = new Controleur(plan);
	}

}
