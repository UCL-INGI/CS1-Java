/**
 * @author Alexandre Dubray
 */

package src;

public class CorrectionItem{

	private final String author, title;
	private final int serial;

	/**
	 * Constructeur
	 *
	 * @pre		author != null, title != null, serial > 0
	 * @post	Une instance de la classe est créée, et représente
	 *			un objet ayant comme auteur author, comme titre title 
	 *			et comme numéro de série serial
	 */
	public CorrectionItem (String author, String title, int serial) {
		this.author = author;
		this.title = title;
		this.serial = serial;
	}

	/**
	 * @pre		-
	 * @post	Retourne la valeur de author
	 */
	 public String getAuthor() {
		return this.author;
	}

	/**
	 * @pre		-
	 * @post	Retourne la valeur de title
	 */
	 public String getTitle() {
		return this.title;
	}

	/**
	 * @pre		-
	 * @pot		Retourne la valeur de serial
	 */
	 public int getSerial() {
		return this.serial;
	}


	/**
	 * @pre		-
	 * @post	La valeur renvoyée contient une représentation
	 *			de cet objet de la forme :
	 *			[serial] author, title
	 */
	public String toString() {
		return ("["+serial+"] "+author+", "+title);
	}
}
