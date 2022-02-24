/**
 * Un résultat d'un Coureur sur une course cycliste: le coureur, son temps et son nombre
 * de points.
 * 
 * @author Benoit Donnet et Charles Pecheur, UCLouvain
 * @version Septembre 2013
 */
public class Resultat {

	// le coureur
	private Coureur coureur;
	
	// le temps effectué
	private Temps temps;
	
	/**
	 * Crée un Resultat.
	 * 
	 * @pre c != null, t != null
	 * @post crée un résultat de temps {t} pour le coureur {c}.
	 */
	public Resultat(Coureur c, Temps t) {
		this.coureur = c;
		this.temps = t;
	}

	/**
	 * @pre -
	 * @post retourne le coureur.
	 */
	public Coureur getCoureur() {
		return this.coureur;
	}

	/**
	 * @pre -
	 * @post retourne le temps.
	 */
	public Temps getTemps() {
		return this.temps;
	}

	/**
	 * @pre other != null
	 * @post compare {this} et {other}, sur base du temps.
	 */
	public int compareTo(Resultat other) {
		return this.temps.compareTo(other.temps);
	}
	
	/**
	 * @pre -
	 * @post retourne ce Resultat sous forme de texte.
	 * 
	 * Exemple: "Alfred: 1:23:45"
	 */
	public String toString() {
		return String.format("%30s : %s", coureur.getNom(), temps);
	}

}
