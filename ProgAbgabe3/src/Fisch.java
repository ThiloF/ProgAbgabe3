public class Fisch implements Leckerbissen {

	private String name;
	private Nahrungstyp typ;
	private Esstyp geschmack;
	private int aktGewicht;
	private int maxAppetit;
	private boolean lebendig;


	public Fisch(String name, Nahrungstyp typ, Esstyp geschmack, int aktGewicht, int maxAppetit) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.typ = typ;
		this.geschmack = geschmack;
		this.aktGewicht = aktGewicht;
		this.maxAppetit = maxAppetit;
	}

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

	@Override
	public int getGramm() {
		// TODO Auto-generated method stub
		return aktGewicht;
	}

	@Override
	public boolean gefressen() {
		if (lebendig) {
			lebendig = false;
			aktGewicht = 0;
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
	
	public String getName(){
		return name;
	}

}
