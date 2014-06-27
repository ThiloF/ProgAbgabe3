/**
 * 
 * @author Thilo Falkstein 877699, Felix König 577751, Lisa Katharina Schröder
 *
 * Dies Klasse repräsentiert einen Fisch.
 * Ein Fisch kann fressen und gefragt werden ob er noch am leben ist, was er frisst und wie viel er wiegt
 *
 */

public class Fisch implements Leckerbissen {

	private String name;
	private Nahrungstyp typ;
	private Esstyp geschmack;
	private int aktGewicht;
	private int maxAppetit;
	private boolean lebendig;

 
	/**
	 * Erzeugt einen Fisch mit folgenden Parametern
	 * @param name
	 * @param typ
	 * @param geschmack
	 * @param aktGewicht
	 * @param maxAppetit
	 */
	
	public Fisch(String name, Nahrungstyp typ, Esstyp geschmack, int aktGewicht, int maxAppetit) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.typ = typ;
		this.geschmack = geschmack;
		this.aktGewicht = aktGewicht;
		this.maxAppetit = maxAppetit;
	}
	
	/**
	 * Prüft ob er die Beute essen kann und erhöht wenn er es kann und noch nicht satt ist das Gewicht 
	 * 
	 * @param beute
	 * @throws EatYourFriendException
	 * @throws MuellException
	 * @throws SchmecktNichtException
	 * @throws SattException
	 */

	public void fressen(Leckerbissen beute) throws EatYourFriendException, MuellException, SchmecktNichtException, SattException {
		
		if(aktGewicht >= maxAppetit)
			throw new SattException("Die Fette Sau ist satt");

		if (name == beute.getName())
			throw new EatYourFriendException(typ + " kann keinen Artgenossen vom gleichen Nahrungstp fressen");

		if (beute.getNahrungstyp() == Nahrungstyp.MUELL)
			throw new MuellException("Muell kann nicht gefressen werden");

		if ((geschmack == Esstyp.VEGANER || geschmack == Esstyp.VEGETARIER) && (beute.getNahrungstyp() != Nahrungstyp.PFLANZE))
			throw new SchmecktNichtException("Beute muss Vegetarisch sein");

		if ((geschmack == Esstyp.FISCHFRESSER) && beute.getNahrungstyp() != Nahrungstyp.FISCH)
			throw new SchmecktNichtException("Beute muss ein Fisch sein");

		if ((geschmack == Esstyp.FLEISCHFRESSER) && (beute.getNahrungstyp() == Nahrungstyp.FISCH || beute.getNahrungstyp() == Nahrungstyp.FLEISCH))
			throw new SchmecktNichtException("Beute muss ein Fisch oder aus Fleisch sein");
		
		aktGewicht += beute.getGramm();
		beute.gefressen();

	}

	/**
	 * liefert das Gewicht
	 */
	
	@Override
	public int getGramm() {
		// TODO Auto-generated method stub
		return aktGewicht;
	}
	
	/**
	 * Sorgt dafür das ein Fisch gefressen werden kann
	 * Wenn er gefressen wird, stirbt der Fisch.
	 */

	@Override
	public boolean gefressen() {
		if (lebendig) {
			lebendig = false;
			aktGewicht = 0;
			return true;
		}
		return false;
	}

	/**
	 * Gibt wahr zurück wenn er am leben ist und falsch wenn nicht
	 */
	
	@Override
	public boolean istLebendig() {
		return lebendig;
	}

	/**
	 * liefert den Nahrungstyp
	 */
	
	@Override
	public Nahrungstyp getNahrungstyp() {
		return typ;
	}
	
	/**
	 * liefert den Namen des Fisches
	 */
	
	public String getName(){
		return name;
	}

}
