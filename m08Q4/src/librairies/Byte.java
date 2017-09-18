package src.librairies;

/**
 *
 * Classe représentant un byte
 *
 * @author O. Bonaventure
 * @version November 2016
 */
public interface Byte {

	/**
	 * @pre		0 <= i <= 7
	 * @post	Retourne la valeur du bit à la position i
	 */
	public int getBit(int i);

	/**
	 * @pre		0 <= i <= 7
	 * @post	Le bit à la position i est mis à 1
	 */
	public void setBit(int i);

	/**
	 * @pre		0 <= i <= 7
	 * @post	Le bit à la position i est mis à 0
	 */
	public void resetBit(int i);

	/**
	 * @pre		-
	 * @post	Les bits sont décallée d'un bit vers la gauche
	 *			(attention à la structure interne). Le bit de
	 *			poids faible est mis à 0. Par exemple, le byte
	 *			"10011001" deviendrait "00110010".
	 */

	public void shiftLeft();

	/**
	 * @pre		-
	 * @post	Les bits sont décallé d'un bit vers la droite.
	 *			(attention à la structure internet). Le bit de
	 *			poids fort prend la valeur initiale du bit de 
	 *			poids faible. Par exemple , le byte 
	 *			"10011001" deviendrait "11001100".
	 */
	public void rotateRight();

	/**
	 * @pre		-
	 * @post	On effectue une ET logique entre chaque bit
	 *			de b et chaque bit de this
	 */
	public Byte and(Byte b1);
}
