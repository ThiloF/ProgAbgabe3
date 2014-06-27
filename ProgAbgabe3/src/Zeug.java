
/**
 * 
 * @author Thilo Falkstein 877699, Felix König 577751, Lisa Katharina Schröder
 *
 * Dies Klasse repräsentiert Zeug also alles außer Fische.
 * 
 *
 */


public class Zeug implements Leckerbissen {

	private String name;
	private Nahrungstyp typ;
	private int g;
	private boolean lebendig;

	/**
	 * Die Konstruktor erzeugt Zeug ;)
	 * @param name
	 * @param typ
	 * @param g
	 */
	
	public Zeug(String name, Nahrungstyp typ, int g) {
		this.name = name;
		this.typ = typ;
		this.g = g;

		this.lebendig = true;
		if (this.typ == Nahrungstyp.MUELL) {
			lebendig = false;
		}
	}
	
	/**
	 * Liefert das Gewicht von Zeug
	 */

	@Override
	public int getGramm() {
		return g;
	}
	
	/**
	 * Sorgt dafür das Zeug gefressen werden kann
	 * Wenn er gefressen wird, stirbt das Zeug. 
	 */

	@Override
	public boolean gefressen() {
		if (lebendig) {
			lebendig = false;
			this.g = 0;
			return true;
		}
		return false;
	}

	/**
	 * Zeigt ob Zeug noch am Leben ist
	 */
	
	@Override
	public boolean istLebendig() {
		return lebendig;
	}

	/**
	 * Liefert den Nahrungstyp
	 */
	
	@Override
	public Nahrungstyp getNahrungstyp() {
		return typ;
	}
	/**
	 * Liefer den Namen
	 */

	@Override
	public String getName() {
		return name;
	}

}
