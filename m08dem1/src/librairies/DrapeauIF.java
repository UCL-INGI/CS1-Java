package src.librairies;

/*
 * @author François Michel
 */
public interface DrapeauIF {

	/**
	 * @pre		-
	 * @post	Le drapeau prend la valeur passée
	 *			en argument
	 */
	public void set(boolean drapeau);

	/**
	 * @pre		-
	 * @post	Si l'argument est 'V' ou 'v', le drapeau
	 *			prend la valeur true, et false sinon
	 */
	public void set(char c);

	/**
	 * @pre		-
	 * @post	retourne le drapeau sous forme d'un booléen
	 */
	public boolean toBoolean();
}
