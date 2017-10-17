package xml;

/**
 * Classe gerant les exceptions lors de l'ouverture et la deserialisation de xml
 * @author 4104
 */
public class ExceptionXML extends Exception {

	/**
	 * Exception personnalisee pour la deserialisation de xml
	 * @param message
	 */
	public ExceptionXML(String message) {
		super(message);
	}

}
