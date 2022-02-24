package StudentCode;

/**
 * @author O. Bonaventure
 * @version Novembre 2016
 */
import src.librairies.Stat;

import java.util.Arrays;

class MatriceCarreCorr implements Stat {

	private double [][] m;

	/**
	 * @pre		n > 0
	 * @post	Construit une matrice carrée de taille n*n
	 */
	public MatriceCarreCorr(int n) {
		this.m = new double[n][n];
	}

	/**
	 *	Constructeur utilisé pour les test. Ne pas montrer aux 
	 *	étudiant
	 *	
	 *	@pre	m != null
	 *	@post	Construit une instance avec la matrice passée en paramètre
	 */
	 public MatriceCarreCorr(double [][] m) {
		this.m = m;
	}

	/**
	 * @pre		0 <= i <n , 0 <= j < n
	 * @post	Stocke la valeur d en position (i,j) dans la matrice
	 */
	public void set(int i, int j, double d) {
		this.m[i][j] = d;
	}

	/**
	 * @pre		0 <= i < n , 0 <= j < n
	 * @post	Retourne la valeur stockée en (i,j) dans la matrice
	 */
	public double get(int i,int j) {
		return this.m[i][j];
	}

	public double max() {
		double max = this.m[0][0];
		for(double [] d : this.m) {
			for(double elem : d) {
				max =( elem >= max )? elem : max;
			}
		}
		return max;
	}

	public double min() {
		double min = this.m[0][0];
		for(double [] d : this.m) {
			for(double elem : d) {
				min = (elem <= min )? elem : min;
			}
		}
		return min;
	}

	public double moyenne() {
		double sum = 0.0;
		for(double [] d : this.m) {
			for (double elem : d) {
				sum += elem;
			}
		}
		return sum/((double)(this.m.length*this.m.length));
	}

	/* Ne pas montrer aux étudiants */
	public String toString() {
		String r = "";
		for(int i=0;i < this.m.length; i++) 
			r += Arrays.toString(this.m[i])+"\n";
		return r;
	}
}
