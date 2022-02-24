package src;

public class Correction {

	private static int nextNum = 1;
	private int numero; // numéro du ticket

	/**
	 * @pre		-
	 * @post	Crée un ticket avec un nouveau numéro. Les numéros
	 *			sont attribués séquentiellement à partir de 1.
	 */
	public Correction() {
		this.numero = nextNum;
		nextNum ++;
	}

	/**
	 * @pre		-
	 * @post	retourne le numéro du billet
	 */
	public int getNumero() {
		return this.numero;
	}
}
