import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Lisa Schröder (?), Thilo Falkenstein (877699), Felix König (577751)
 * Die Meer-Klasse liest aus 2 Dateien Akteure und Fressakte ein, welche dann mit displayStory() ausgegeben werden können
 */
public class Meer {

	private ArrayList<Leckerbissen> inhalt = new ArrayList<Leckerbissen>();
	private ArrayList<String[]> fressakte = new ArrayList<String[]>();

	/**
	 * Erstellt ein neues Meer, definiert durch die 2 Dateien für Akteure und Fressakte
	 * @param acteurs Textdatei der Akteure (Fische, Pflanzen, Müll etc.)
	 * @param scene Textdatei der Fressakte ("A frisst B")
	 */
	public Meer(File acteurs, File scene) {
		ladeFische(acteurs);
		ladeFressakte(scene);
	}

	/**
	 * Sucht im Meer nach einem Etwas
	 * @param name Name des Etwas
	 * @return Leckerbissen Das Etwas, welches dem Namen entspricht
	 */
	private Leckerbissen find(String name) {
		for (Leckerbissen l : inhalt) {
			if (l.getName().equals(name))
				return l;
		}
		return null;
	}

	/**
	 * Geht die Geschichte durch und gibt sie auf der Standardausgabe aus
	 */
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

	/**
	 * Fügt etwas dem Meer hinzu
	 * @param leckerbissen Irgendein "Leckerbissen", der ins Meer geworfen werden soll
	 */
	public void addLeckerbissen(Leckerbissen leckerbissen) {
		inhalt.add(leckerbissen);
	}

	/**
	 * Fügt einen Fressakt der Liste hinzu, die bei displayStory() abgearbeitet wird
	 * @param a Name des hungrigen Fisches
	 * @param b Name des Leckerbissen, der gefressen werden soll
	 */
	public void addFressakt(String a, String b) {
		fressakte.add(new String[] { a, b });
	}

	/**
	 * Liest Fressakte aus einer Datei und fügt sie der Liste hinzu
	 * @param aktdatei Textdatei mit Fressakten
	 */
	private void ladeFressakte(File aktdatei) {

		if (!aktdatei.exists() || aktdatei.isDirectory()) {
			System.err.println("Geschichtsdatei existiert nicht oder ist Ordner");
			return;
		}

		try {

			BufferedReader reader = new BufferedReader(new FileReader(aktdatei));
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

	/**
	 * Liest "Leckerbissen", also Meeresinhalte aus einer Datei und wirft sie ins Meer
	 * @param fischdatei Textdatei mit Leckerbissen
	 */
	private void ladeFische(File fischdatei) {

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

}
