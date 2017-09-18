/**
 * @author O. Bonaventure
 */
package src.librairies;

public interface MyString {

	/**
	 * @pre		-
	 * @post	Retourne la longueur de la chaîne de caractère
	 */
	public int length();

	/**
	 * @pre		0 <= i < this.length()
	 * @post	retourne le caractère à l'indice i de this
	 */
	public char charAt(int i);

	/**
	 * @pre		s != null
	 * @post	Retourne true si la chaîne de caractère s
	 *			est comprise dans la chaîne this, false sinon.
	 */
	public boolean contains(MyString s);

	/**
	 * @pre		-
	 * @post	Retourne la chaîne de caractère qui est
	 *			la concaténation de this et le caractère c
	 */
	public MyString concat(char c);
}
