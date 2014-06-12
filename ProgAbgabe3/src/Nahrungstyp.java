
public enum Nahrungstyp { 

	PFLANZE, FISCH, FLEISCH, MUELL;
	
	public static Nahrungstyp vonName(String s) {
		if (s.equals("PFLANZE")) return PFLANZE;
		if (s.equals("FISCH")) return FISCH;
		if (s.equals("FLEISCH")) return FLEISCH;
		if (s.equals("MUELL")) return MUELL;
		return MUELL;
	}
	
}
