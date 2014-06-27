/**
 * Diese Exception wird geworfen, wenn ein Fisch versucht, Müll zu essen
 * @author fkoen001
 */
public class MuellException extends FressException {

	// Generiertes Dingens, weil Eclipse sonst meckert
	private static final long serialVersionUID = -7234440515948018943L;

	public MuellException(String messege) {
		super(messege);
	}

}
