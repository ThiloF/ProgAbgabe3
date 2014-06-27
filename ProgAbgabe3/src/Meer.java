import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Meer {

	private ArrayList<Leckerbissen> inhalt = new ArrayList<Leckerbissen>();
	private ArrayList<Fisch> fische = new ArrayList<Fisch>();
	private ArrayList<String[]> fressakte = new ArrayList<String[]>();

	public Meer(File acteurs, File scene) {
		ladeFische(acteurs);
		ladeGeschichte(scene);
	}

	private Leckerbissen find(String name) {
		for (Leckerbissen l : inhalt) {
			if (l.getName().equals(name))
				return l;
		}
		return null;
	}

	public void displayStory() {

		for (String[] akt : fressakte) {

			Leckerbissen l1 = find(akt[0]);
			Leckerbissen l2 = find(akt[1]);

			if (l1 == null || l2 == null) {
				System.err.println("Fressakt konnte nicht ausgeführt werden: " + akt[0] + " frisst " + akt[1]);
			}

			if (!(l1 instanceof Fisch)) {
				System.err.println("Fressakteur ist kein Fisch: " + l1.getName());
				continue;
			}

			Fisch fisch = (Fisch) l1;
			System.out.println(l1.getName() + " versucht " + l2.getName() + " zu fressen...");

			try {
				fisch.fressen(l2);
				System.out.println(fisch.getName() + " hat " + l2.getName() + " gefressen!");
			} catch (MuellException e) {
				e.printStackTrace();
				System.out.println(l1.getName() + " kann keinen Müll verdauen.");
			} catch (SchmecktNichtException e) {
				e.printStackTrace();
				System.out.println(l1.getName() + "'s Überzeugung hat den Fressakt verhindert.");
			} catch (SattException e) {
				e.printStackTrace();
				System.out.println(l1.getName() + " möchte nichts mehr essen.");
			} catch (EatYourFriendException e) {
				e.printStackTrace();
				System.out.println(l1.getName() + " frisst keine Freunde.");
			}
		}
	}

	public void addFisch(Fisch fisch) {
		fische.add(fisch);
	}

	public void addLeckerbissen(Leckerbissen leckerbissen) {
		inhalt.add(leckerbissen);
	}

	public void addFressakt(String a, String b) {
		fressakte.add(new String[] { a, b });
	}

	public void ladeGeschichte(File geschichtsdatei) {

		if (!geschichtsdatei.exists() || geschichtsdatei.isDirectory()) {
			System.err.println("Geschichtsdatei existiert nicht oder ist Ordner");
			return;
		}

		try {

			BufferedReader reader = new BufferedReader(new FileReader(geschichtsdatei));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");

				if (parts.length != 3) {
					System.err.println("Geschichts-Zeile ist ungültig und wird übersprungen: " + line);
					continue;
				}

				addFressakt(parts[0], parts[2]);

			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Konnte Fischdatei nicht laden");
			e.printStackTrace();
		}

	}

	public void ladeFische(File fischdatei) {

		if (!fischdatei.exists() || fischdatei.isDirectory()) {
			System.err.println("Fischdatei existiert nicht oder is Ordner");
			return;
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fischdatei));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");

				if (parts.length < 4 || parts.length > 5) {
					System.err.println("Fisch-Zeile ist ungültig und wird übersprungen: " + line);
					continue;
				}

				Nahrungstyp typ = Nahrungstyp.vonName(parts[1].trim());
				if (typ == Nahrungstyp.FISCH) {
					int gewicht, appetit;
					try {
						gewicht = Integer.parseInt(parts[3].trim());
						appetit = Integer.parseInt(parts[4].trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.err.println("Gewicht und/oder Appetit sind keine Integer: " + line);
						continue;
					}

					Fisch fisch = new Fisch(parts[0].trim(), typ, Esstyp.vonName(parts[2].trim()), gewicht, appetit);
					addFisch(fisch);
					addLeckerbissen(fisch);
				} else {
					int anzahl, gewicht;
					try {
						anzahl = Integer.parseInt(parts[2].trim());
						gewicht = Integer.parseInt(parts[3].trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.err.println("Anzahl und/oder Gewicht sind keine Integer: " + line);
						continue;
					}

					for (int i = 0; i < anzahl; i++) {
						addLeckerbissen(new Zeug(parts[0].trim(), Nahrungstyp.vonName(parts[1].trim()), gewicht));
					}
				}

			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Konnte Fischdatei nicht laden");
			e.printStackTrace();
		}

	}

	public void ladeZeug(File zeugdatei) {

		if (!zeugdatei.exists() || zeugdatei.isDirectory()) {
			System.err.println("Zeugdatei existiert nicht oder is Ordner");
			return;
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(zeugdatei));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");

				if (parts.length != 4) {
					System.err.println("Zeug-Zeile ist ungültig: " + line);
					continue;
				}

				int anzahl, gewicht;
				try {
					anzahl = Integer.parseInt(parts[2].trim());
					gewicht = Integer.parseInt(parts[3].trim());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.err.println("Anzahl und/oder Gewicht sind keine Integer: " + line);
					continue;
				}

				for (int i = 0; i < anzahl; i++) {
					addLeckerbissen(new Zeug(parts[0].trim(), Nahrungstyp.vonName(parts[1].trim()), gewicht));
				}

			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Konnte Zeugdatei nicht laden");
			e.printStackTrace();
		}

	}

}
