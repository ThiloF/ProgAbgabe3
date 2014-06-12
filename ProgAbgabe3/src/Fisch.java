
public class Fisch implements Leckerbissen {
	
	
	private String name;
	private Nahrungstyp typ;
	private Esstyp geschmack;
	private int aktGewicht;
	private int maxAppetit;

	
	public Fisch(String name, Nahrungstyp typ , Esstyp geschmack, int aktGewicht, int maxAppetit) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.typ = typ;
		this.geschmack  = geschmack;
		this.aktGewicht = aktGewicht;
		this.maxAppetit = maxAppetit;
	}
	
	
	@Override
	public int getGramm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean gefressen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean istLebendig() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Nahrungstyp getNahrungstyp() {
		// TODO Auto-generated method stub
		return null;
	}

}
