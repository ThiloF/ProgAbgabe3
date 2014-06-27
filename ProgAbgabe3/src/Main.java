import java.io.File;

/** 
 * Die Main-Klasse, welche die main-Methode enthält und eine Meer-Instanz macht
 * @author fkoen001
 */
public class Main {

	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("Verwendung: <Akteurdatei> <Szenendatei>");
			System.exit(-1);
		}

		File acteurs = new File(args[0]);
		File scene = new File(args[1]);

		if (!acteurs.exists() || acteurs.isDirectory()) {
			System.err.println("Die Akteurdatei ist ungültig.");
			System.exit(-1);
		}

		if (!scene.exists() || scene.isDirectory()) {
			System.err.println("Die Szenendatei ist ungültig.");
			System.exit(-1);
		}

		System.out.println("Lade Meer...");
		Meer meer = new Meer(acteurs, scene);

		System.out.println("Gehe Geschichte durch ...");
		System.out.println("-------------------------");

		meer.displayStory();

		System.out.println("-------------------------");
		System.out.println("Die Geschichte ist fertig");
	}

}
