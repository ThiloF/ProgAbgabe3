/**
 * Diese Exception wird geworfen, wenn ein Fisch etwas versucht zu Essen, was nicht seiner Überzeugung entspricht
 * @author fkoen001
 */
public class SchmecktNichtException extends FressException {

	// Generiertes Dingens, weil Eclipse sonst meckert
	private static final long serialVersionUID = 5105529412139417234L;

	public SchmecktNichtException(String messege) {
		super(messege);
	}

}
