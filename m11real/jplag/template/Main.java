/**
 * Classe de test pour l'examne TourDeFrance
 * 
 * @author Benoit Donnet et Charles Pecheur, UCLouvain
 * @version Septembre 2013
 */
public class Main {

	private static Coureur[] coureurs = { new Coureur("Alfred", 24),
			new Coureur("Bernard", 27), new Coureur("Claude", 19),
			new Coureur("Daniel", 31), new Coureur("Emile", 25),
			new Coureur("Fred", 28), new Coureur("Gerard", 25) };

	public static void main(String[] args) {

		Classement cl = new ClassementTemps();
		
		while (true) {
			Coureur c = coureurs[(int) (Math.random() * coureurs.length)];
			Temps t = new Temps(0, 0, 500 + (int) (Math.random() * 5000));
			Resultat r = new Resultat(c, t);
			System.out.printf(">>>\n%s\n", r);
			Resultat r1 = cl.get(c);

			if (r1 == null) {
				System.out.println("  Pas encore classé");
			} else {
				System.out.println("  Actuellement classé " + cl.getPosition(c));
			}

			if (r1 != null && r.compareTo(r1) >= 0) {
				System.out.println("  Moins bon temps, ignoré");
			} else {
				System.out.println("  Meilleur temps, enregistré");
				cl.remove(c);
				cl.add(r);
				System.out.println(cl);
				System.out.println("  Maintenant classé " + cl.getPosition(c));
			}
			try {
				Thread.sleep(2000); // attendre deux secondes
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
