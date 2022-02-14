package StudentCode;
/**
 * @author O. Bonaventure
 * @version Novembre 2016
 */

import src.librairies.Stat;
import java.util.Arrays;

class VecteurCorr implements Stat {

	private double [] v;

	/**
	 * @pre		n > 0
	 * @post	Construit un vecteur contenant n réels;
	 */
	public VecteurCorr (int n) {
		this.v = new double[n];
	}

	/**
	 *	Constructeur utilisé pour les test. ne pas montrer aux étudiants
	 *	
	 *	@pre	v != null
	 *	@post	crée un vecteur représenté par v
	 */
	 public VecteurCorr (double [] v){
		this.v = v;
	}

	/**
	 * @pre		0 <= i < n
	 * @post	Stocke la valeur d en position i du vecteur
	 */
	public void seet(int i,double d) {
		v[i] = d;
	}

	/**
	 * @pre		0 <= i < n
	 * @post	Retourne la valeur se trouvant en position i du vecteur
	 */
	public double get(int i) {
		return v[i];
	}

	public double max() {
		double max = this.get(0);
		for(double d : this.v)
			max = (d >= max) ? d : max;
		return max;
	}

	public double min() {
		double min = this.get(0);
		for(double d : this.v)
			min = (d <= min) ? d : min;
		return min;
	}

	public double moyenne() {
		double sum =0.0;
		for(double d : this.v)
			sum += d;
		return sum/(double)this.v.length;
	}

	public String toString() {
		return Arrays.toString(this.v);
	}
}
