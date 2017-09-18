package src.librairies;
/**
 * @author O. Bonaventure
 * @version Novembre 2016
 *
 * Une interface donnant des statistique par
 * rapport à un ensemble
 */
public interface Stat {

	/**
	 * @pre		-
	 * @post	Retourne le plus grand élément de l'ensemble
	 */
	public double max();

	/**
	 * @pre		-
	 * @post	Retourne le plus petit élément de l'ensemble
	 */
	public double min();

	/**
	 * @pre		-
	 * @post	Retourne la moyenne des nombres présents dans l'ensemble
	 */
	public double moyenne();
}
