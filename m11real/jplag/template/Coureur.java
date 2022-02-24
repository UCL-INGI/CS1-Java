/**
 * Un coureur cycliste.
 * 
 * @author Benoit Donnet et Charles Pecheur, UCLouvain
 * @version Septembre 2010
 */
public class Coureur {
	// Le nom du coureur.
	private String nom;

	// L'âge du coureur.
	private int age;

	/**
	 * @pre: nom != null, age > 0
	 * @post: Crée un coureur dont le nom est {nom} et dont l'âge est {age}
	 */
	public Coureur(String nom, int age) {
		this.nom = nom;
		this.age = age;
	}

	/**
	 * @pre: -
	 * @post: Retourne l'âge de ce coureur.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @pre: -
	 * @post: Retourne le nom de ce coureur.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Teste si ce coureur est égal à un objet quelconque. Le critère d'égalité
	 * porte sur le nom et l'âge du coureur.
	 * 
	 * @pre: -
	 * @post: Retourne {true} is {o} est égal à ce coureur, {false} sinon.
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Coureur) {
			Coureur c = (Coureur) o;
			return (this.nom.equals(c.getNom()) && this.age == c.getAge());
		} else {
			return false;
		}
	}
}
