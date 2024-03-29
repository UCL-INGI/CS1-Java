package src;

import java.util.Arrays;

import src.librairies.De;
/**
 * @author O. Bonaventure
 * @version Nov. 2016
 * refactor aout 2017 Dubray Alexandre
 */

class DeStatsCorr extends De {

	private int lances;
	private int [] resultats;

	/**
	 * @pre		s != null;
	 * @post	Crée une instance de la classe DeStatsCorr
	 *			avec ocmme nom s
	 */
	public DeStatsCorr(String s) {
		super(s);
		this.lances = 0;
		this.resultats = new int[6];
	}

	/**
	 * @pre		-
	 * @post	retourne le nombre de lancés du dé this
	 */
	public int getLances() {
		return this.lances;
	}

	/**
	 * @pre		-
	 * @post	retourne le tableau des résultats du dé this
	 */
	public int[] getResultats() {
		return this.resultats;
	}

	/**
	 * @pre		1 <= n <= 6
	 * @post	retourne le nombre de fois que le dé
	 *			a donné comme résultat n
	 */
	public int statistique(int n) {
		return this.resultats[n-1];
	}

	/**
	 * @pre		-
	 * @post	incrémente le nombre de lancés et le 
	 *			nombre de fois qu'un chiffre est sorti.
	 *			Retourne le chiffre que le dé a donné.
	 */

	public int lance() {
		int res = super.lance();
		this.lances++;
		resultats[res-1]++;
		return res;
	}

	/**
	 * @pre		-
	 * @post	retourne une description du dé sous forme
	 *			d'un String. Le format retourné est le nom du 
	 *			dé suivi des statistique sous forme de tableau
	 */
	public String toString() {
		return super.toString() + " " + this.getLances() + " " + Arrays.toString(this.getResultats());
	}

	private String resToString() {
		String str = "|";
		for(int i : this.getResultats())
			str += i + "|";
		return str;
	}

	/**
	 * @pre		-
	 * @post	retourne true si this et o sont deux DeStatsCorr identiques
	 */
	public boolean equals(Object o) {
		if (o instanceof DeStatsCorr) {
			DeStatsCorr other = (DeStatsCorr) o;
			return super.equals(o) && this.getLances() == other.getLances() && this.getResultats().equals(other.getResultats());
		}
		return false;
	}

}
