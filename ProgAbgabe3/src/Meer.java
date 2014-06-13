import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Meer {

	private ArrayList<Leckerbissen> inhalt = new ArrayList<Leckerbissen>();
	private ArrayList<Fisch> fische = new ArrayList<Fisch>();

	public Meer() {
		ladeFische(new File("fische.txt"));
		ladeZeug(new File("zeug.txt"));
		/*
		 * for (Leckerbissen l : inhalt) { System.out.println(l.toString() + l.getNahrungstyp()); }
		 */

		doStory();

	}

	private void doStory() {
		Random random = new Random();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("out.txt")));
			for (int i = 0; i < 1000; i++) {
				Fisch fisch = fische.get(random.nextInt(fische.size()));
				Leckerbissen leckerbissen = inhalt.get(random.nextInt(inhalt.size()));
				try {
					writer.write(fisch.getName() + ": ");
					fisch.fressen(leckerbissen);
					writer.write("hat gegessen: " + leckerbissen.toString());
				} catch (MuellException | SchmecktNichtException | SattException | EatYourFriendException e) {
					writer.write(e.getMessage());
					e.printStackTrace();
				}
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("IO Error...");
			e.printStackTrace();
		}
	}

	public void addFisch(Fisch fisch) {
		fische.add(fisch);
	}

	public void addLeckerbissen(Leckerbissen leckerbissen) {
		inhalt.add(leckerbissen);
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

				if (parts.length != 5) {
					System.err.println("Fisch-Zeile ist ungültig: " + line);
					continue;
				}

				int gewicht, appetit;
				try {
					gewicht = Integer.parseInt(parts[3].trim());
					appetit = Integer.parseInt(parts[4].trim());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.err.println("Gewicht und/oder Appetit sind keine Integer: " + line);
					continue;
				}

				Fisch fisch = new Fisch(parts[0].trim(), Nahrungstyp.vonName(parts[1].trim()), Esstyp.vonName(parts[2].trim()), gewicht, appetit);
				addFisch(fisch);
				addLeckerbissen(fisch);
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

				// Zeug a = new Zeug("Stiefel", Nahrungstyp.MUELL, 100);
				// Zeug b = new Zeug("Algen", Nahrungstyp.PFLANZE, 5);

				// a.getGramm();

			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Konnte Zeugdatei nicht laden");
			e.printStackTrace();
		}

	}

}
