package xml;

/**
 * 
 * @author 4104
 *
 */
public class AnnulationXML extends ExceptionXML{
	private static final long serialVersionUID = -8115569127704610375L;

	/**
	 * Exception personnalisee pour la deserialisation de xml lancé lors d'une annulation
	 * @param message
	 */
	public AnnulationXML(String message) {
		super(message);
	}
}
