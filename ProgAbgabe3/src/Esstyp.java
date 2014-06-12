
public enum Esstyp {

	VEGETARIER, VEGANER, FLEXITARIER, FISCHFRESSER, FLEISCHFRESSER;
	
	public static Esstyp vonName(String s) {
		if (s.equals("VEGETARIER")) return VEGETARIER;
		if (s.equals("VEGANER")) return VEGANER;
		if (s.equals("FLEXITARIER")) return FLEXITARIER;
		if (s.equals("FISCHFRESSER")) return FISCHFRESSER;
		if (s.equals("FLEISCHFRESSER")) return FLEISCHFRESSER;
		return VEGETARIER;
	}
	
}
