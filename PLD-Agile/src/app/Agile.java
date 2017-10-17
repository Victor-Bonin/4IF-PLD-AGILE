package app;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modele.Plan;
import vue.Fenetre;
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
		try {
			DeserialiseurXML.charger(plan);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ExceptionXML e) {
			e.printStackTrace();
		}
		Fenetre test = new Fenetre(plan);
	}

}
