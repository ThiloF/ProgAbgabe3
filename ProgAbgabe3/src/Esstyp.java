/**
 * 
 * @author Thilo Falkstein 877699, Felix König 577751, Lisa Katharina Schröder
 *
 * Dies Klasse enthält die Esstypen
 * 
 *
 */
public enum Esstyp {

	VEGETARIER, VEGANER, FLEXITARIER, FISCHFRESSER, FLEISCHFRESSER;
	
	/**
	 * Gibt einen Enum anhand es Parameter aus; 
	 * @param s
	 * @return
	 */
	
	public static Esstyp vonName(String s) {
		if (s.equals("VEGETARIER")) return VEGETARIER;
		if (s.equals("VEGANER")) return VEGANER;
		if (s.equals("FLEXITARIER")) return FLEXITARIER;
		if (s.equals("FISCHFRESSER")) return FISCHFRESSER;
		if (s.equals("FLEISCHFRESSER")) return FLEISCHFRESSER;
		return VEGETARIER;
	}
	
}
