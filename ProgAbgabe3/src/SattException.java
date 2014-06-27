/**
 * Diese Exception wird geworfen, wenn ein satter Fisch versucht, trotzdem zu essen
 * @author fkoen001
 */
public class SattException extends FressException {

	// Generiertes Dingens, weil Eclipse sonst meckert
	private static final long serialVersionUID = 5061818818605211021L;

	public SattException(String messege) {
		super(messege);
	}

}
