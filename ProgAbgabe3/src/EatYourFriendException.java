/**
 * Exception, welche geworfen wird, wenn ein Fisch einen Artgenossen fressen möchte.
 * @author fkoen001
 */
public class EatYourFriendException extends Exception {

	// Generiertes Dingens, weil Eclipse sonst meckert
	private static final long serialVersionUID = 2567124746429546795L;

	public EatYourFriendException(String message) {
		super(message);
	}

}
