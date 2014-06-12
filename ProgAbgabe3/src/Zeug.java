
// Pflanze, Fleich und Müll
public class Zeug implements Leckerbissen {

	String name;
	Nahrungstyp typ;
	int g;
	
	public Zeug (String name, Nahrungstyp typ, int g){
		this.name = name;
		this.typ = typ;
		this.g= g;
		
		
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
