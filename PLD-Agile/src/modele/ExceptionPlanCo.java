/**
 * @author 4104
 */
package modele;

public class ExceptionPlanCo extends Exception {
	private static final long serialVersionUID = 6735829319293468263L;

	/**
	 * Exception personnalisee pour le modele de PlanCo
	 * @param message
	 */
	public ExceptionPlanCo(String message) {
		super(message);
	}
}
