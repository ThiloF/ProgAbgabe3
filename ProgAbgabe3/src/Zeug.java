// Pflanze, Fleich und Müll
public class Zeug implements Leckerbissen {

	private String name;
	private Nahrungstyp typ;
	private int g;
	private boolean lebendig;

	public Zeug(String name, Nahrungstyp typ, int g) {
		this.name = name;
		this.typ = typ;
		this.g = g;

		this.lebendig = true;
		if (this.typ == Nahrungstyp.MUELL) {
			lebendig = false;
		}
	}

	@Override
	public int getGramm() {
		return g;
	}

	@Override
	public boolean gefressen() {
		if (lebendig) {
			lebendig = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean istLebendig() {
		return lebendig;
	}

	@Override
	public Nahrungstyp getNahrungstyp() {
		return typ;
	}

	@Override
	public String getName() {
		return name;
	}

}
