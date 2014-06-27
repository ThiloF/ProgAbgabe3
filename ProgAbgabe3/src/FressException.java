/**
 * FressException, welche Exception erweitert und als Oberklasse für die 4 konkreten Exceptions dient
 * @author fkoen001
 */
public abstract class FressException extends Exception {

	// Generiertes Dingens, weil Eclipse sonst meckert
	private static final long serialVersionUID = -3095877324155540494L;

	public FressException(String messege) {
		super(messege);
	}

}
